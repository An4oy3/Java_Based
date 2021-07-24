import core.Line;
import core.Station;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String url = "https://www.moscowmap.ru/metro.html#lines";
    private static final String resultURL = "src/resources/map.json";

    private static List<Line> lineList = new ArrayList<>();
    private static List<Station> stationList = new ArrayList<>();
    private static Map<Station, Set<Station>> connections = new HashMap<>();

    public static void main(String[] args) {
        createMetroMap(url);
        createJSON(buildJSON());
        stationsCount(resultURL);

    }

    private static void stationsCount(String URL){

        StringBuilder sb = new StringBuilder();
        try {
            List<String> list = Files.readAllLines(Paths.get(URL));
            list.forEach(sb::append);
            JSONObject jsonObject = (JSONObject) JSONValue.parse(sb.toString());

            JSONObject stations = (JSONObject) jsonObject.get("stations");

            Set set = stations.keySet();

            for (Object o : set) {
                ArrayList<String> stationlist = (ArrayList<String>) stations.get(o);
                System.out.println("Линия №" + o + " : " + "кол-во станций " + stationlist.size());
                //stationList.forEach(System.out::println);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void createJSON(JSONObject jsonString){
        File file = new File(resultURL);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            jsonString.writeJSONString(writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONObject buildJSON(){
        JSONObject jsonObject = new JSONObject();

        Map<String, List<String>> map = new TreeMap<>();
        for (Line line : lineList) {
            ArrayList<String> stationlist = new ArrayList<>();
            line.getStations().forEach(s -> stationlist.add(s.getName()));
            map.put(line.getNumber(), stationlist);
        }
        jsonObject.put("stations", map);

        JSONArray array = new JSONArray();
        for (Line line : lineList) {
            JSONObject object = new JSONObject();
            object.put("number", line.getNumber());
            object.put("name", line.getName());
            array.add(object);
        }
        jsonObject.put("lines", array);


       JSONArray arrayConnections = new JSONArray();
       int connectionsCount = 0;
       for(Map.Entry<Station, Set<Station>> entry : connections.entrySet()) {
           JSONArray entryArrayConnections = new JSONArray();
           if (!entry.getValue().isEmpty()) {
               for (Station station : entry.getValue()) {
                   JSONObject object = new JSONObject();
                   object.put("line", station.getLine());
                   object.put("station", station.getName());
                   entryArrayConnections.add(object);
                   connectionsCount++;
               }
               arrayConnections.add(entryArrayConnections);
           }
       }
       jsonObject.put("connections", arrayConnections);
       System.out.println("Количество переходов - " + connectionsCount);

        return jsonObject;
    }


    private static void createMetroMap(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).maxBodySize(0).get();

            Elements elements = doc.select("span.js-metro-line");
            elements.forEach(element -> {
                lineList.add(new Line(element.attr("data-line"), element.text()));
            });

            Elements elementsStation = doc.select("div.js-metro-stations");
            elementsStation.forEach(element -> {
                element.select("span.name").forEach(e -> {
                    stationList.add(new Station(element.attr("data-line"), e.text()));
                });
            });
            


            for (Line line : lineList) {
                for (Station station : stationList) {
                    if (line.getNumber().equals(station.getLine()))
                        line.addStation(station);
                }
            }
            connectionsCreated(doc);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connectionsCreated(Document doc){
        //Формирую пересадки. Лист st - лист с названиями станций & Лист con - лист с пересадками & Лист ln - лист с номерами линий
        Elements elementsConnection = doc.select("span");
        ArrayList<String> ln = new ArrayList<>();
        ArrayList<String> st = new ArrayList<>();
        ArrayList<String> con = new ArrayList<>();
        elementsConnection.forEach(element -> {
            ln.add(element.select("span.js-metro-line").attr("data-line"));
            st.add(element.select("span.name").text());
            con.add(element.attr("title"));
        });

        //Формирую мапу из трех листов(st&con&ln), в которой каждой станции соответствует список переходов.
        Map<String, Set<String>> result = new HashMap<>();
        String lineNum = "";//Переменная, что бы помечать какой номер линии у у станции-ключа
        for (int i = 0; i < st.size(); i++) {
            Set<String> stationsForCon = new HashSet<>();
            if(!ln.get(i).isEmpty()){
                lineNum = ln.get(i);
            }
            if(!(st.get(i).isEmpty()) && con.get(i+1).contains("переход на станцию")){
                for (int j = i+1; j < con.size(); j++) {
                    if(con.get(j).isEmpty()){
                        break;
                    } else {
                        stationsForCon.add(con.get(j));
                    }
                }
                result.put((st.get(i) + " " + lineNum), stationsForCon);
            }
        }

        //Формирую мапу connections
        for(Map.Entry<String, Set<String>> entry : result.entrySet()){
            String key = entry.getKey();
            String keyStationName = key.substring(0, key.lastIndexOf(" "));
            String keyLineNum = key.substring(key.lastIndexOf(" ")+1, key.length());

            //Форматирую пересадки(Значения мапы result) в валидный вид
            Set<String> value = entry.getValue();
            Set<Station> connectionsSet = new HashSet<>();
            for (String s : value) {
                String lineValue = s.substring(s.lastIndexOf("»")+1, s.length()).trim();
                if(lineValue.equals("Белорусско-Савёловского (первого) диаметра")) {
                    lineValue = "МЦД-1";
                }else if(lineValue.equals("Курско-Рижского (второго) диаметра")) {
                    lineValue = "МЦД-2";
                }else if(lineValue.equals("БКЛ")) {
                    lineValue = "Большая Кольцевая Линия";
                }else {
                    lineValue = lineValue.replaceAll("ой", "ая");
                    lineValue = lineValue.replaceAll("линии", "линия");
                }

                //Меняю Имя Линии на ее номер
                for (Line line : lineList) {
                    if(line.getName().equals(lineValue)){
                        lineValue = line.getNumber();
                        break;
                    }
                }
                String stationValueName = s.substring(s.indexOf('«')+1, s.indexOf('»'));


                for (Station station : stationList) {
                    if(station.getName().equals(stationValueName) && station.getLine().equals(lineValue)){
                        connectionsSet.add(station);
                        break;
                    }
                }
                //System.out.println(stationValueName + " " + lineValue);
            }
            for (Station station : stationList) {
                if(station.getName().equals(keyStationName) && station.getLine().equals(keyLineNum)){
                    connections.put(station, connectionsSet);
                }
            }
        }
//        for(Map.Entry<Station, Set<Station>> entry : connections.entrySet()){
//            System.out.println(entry.getKey().getName() + " " + entry.getKey().getLine());
//            for (Station station : entry.getValue()) {
//                System.out.println("\t" + station.getName() + " " + station.getLine());
//            }
//        }
    }
}
