/*
 * Zachary Bax
 * CPSC 5031, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    public class Edge{
        public String vertexFrom, vertexTo;
        public int weight;

        public Edge(String vertexFrom, String vertexTo, int weight){
            this.vertexFrom = vertexFrom;
            this.vertexTo = vertexTo;
            this.weight = weight;
        }
    }

    public Graph(){
        VertexList = new ArrayList<>();
        EdgeList = new HashMap<>();
    }

    public void addEdge(String vertexFrom, String vertexTo, int weight){
        Edge e = new Edge(vertexFrom, vertexTo, weight);
        ArrayList<Edge> eList = new ArrayList<>();

        if(EdgeList.containsKey(vertexFrom))
            EdgeList.get(vertexFrom).add(e);
        else {
            eList.add(e);
            EdgeList.put(vertexFrom, eList);
            VertexList.add(vertexFrom);
        }
    }

    public void addEdge(String vertexFrom, String vertexTo){
        Edge e = new Edge(vertexFrom, vertexTo, 1);
        ArrayList<Edge> eList = new ArrayList<>();

        if(EdgeList.containsKey(vertexFrom))
            EdgeList.get(vertexFrom).add(e);
        else {
            eList.add(e);
            EdgeList.put(vertexFrom, eList);
            VertexList.add(vertexFrom);
        }
    }

    public ArrayList outgoing (String vertex){
        return EdgeList.get(vertex);
    }

    public ArrayList vertices(){
        return VertexList;
    }

    private ArrayList<String> VertexList;
    private HashMap<String, ArrayList<Edge>> EdgeList;

}
