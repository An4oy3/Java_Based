import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path;
        while (true){
            try {
                path = reader.readLine();
                if(path.equals("exit")){
                    break;
                }
                long result = FileUtils.calculateFolderSize(path);
                DecimalFormat format = new DecimalFormat("#.00");
                if(result <= 1024000) {
                    System.out.println( format.format(result / 1024.0) + " Кбайт");
                } else if(result >1024000 && result <= 1048576000){
                    System.out.println( format.format(result / 1048576.0) + " Мбайт");
                } else {
                    System.out.println( format.format(result / 1073741824.0) + " Гбайт");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
