public class ParametersBack {
    private long limit;
    private String path;

    public ParametersBack(String[] args) {
        path = args[1];
        limit = SizeCalculator.getSizeFromHumanReadable(args[3]);
    }

    public long getLimit() {
        return limit;
    }

    public String getPath() {
        return path;
    }
}
