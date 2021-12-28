import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Parser{
    private List<String> urls = new ArrayList<>();
    private String path;

    public Parser(String path){
        this.path = path;
    }

    public List<String> parse(){
        StringBuilder builder = new StringBuilder();

        Document doc = null;
        try {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doc = Jsoup.connect(path).get();
            Elements elements = doc.select("a");
            elements.forEach(element -> {
                String href = element.attr("abs:href");
                if(isValidElement(href, path)){
                    builder.append(addSpace(href + "\n"));
                    urls.add(addSpace(href));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public static boolean isValidElement(String element, String url){
        boolean valid;
        if(element.contains(url) && !element.contains("#")){
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }

    public static String addSpace(String element){
        StringBuilder builder = new StringBuilder();
        int countSlash = 0;
        for (int i = 0; i < element.length(); i++) {
            if(element.charAt(i)=='/'){
                countSlash++;
            }
        }

        for (int i = 0; i < countSlash-2; i++) {
            builder.append("\t");
        }
        return builder.append(element).toString();
    }

}
