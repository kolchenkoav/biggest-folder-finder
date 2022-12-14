import java.io.File;
import java.util.ArrayList;

public class Node {
    private File folder;
    private long size;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;
    private ArrayList<Node> children;
    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        children.add(node);
    }

    public File getFolder() {
        return folder;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        if (Main.sizeLimit > getSize()) { return ""; }

        StringBuilder builder = new StringBuilder();
        String size = SizeCalculator.getHumanReadableSize(getSize());
        builder.append(folder.getName() + " - " + size + "\n");
        for (Node child : children) {
            if (Main.sizeLimit <= child.getSize()) {
                builder.append("  ".repeat(getLevel() + 1) + child.toString());
            }
        }
        return builder.toString();
    }
}
