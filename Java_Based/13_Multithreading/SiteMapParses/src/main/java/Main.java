import org.jsoup.Jsoup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
       public static void main(String[] args) {
        Parser parser = new Parser("https://lenta.ru");
        parser.parse();
        ForkJoinPool pool = new ForkJoinPool(50);

        List<String> list = new ForkJoinPool().invoke(new ParserTask(parser));

           for (String s : list) {
               System.out.println(s);
           }
           System.out.println(list.size());
    }
}
