package org.example;

import algorithm.KruskalAlgorithm;
import algorithm.PrimAlgorithm;
import model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            File inputFile = new File("ass_3_input.json");
            GraphInputData inputData = mapper.readValue(inputFile, GraphInputData.class);

            GraphOutputData outputData = new GraphOutputData();
            outputData.setResults(new ArrayList<>());

            System.out.println("Number of graphs: " + inputData.getGraphs().size());

            for (GraphDefinition graph : inputData.getGraphs()) {
                System.out.println("Graph's ID: " + graph.getId());
                GraphResult result = new GraphResult();
                result.setGraphId(graph.getId());
                result.setInputStats(new InputStats(graph.getNodes().size(), graph.getEdges().size()));

                // Prim's algorithm
                System.out.println("Prim's algorithm");
                PrimAlgorithm prim = new PrimAlgorithm();
                long primStart = System.nanoTime();
                AlgorithmStats primStats = prim.run(graph.getNodes(), graph.getEdges());
                long primEnd = System.nanoTime();
                primStats.setExecutionTimeMs((primEnd - primStart) / 1_000_000.0);
                result.setPrim(primStats);
                System.out.println("Prim's algorithm is done. Cost: " + primStats.getTotalCost());

                // Kruskal's algorithm
                System.out.println("Kruskal's algorithm");
                KruskalAlgorithm kruskal = new KruskalAlgorithm();
                long kruskalStart = System.nanoTime();
                AlgorithmStats kruskalStats = kruskal.run(graph.getNodes(), graph.getEdges());
                long kruskalEnd = System.nanoTime();
                kruskalStats.setExecutionTimeMs((kruskalEnd - kruskalStart) / 1_000_000.0);
                result.setKruskal(kruskalStats);
                System.out.println("Kruskal's algorithm is done. Cost: " + kruskalStats.getTotalCost());

                outputData.getResults().add(result);
            }

            File outputFile = new File("ass_3_output.json");
            mapper.writeValue(outputFile, outputData);

            System.out.println("\nResults are saved in " + outputFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}