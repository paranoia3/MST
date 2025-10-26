package algorithm;

import org.example.OperationCounter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisjointSetUnion {
    private final Map<String, String> parent;
    private final Map<String, Integer> rank;
    private final OperationCounter counter;

    public DisjointSetUnion(List<String> nodes, OperationCounter counter) {
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
        this.counter = counter;
        for (String node : nodes) {
            parent.put(node, node);
            rank.put(node, 0);
        }
    }

    public String find(String i) {
        counter.count++;
        if (parent.get(i).equals(i)) {
            return i;
        }

        String root = find(parent.get(i));
        parent.put(i, root);
        return root;
    }

    public void union(String x, String y) {
        String rootX = find(x);
        String rootY = find(y);

        counter.count++;
        if (!rootX.equals(rootY)) {
            counter.count += 3;
            if (rank.get(rootX) > rank.get(rootY)) {
                parent.put(rootY, rootX);
            } else if (rank.get(rootX) < rank.get(rootY)) {
                parent.put(rootX, rootY);
            } else {
                parent.put(rootY, rootX);
                rank.put(rootX, rank.get(rootX) + 1);
            }
        }
    }
}