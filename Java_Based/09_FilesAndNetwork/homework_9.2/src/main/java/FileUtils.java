import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        File sourceDir = new File(sourceDirectory);
        File destinationDir = new File(destinationDirectory);
        if(sourceDir.isDirectory() && destinationDir.isDirectory()){
            File[] files = sourceDir.listFiles();
            for (File file : files) {
                if(file.isDirectory()){
                    File newFolder = new File(destinationDir.getPath().toString() + "/" + file.getName());
                    Path path = Files.createDirectory(newFolder.toPath());
                    FileUtils.copyFolder(file.toPath().toString(), path.toString());
                }else {
                    byte[] bytes = Files.readAllBytes(file.toPath());
                    FileOutputStream fos = new FileOutputStream(destinationDirectory + "/" + file.getName());
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                }
            }
        }
    }
}
