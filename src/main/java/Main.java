import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String folderPath = "E:\\Records";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

//        long sizeFolder = getFolderSize(file);
        //4065 мс
        //753 Gb

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long sizeFolder = pool.invoke(calculator);
        //2606 мс
        //809533860139 bytes
        //753 Gb

        System.out.println(System.currentTimeMillis()-start+ " мс");

        //printSizeFolder(sizeFolder);
        System.out.println(getHumanReadableSize(sizeFolder));

        System.out.println(getSizeFromHumanReadable("235K"));
        System.out.println(getSizeFromHumanReadable("42Tb"));

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

    public static String getHumanReadableSize(long size) {
        long x = size;
        String[] as = {"B", "Kb", "Mb", "Gb", "Tb"};
        int ind = 0;
        while (Long.toString(x).length() > 4) {
            x /= 1024;
            ind++;
        }
        return x + " " + as[ind];
    }

    // 24B, 234Kb, 36Mb, 34Gb, 42Tb
    // 24B, 234K, 36M, 34G, 42T
    // 235K => 235 * 1024 = 240640
    public static long getSizeFromHumanReadable(String size) {
        HashMap<Character, Integer> char2multipliers = getMultipliers();
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multipliers.get(sizeFactor);
        long length = Long.parseLong(size.replaceAll("[^0-9]", ""));
        return length * multiplier;
    }

    private static HashMap<Character, Integer> getMultipliers() {
        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        HashMap<Character, Integer> char2multipliers = new HashMap<>();
        int i = 0;
        for (char multiplier : multipliers) {
            char2multipliers.put(multiplier, (int) Math.pow(1024, i));
            i++;
        }
        return char2multipliers;
    }
}
