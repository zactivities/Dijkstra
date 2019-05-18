/*
 * Zachary Bax
 * CPSC 5031, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Min heap. Elements are key:weight pairs. Ops are:
 *     1. construct from set of key:weight pairs (a dict), Θ(n)
 *     2. enqueue a new or existing key with its current weight, Θ(log n)
 *     3. dequeue the key with the minimum weight, Θ(log n)
 *     4. empty tells if the heap is empty, Θ(1)
 *
 * @author zacharybax
 */
public class Heap {

    public class KeyWeight{
        public String key;
        public Integer weight;
        public KeyWeight(){
            key = "";
            weight = 0;
        }
        public KeyWeight(String k, Integer w){
            key = k;
            weight = w;
        }
        @Override
        public String toString() {
            return "key = " + key +" weight = " + weight ;
        }
    }

    /**
     * Heap constructor
     */
    public Heap(){
        this.weights = new HashMap<>();
        this.place = new HashMap<>();
        this.heap = new ArrayList<>();
        this.last = 0;
    }

    /**
     * Heap constructor
     * @param initial key, weight pairs to construct heap
     */
    public Heap(HashMap<String, Integer> initial){
        this.weights = new HashMap<>();
        this.place = new HashMap<>();
        this.heap = new ArrayList<>();
        this.last = 0;

        if(!initial.isEmpty()){
            for(String k : initial.keySet()){
                this.heap.add(k);
                this.weights.put(k, initial.get(k));
                this.place.put(k, last);
                last++;
            }
        }
        heapConstruct();
    }

    /**
     * @return true if no more items in the heap
     */
    public boolean empty(){
        return heap.size() == 0;
    }

    /**
     * put key into heap with given weight. if key was
     * already present, this will change the weight
     * and repair the heap as necessary
     * @param k key
     * @param w weight
     */
    public void enqueue(String k, Integer w){
        Integer oldWeight, i;
        if(this.weights.containsKey(k)){
            oldWeight = this.weights.get(k);
            i = this.place.get(k);
        }
        else {
            this.heap.add(k);
            oldWeight = null;
            i = last();
            this.place.put(k, i);
        }
        this.weights.put(k, w);

        if(oldWeight == null || oldWeight > w)
            swapUp(i);
        else if (oldWeight < w)
            swapDown(i);
    }

    /**
     * remove minimum element and return its key and weight
     * @return key/weight pair
     */
    public KeyWeight dequeue(){
        int last = last();
        if(last < 0)
            return null;

        // retrieve the minimum element (at the root)
        String key = this.heap.get(0);
        Integer weight = this.weights.get(key);

        this.weights.remove(key);
        this.place.remove(key);

        // remove the last element and place it at the root,
        // then fix the heap
        if(last > 0){
            this.heap.set(0, this.heap.get(last));
            this.heap.remove(last);
            this.place.replace(this.heap.get(0), 0);
            swapDown(0);
        }
        else
            this.heap.remove(0);

       KeyWeight kw = new KeyWeight(key, weight);
       return kw;
    }

    /**
     * turn self into heap
     */
    private void heapConstruct(){
        Integer lastParent = parent(last());
        for(int i = lastParent; i > -1; i--)
            swapDown(i);
    }

    private Integer weight(Integer i){
        if(i > last())
            return null;
        return this.weights.get(this.heap.get(i));
    }

    private Integer parent(Integer i){
        return (i - 1) / 2;
    }

    private Integer leftChild(Integer p){
        return (2 * p) + 1;
    }

    private Integer rightChild(Integer p){
        return (2 * p) + 2;
    }

    private void swapUp(Integer i){
        if(i > 0){
            int p = parent(i);
            if(weight(i) < weight(p)){
                String temp = this.heap.get(i);
                this.heap.set(i, heap.get(p));
                this.heap.set(p, temp);
                this.place.replace(this.heap.get(i), i);
                this.place.replace(this.heap.get(p), p);
                swapUp(p);
            }
        }
    }

    private void swapDown(Integer p){
        Integer leftChild = leftChild(p);
        Integer rightChild = rightChild(p);
        Integer leftChildWeight = weight(leftChild);

        if(leftChildWeight == null)
            return;

        Integer rightChildWeight = weight(rightChild);

        if(rightChildWeight != null && rightChildWeight < leftChildWeight){
            leftChild = rightChild;
            leftChildWeight = rightChildWeight;
        }

        if(weight(p) > leftChildWeight){
            String temp = this.heap.get(leftChild);
            this.heap.set(leftChild, this.heap.get(p));
            this.heap.set(p, temp);
            this.place.replace(this.heap.get(leftChild), leftChild);
            this.place.replace(this.heap.get(p), p);
            swapDown(leftChild);
        }
    }

    public int last(){
        return heap.size() - 1;
    }

    private ArrayList<String> heap;
    public HashMap<String, Integer> weights;
    private HashMap<String, Integer> place;
    private int last;
}
