import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.*;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        PrintWriter writer = new PrintWriter("res/numbers.txt");
        PrintWriter writer1 = new PrintWriter("res/numbers1.txt");
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};


        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(() -> {
            StringBuilder sb = null;
            for(int regionCode = 1; regionCode < 100; regionCode++){
                sb = new StringBuilder("");
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                sb.append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter);
                                if(regionCode < 10) {
                                    sb.append(padNumber(regionCode, 2))
                                            .append("\n");
                                } else{
                                    sb.append(regionCode)
                                            .append("\n");
                                }

                            }
                        }
                    }
                }
                writer.write(sb.toString());
                writer1.write(sb.toString());
            }
            return sb;
        }).get();

        service.shutdown();
        writer1.flush();
        writer1.close();
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static synchronized String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.append('0');
        }
        return numberStr.reverse().toString();
    }
}
