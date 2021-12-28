import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            try {
                System.out.println("Введите путь к папке, которую необходимо скопировать: ");
                String sourceDirectory = reader.readLine();
                if (sourceDirectory.equals("exit")) {
                    break;
                }
                System.out.println("Введите путь к папке, в которую необходимо скопировать: ");
                String destinationDirectory = reader.readLine();
                if(destinationDirectory.equals("exit")){
                    break;
                }
                FileUtils.copyFolder(sourceDirectory, destinationDirectory);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
