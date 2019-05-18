/*
 * Zachary Bax
 * CPSC 5031, Seattle University
 * This is free and unencumbered software released into the public domain.
 */


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Driver is used for testing the Graph, Dijkstra and Heap classes.
 */
public class Driver {
    public static void main(String[] args){
        System.out.println("\n\n////////////////////////////////////////////////////");
        System.out.println("\t\tCreating Heap");
        System.out.println("////////////////////////////////////////////////////\n");

        HashMap<String, Integer> hm = new HashMap<>();
        Heap.KeyWeight kw ;

        hm.put("a", 5);
        hm.put("b", 10);
        hm.put("c", -2);

        Heap heap = new Heap(hm);

        heap.enqueue("d", 11);
        heap.enqueue("e", -1);

        System.out.println("COMPLETE\n");

        System.out.println("\n\n////////////////////////////////////////////////////");
        System.out.println("\t\tTesting Heap");
        System.out.println("////////////////////////////////////////////////////\n");


        System.out.println(heap.empty() + " expected false");
        kw = heap.dequeue();
        System.out.println(kw.key + " " + kw.weight + " expect c -2");
        kw = heap.dequeue();
        System.out.println(kw.key + " " + kw.weight + " expect e -1");
        kw = heap.dequeue();
        System.out.println(kw.key + " " + kw.weight + " expect a 5");
        kw = heap.dequeue();
        System.out.println(kw.key + " " + kw.weight + " expect b 10");
        kw = heap.dequeue();
        System.out.println(kw.key + " " + kw.weight + " expect d 11");
        System.out.println(heap.empty() + " expected true");



        System.out.println("\n\n////////////////////////////////////////////////////");
        System.out.println("\t\tCreating 1st Graph");
        System.out.println("////////////////////////////////////////////////////\n");

        Graph g1 = new Graph();

        g1.addEdge("a", "b", 2);
        g1.addEdge("a", "c", 6);
        g1.addEdge("a", "d", 1);
        g1.addEdge("b", "a", 2);
        g1.addEdge("b", "c", 3);
        g1.addEdge("b", "d", 2);
        g1.addEdge("c", "a", 6);
        g1.addEdge("c", "b", 3);
        g1.addEdge("c", "d", 3);
        g1.addEdge("c", "e", 1);
        g1.addEdge("c", "f", 5);
        g1.addEdge("d", "a", 1);
        g1.addEdge("d", "b", 2);
        g1.addEdge("d", "c", 3);
        g1.addEdge("d", "e", 1);
        g1.addEdge("e", "c", 1);
        g1.addEdge("e", "d", 1);
        g1.addEdge("e", "f", 2);
        g1.addEdge("f", "c", 5);
        g1.addEdge("f", "e", 2);

        System.out.println("COMPLETE");

        ArrayList<String> v1 = g1.vertices();

        System.out.println("\n////////////////////////////////////////////////////");
        System.out.println("\t\tPrinting Vertices and Edges from 1st Graph");
        System.out.println("////////////////////////////////////////////////////\n");

        printGraph(v1, g1);

        System.out.println("\n\n////////////////////////////////////////////////////");
        System.out.println("\t\tTesting Dijkstra on 1st Graph");
        System.out.println("////////////////////////////////////////////////////\n");

        String start1 = "a";

        printDijkstra(g1, start1);


        System.out.println("\n\n////////////////////////////////////////////////////");
        System.out.println("\t\tCreating 2nd Graph");
        System.out.println("////////////////////////////////////////////////////\n");

        Graph g2 = new Graph();

        g2.addEdge("Seattle", "Kenmore", 14);
        g2.addEdge("Seattle", "Kirkland", 12);
        g2.addEdge("Seattle", "Bellevue", 10);
        g2.addEdge("Seattle", "Tukwila", 11);
        g2.addEdge("Kenmore", "Seattle", 14);
        g2.addEdge("Kenmore", "Bothell", 3);
        g2.addEdge("Bothell", "Kenmore", 3);
        g2.addEdge("Bothell", "Kirkland", 8);
        g2.addEdge("Kirkland", "Seattle", 12);
        g2.addEdge("Kirkland", "Bothell", 8);
        g2.addEdge("Kirkland", "Bellevue", 5);
        g2.addEdge("Bellevue", "Seattle", 10);
        g2.addEdge("Bellevue", "Kirkland", 5);
        g2.addEdge("Bellevue", "Renton", 11);
        g2.addEdge("Renton", "Bellevue", 11);
        g2.addEdge("Renton", "Tukwila", 4);
        g2.addEdge("Tukwila", "Seattle", 11);
        g2.addEdge("Tukwila", "Renton", 4);

        System.out.println("COMPLETE");

        ArrayList<String> v2 = g2.vertices();

        System.out.println("\n////////////////////////////////////////////////////");
        System.out.println("\t\tPrinting Vertices and Edges from 2nd Graph");
        System.out.println("////////////////////////////////////////////////////\n");

        printGraph(v2, g2);

        System.out.println("\n\n////////////////////////////////////////////////////");
        System.out.println("\t\tTesting Dijkstra on 2nd Graph");
        System.out.println("////////////////////////////////////////////////////\n\n");

        String start2 = "Seattle";

        printDijkstra(g2, start2);
    }

    public static void printGraph(ArrayList<String> v, Graph g){
        for(int i = 0; i < v.size(); i++){
            String ver = v.get(i);
            System.out.print("\nVertex: " + ver);

            ArrayList<Graph.Edge> outEdges = g.outgoing(ver);

            for(int j = 0; j < outEdges.size(); j++)
                System.out.print("\n\t" + outEdges.get(j).vertexFrom + " -> "
                        + outEdges.get(j).vertexTo + "  " + outEdges.get(j).weight);
        }
    }

    public static void printDijkstra(Graph g, String start){
        ArrayList<HashMap> result = Dijkstra.Dijkstra(g, start);
        HashMap<String, Integer> shortest = result.get(0);
        HashMap<String, ArrayList<String>> paths = result.get(1);

        System.out.println("Starting from " + start + "\n");
        for(String key: shortest.keySet()){
            System.out.print(key + " : " + shortest.get(key) + "\t");
        }

        System.out.println();

        for(String key: paths.keySet()){
            System.out.print(key + " : " + paths.get(key) + " ");
        }
    }
}
