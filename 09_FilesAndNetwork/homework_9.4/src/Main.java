import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    private static final String imgRepository = "images";
    public static void main(String[] args){
        Document doc = null;
        Elements elements = null;
        try {
            doc = Jsoup.connect("https://lenta.ru").get();
            elements = doc.select("img");
            elements.forEach(element ->{
                try {
                    download(element.attr("abs:src"), imgRepository);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void download(String url, String newFile) throws Exception {
        try (InputStream in = URI.create(url).toURL().openStream()) {
            String urlForNewFile = url.substring(url.lastIndexOf("/")+1, url.length());
            for (int i = 0; i < urlForNewFile.length()-1; i++) {
                if(urlForNewFile.charAt(i + 1) == '?' || urlForNewFile.charAt(i + 1) == '*' || urlForNewFile.charAt(i + 1) == '|' || urlForNewFile.charAt(i + 1) == '\\'){
                    String s = urlForNewFile.substring(0, i) + "\\";
                    urlForNewFile = urlForNewFile.replace(urlForNewFile.substring(0, i), s);
                }
            }
            String fileName = newFile + File.separator + urlForNewFile;
            Files.copy(in, Paths.get(fileName));
        }
    }
}
