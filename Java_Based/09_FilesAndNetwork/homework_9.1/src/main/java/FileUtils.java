import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        long size = 0;
       File dir = new File(path);
       File[] files = dir.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                size += FileUtils.calculateFolderSize(file.getPath());
            } else {
                size += file.length();
            }
        }

        return size;
    }
}
