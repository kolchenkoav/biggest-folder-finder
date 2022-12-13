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
        System.out.println(sizeFolder + " bytes");
    }
}
