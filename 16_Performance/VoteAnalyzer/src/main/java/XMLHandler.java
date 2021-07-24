import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class XMLHandler extends DefaultHandler {
    int limit = 5000000;
    int count = 0;
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private HashMap<Voter, Integer> voterCount;
    
    
    public XMLHandler(){
        voterCount = new HashMap<>();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && count < limit) {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay");
                try {
                    DBConnection.countVoter(name, birthDay);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("voter")){
            voter = null;
        }
    }

    public void printVoters(){
        voterCount.keySet().forEach(voter1 -> {
            int count = voterCount.get(voter1);
            if(count > 1){
                System.out.println(voter1.toString() + " - " + count);
            }
        });



//        for (Voter voter : voterCount.keySet()) {
//            int count = voterCount.get(voter);
//            if(count > 1){
//                System.out.println(voter.toString() + " - " + count);
//            }
//        }
    }
}
