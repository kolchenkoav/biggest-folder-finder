import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String folderPath = "E:\\Skilbox";
        File file = new File(folderPath);

        long sizeFolder = getFolderSize(file);
        printSizeFolder(sizeFolder);
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) { return folder.length(); }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file: files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
    public static void printSizeFolder(long sizeFolder) {
        System.out.println(sizeFolder + " bytes");              // 118 MB (124426484 bytes)
        System.out.println(sizeFolder/1024 + " kb");            // 118 MB (124426484 bytes)
        System.out.println((sizeFolder/1024)/1024 + " MB");     // 118 MB (124426484 bytes)
        System.out.println((((double) sizeFolder/1024)/1024)/1024 + " GB");     // 118 MB (124426484 bytes)
    }
}


//        System.out.println(file.length());
//        Set keys = System.getProperties().keySet();
//        for(Object key : keys) {
//            System.out.println(key);
//        }
//        System.out.println(System.getProperties().get("user.dir"));
//        System.out.println(System.getProperties().get("user.home"));