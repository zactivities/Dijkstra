/*
 * Zachary Bax
 * CPSC 5031, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Dijkstra's shortest path algorithm
 *
 * @author zachary bax
 */
public class Dijkstra {

    public static ArrayList<HashMap> Dijkstra(Graph g, String start){

        // # n is |V|, number of vertices in the graph
        int n = g.vertices().size();
        HashMap<String, String> path = new HashMap<>();
        HashMap<String, Integer> shortest = new HashMap<>();
        ArrayList<String> found = new ArrayList<>();
        ArrayList<String> vertices = g.vertices();

        for(int i = 0; i < n; i++){
            shortest.put((String)g.vertices().get(i), (int) Double.POSITIVE_INFINITY);
        }

        shortest.put(start, 0);
        Heap unsolved = new Heap(shortest);

        // get the shortest path to every other vertex
        while(found.size() < n){
            // find closest unsolved vertex
            String closest = unsolved.dequeue().key;
            // closest vertex is now solved, we know that no other shorter path exists
            found.add(closest);

            ArrayList<Graph.Edge> neighbor = g.outgoing(closest);

            // update paths to other vertices via closest
            for(int i = 0; i < neighbor.size(); i++){
                String k = neighbor.get(i).vertexTo;
                if(!found.contains(k)){
                    int newPathToK = shortest.get(closest) + neighbor.get(i).weight;
                    if(newPathToK < unsolved.weights.get(k)){
                        // fix weight in priority queue
                        unsolved.enqueue(k, newPathToK);
                        shortest.put(k, newPathToK);
                        path.put(k, closest);

                    }
                }
            }
        }

        // report results
        HashMap<String, String> paths = new HashMap<>();

        for(String v : vertices) {

            if(!(v.equals(start))){

                ArrayList<String> thisPath = new ArrayList<>();
                String w = v;

                while(!w.equals(start)){
                    thisPath.add(w);
                    w = path.get(w);
                }

                thisPath.add(start);
                Collections.reverse(thisPath);
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < thisPath.size(); i++)
                    sb.append(thisPath.get(i));
                paths.put(v, sb.toString());
            }
        }
        shortest.remove(start);

        ArrayList<HashMap> returns = new ArrayList<>();
        returns.add(0,shortest);
        returns.add(1, paths);

        return returns;
    }
}
