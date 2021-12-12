package api;

import java.util.HashMap;
import java.util.Iterator;

public class DirectedWeightedGraphC implements DirectedWeightedGraph{

    private HashMap<Integer,NodeData> NodeCollection;
    private HashMap<Integer,HashMap<Integer,EdgeData>> EdgeCollection;
    private HashMap<Integer,HashMap<Integer,EdgeData>> EdgeCollectionFromDest;
    private int MC;

    public DirectedWeightedGraphC() {
        this. NodeCollection = new HashMap<>();
        this.EdgeCollection = new HashMap<>();
        this.EdgeCollectionFromDest = new HashMap<>();
        this.MC=0;
    }

    public DirectedWeightedGraphC(DirectedWeightedGraphC other) { // deep copy

        this.NodeCollection = new HashMap<>();
        this.EdgeCollection = new HashMap<>();
        this.EdgeCollectionFromDest = new HashMap<>();

        for (Integer key : other.NodeCollection.keySet()) {
            NodeData node= new NodeDataC((NodeDataC) other.NodeCollection.get(key));
            this.NodeCollection.put(key,node);
        }
        for (Integer keys : other.EdgeCollection.keySet()) {
            for (Integer keyD : other.EdgeCollection.get(keys).keySet()) {
                EdgeData edgeData= new EdgeDataC((EdgeDataC) other.EdgeCollection.get(keys).get(keyD));
                this.EdgeCollection.get(keys).put(keyD,edgeData);
            }
        }
        for (Integer keys : other.EdgeCollectionFromDest.keySet()) {
            for (Integer keyD : other.EdgeCollectionFromDest.get(keys).keySet()) {
                EdgeData edgeData= new EdgeDataC((EdgeDataC) other.EdgeCollectionFromDest.get(keys).get(keyD));
                this.EdgeCollectionFromDest.get(keys).put(keyD,edgeData);
            }
        }
        this.MC=0;


    }

    public HashMap<Integer, NodeData> getNodeCollection() {
        return NodeCollection;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getEdgeCollection() {
        return EdgeCollection;
    }

    @Override
    public NodeData getNode(int key) {
        return this.NodeCollection.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return this.EdgeCollection.get(src).get(dest);
    }

    public void setMC(int MC) {
        this.MC = MC;
    }

    @Override
    public void addNode(NodeData n) {
        this.NodeCollection.put(n.getKey() , n);
        this.MC++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        String info = "";
        int tag = 0;
        EdgeData edge = new EdgeDataC(src,dest,w,info,tag);
        if (this.EdgeCollection.get(src) != null){
            this.EdgeCollection.get(src).put(dest , edge);
        }
        else{
            HashMap<Integer,EdgeData> edges = new HashMap<>();
            edges.put(dest , edge);
            this.EdgeCollection.put(src , edges);
        }
        if (this.EdgeCollectionFromDest.get(dest) != null){
            this.EdgeCollectionFromDest.get(dest).put(src , edge);
        }
        else{
            HashMap<Integer,EdgeData> edges = new HashMap<>();
            edges.put(src , edge);
            this.EdgeCollectionFromDest.put(dest , edges);
        }
        this.MC++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        if (this.MC>0){
            throw new RuntimeException();
        }
        Iterator NodeIterator = this.NodeCollection.entrySet().iterator();
        return NodeIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        if (this.MC>0){
            throw new RuntimeException();
        }
        Iterator NodeIterator = this.EdgeCollection.entrySet().iterator();
        return NodeIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (this.MC>0){
            throw new RuntimeException();
        }
        Iterator NodeIterator = this.EdgeCollection.get(node_id).entrySet().iterator();
        return NodeIterator;
    }

    @Override
    public NodeData removeNode(int key) {
        for (Integer dest : this.EdgeCollectionFromDest.get(key).keySet()) {
            this.EdgeCollection.get(dest).remove(key);

        }
        for (Integer src : this.EdgeCollection.get(key).keySet()) {
            this.EdgeCollectionFromDest.get(src).remove(key);

        }
        this.MC++;
        this.EdgeCollection.remove(key);
        this.EdgeCollectionFromDest.remove(key);
        return this.NodeCollection.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        this.EdgeCollectionFromDest.get(dest).remove(src);
        this.MC++;
        return this.EdgeCollection.get(src).remove(dest);

    }

    @Override
    public int nodeSize() {
        return this.NodeCollection.size();
    }

    @Override
    public int edgeSize() {
        int size = 0;
        for (HashMap<Integer, EdgeData> value : this.EdgeCollection.values()) {
            size += value.size();
        }
        return size;
    }

    @Override
    public int getMC() {
        return this.MC;
    }

    public String toString(){

        String answer = "{\n" + "\"Edges\": [\n";


        for (Integer keyS : this.EdgeCollection.keySet()){
            for (Integer keyD : this.EdgeCollection.get(keyS).keySet()){
                answer = answer + "{\n" + "\"src\": " + keyS + ",\n\"w\": " + this.EdgeCollection.get(keyS).get(keyD).getWeight() + ",\n\"dest\": " + keyD + "\n},\n";
            }
        }
        answer = answer.substring(0,answer.length()-2);
        answer = answer +"],\n" + "\"Nodes\":[";

        for (Integer key : this.NodeCollection.keySet()){
            answer = answer + "{\n" + "\"pos\": " + this.NodeCollection.get(key).getLocation().toString() + ",\n\"id\": " + key + "\n},\n";
        }

        answer = answer.substring(0,answer.length()-2);
        answer = answer + "]\n" + '}';
        return answer;
    }

    public double minXValue(){
        double min = Double.MAX_VALUE;
        for (NodeData node : this.NodeCollection.values()) {
            if (node.getLocation().x()<min){
                min = node.getLocation().x();
            }
        }
        return min;
    }
    public double minYValue(){
        double min = Double.MAX_VALUE;
        for (NodeData node : this.NodeCollection.values()) {
            if (node.getLocation().y()<min){
                min = node.getLocation().y();
            }
        }
        return min;
    }

    public double maxXValue(){
        double max = Double.MIN_VALUE;
        for (NodeData node : this.NodeCollection.values()) {
            if (node.getLocation().x()>max){
                max = node.getLocation().x();
            }
        }
        return max;
    }
    public double maxYValue(){
        double max = Double.MIN_VALUE;
        for (NodeData node : this.NodeCollection.values()) {
            if (node.getLocation().y()>max){
                max = node.getLocation().y();
            }
        }
        return max;
    }

}