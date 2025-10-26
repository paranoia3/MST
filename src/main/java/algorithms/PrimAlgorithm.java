package algorithms;

import models.AlgorithmStats;
import models.Edge;
import org.assigment.OperationCounter;

import java.util.*;

public class PrimAlgorithm {

    public AlgorithmStats run(List<String> nodes, List<Edge> edges) {
        OperationCounter counter = new OperationCounter();

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        Set<String> visited = new HashSet<>();

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
            counter.count++;
            return Integer.compare(e1.getWeight(), e2.getWeight());
        });

        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge edge : edges) {
            adj.computeIfAbsent(edge.getFrom(), k -> new ArrayList<>()).add(edge);
            adj.computeIfAbsent(edge.getTo(), k -> new ArrayList<>()).add(edge);
        }

        String startNode = nodes.get(0);
        visited.add(startNode);

        if (adj.containsKey(startNode)) {
            for (Edge edge : adj.get(startNode)) {
                pq.add(edge);
                counter.count++;
            }
        }

        while (!pq.isEmpty() && mstEdges.size() < nodes.size() - 1) {
            Edge minEdge = pq.poll();
            counter.count++;

            String fromNode = minEdge.getFrom();
            String toNode = minEdge.getTo();

            counter.count++;
            boolean fromVisited = visited.contains(fromNode);
            counter.count++;
            boolean toVisited = visited.contains(toNode);

            String nextNode;
            if (fromVisited && !toVisited) {
                nextNode = toNode;
            } else if (!fromVisited && toVisited) {
                nextNode = fromNode;
            } else {
                continue;
            }

            visited.add(nextNode);
            mstEdges.add(minEdge);
            totalCost += minEdge.getWeight();

            if (adj.containsKey(nextNode)) {
                for (Edge neighborEdge : adj.get(nextNode)) {
                    counter.count++;
                    if (!visited.contains(neighborEdge.getFrom()) || !visited.contains(neighborEdge.getTo())) {
                        pq.add(neighborEdge);
                        counter.count++;
                    }
                }
            }
        }

        return new AlgorithmStats(mstEdges, totalCost, counter.count, 0.0);
    }
}
