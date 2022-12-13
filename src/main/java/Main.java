import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static long sizeLimit;
    public static void main(String[] args) {
        String folderPath;
        if (args.length != 4) {
            folderPath = "E:\\Downloads";
            sizeLimit = SizeCalculator.getSizeFromHumanReadable("300Mb");
        } else {
            folderPath = args[1];
            sizeLimit = SizeCalculator.getSizeFromHumanReadable(args[3]);
        }

        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);


        System.out.println("=============================================");
        System.out.println("Time :  " + (System.currentTimeMillis()-start) + " мс");
        System.out.println("Path :  " + folderPath);
        System.out.println("Limit > then " + SizeCalculator.getHumanReadableSize(sizeLimit));
        System.out.println("BiggestFolderFinder.jar -d E:\\Records -l 500Mb");
        System.out.println("=============================================");
        System.out.println(root);
    }
}
