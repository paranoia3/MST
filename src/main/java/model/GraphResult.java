package model;

public class GraphResult {
    private int graphId;
    private InputStats inputStats;
    private AlgorithmStats prim;

    public int getGraphId() { return graphId; }
    public void setGraphId(int graphId) { this.graphId = graphId; }
    public InputStats getInputStats() { return inputStats; }
    public void setInputStats(InputStats inputStats) { this.inputStats = inputStats; }
    public AlgorithmStats getPrim() { return prim; }
    public void setPrim(AlgorithmStats prim) { this.prim = prim; }
}