import java.util.HashMap;

public class SizeCalculator {
    private final static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

    public static String getHumanReadableSize(long size) {
        for (int i = 0; i < sizeMultipliers.length; i++) {
            double value = ((double) size) / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value * 100)/100. + " " +
                        sizeMultipliers[i] +
                        (i > 0 ? "b" : "");
            }
        }
        return "Very big...";
    }

    // 24B, 234Kb, 36Mb, 34Gb, 42Tb
    // 24B, 234K, 36M, 34G, 42T
    // 235K => 235 * 1024 = 240640
    public static long getSizeFromHumanReadable(String size) {
        if (size.substring(0, 1) == "0") {
            return 0;
        }
        HashMap<Character, Integer> char2multipliers = getMultipliers();
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multipliers.get(sizeFactor);
        long length = Long.parseLong(size.replaceAll("[^0-9]", ""));
        return length * multiplier;
    }

    private static HashMap<Character, Integer> getMultipliers() {
        HashMap<Character, Integer> char2multipliers = new HashMap<>();
        int i = 0;
        for (char multiplier : sizeMultipliers) {
            char2multipliers.put(multiplier, (int) Math.pow(1024, i));
            i++;
        }
        return char2multipliers;
    }
}
