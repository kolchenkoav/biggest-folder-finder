import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static long sizeLimit = SizeCalculator.getSizeFromHumanReadable("500 Mb");
    public static void main(String[] args) {
        String folderPath = "E:\\Records";
        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);


        System.out.println("=============================================");
        System.out.println("Time :  " + (System.currentTimeMillis()-start) + " мс");
        System.out.println("Path :  " + folderPath);
        System.out.println("Limit > " + SizeCalculator.getHumanReadableSize(sizeLimit));
        System.out.println("=============================================");
        System.out.println(root);
    }
}
