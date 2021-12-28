import org.imgscalr.AsyncScalr;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static int coreCount = 4;

    public static void main(String[] args) {
        String srcFolder = "D:/1/src";
        String dstFolder = "D:/1/dst";

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();


        File[] allFiles = srcDir.listFiles();

       try {
           for (File file : allFiles) {
               BufferedImage image = ImageIO.read(file);
               if (image == null) {
                   continue;
               }
               BufferedImage newImage = Scalr.resize(image, image.getWidth()/2, image.getHeight()/2);
               File newFile = new File(dstFolder + "/" + file.getName());
               ImageIO.write(newImage, "jpg", newFile);

           }
       } catch (Exception e){
           e.printStackTrace();
       }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
