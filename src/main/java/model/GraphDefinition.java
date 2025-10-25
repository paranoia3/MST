package model;

import java.util.List;

public class GraphDefinition {
    private int id;
    private List<String> nodes;
    private List<Edge> edges;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public List<String> getNodes() { return nodes; }
    public void setNodes(List<String> nodes) { this.nodes = nodes; }
    public List<Edge> getEdges() { return edges; }
    public void setEdges(List<Edge> edges) { this.edges = edges; }
}