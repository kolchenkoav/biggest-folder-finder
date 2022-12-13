import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static long sizeLimit;
    public static void main(String[] args) {
        String folderPath;
        if (args.length != 4) {
            folderPath = "E:/Downloads";
            sizeLimit = SizeCalculator.getSizeFromHumanReadable("300Mb");
        } else {
            ParametersBack pb = new ParametersBack(args);
            folderPath = pb.getPath();
            sizeLimit = pb.getLimit();
        }

        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);

        printResult(start, folderPath, root);
    }
    private static void printResult(long start, String folderPath, Node root) {
        System.out.println("===========================================================================");
        System.out.println("Time :  " + (System.currentTimeMillis()-start) + " мс");
        System.out.println("Path :  " + folderPath);
        System.out.println("Limit > then " + SizeCalculator.getHumanReadableSize(sizeLimit));
        System.out.println();
        System.out.println("parameters : -d <pathForFolder> -l <Limit>   (b, Kb, Mb, Gb, Tb)");
        System.out.println("For example: java -jar BiggestFolderFinder.jar -d E:/Downloads -l 200mb");
        System.out.println("===========================================================================");
        System.out.println(root);
    }
}
