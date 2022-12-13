import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String folderPath = "P:\\JAVA";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        // 121 262 мс
        // 289084948843 bytes
//        long sizeFolder = getFolderSize(file);

        // 113 922 мс
        // 289084948843 bytes
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long sizeFolder = pool.invoke(calculator);

        System.out.println(System.currentTimeMillis()-start+ " мс");

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
