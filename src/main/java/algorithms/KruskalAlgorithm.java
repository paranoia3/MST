package algorithms;

import models.AlgorithmStats;
import models.Edge;
import org.assigment.OperationCounter;
import java.util.ArrayList;
import java.util.List;


public class KruskalAlgorithm {

    public AlgorithmStats run(List<String> nodes, List<Edge> edges) {
        OperationCounter counter = new OperationCounter();

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;

        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort((e1, e2) -> {
            counter.count++;
            return Integer.compare(e1.getWeight(), e2.getWeight());
        });

        DisjointSetUnion dsu = new DisjointSetUnion(nodes, counter);

        for (Edge edge : sortedEdges) {
            String fromRoot = dsu.find(edge.getFrom());
            String toRoot = dsu.find(edge.getTo());

            counter.count++;
            if (!fromRoot.equals(toRoot)) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();
                dsu.union(edge.getFrom(), edge.getTo());
            }

            if (mstEdges.size() == nodes.size() - 1) {
                break;
            }
        }

        return new AlgorithmStats(mstEdges, totalCost, counter.count, 0.0);
    }
}
