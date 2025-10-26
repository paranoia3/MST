package models;

import java.util.List;

public class AlgorithmStats {
    private List<Edge> mstEdges;
    private int totalCost;
    private long operationsCount;
    private double executionTimeMs;

    public AlgorithmStats() {}

    public AlgorithmStats(List<Edge> mstEdges, int totalCost, long operationsCount, double executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }

    public List<Edge> getMstEdges() { return mstEdges; }
    public void setMstEdges(List<Edge> mstEdges) { this.mstEdges = mstEdges; }
    public int getTotalCost() { return totalCost; }
    public void setTotalCost(int totalCost) { this.totalCost = totalCost; }
    public long getOperationsCount() { return operationsCount; }
    public void setOperationsCount(long operationsCount) { this.operationsCount = operationsCount; }
    public double getExecutionTimeMs() { return executionTimeMs; }
    public void setExecutionTimeMs(double executionTimeMs) { this.executionTimeMs = executionTimeMs; }
}