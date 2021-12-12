package api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class DirectedWeightedGraphAlgorithmsC implements DirectedWeightedGraphAlgorithms{

    DirectedWeightedGraphC graph;

    public DirectedWeightedGraphAlgorithmsC(DirectedWeightedGraphC graph) {
        this.graph = graph;
    }

    public DirectedWeightedGraphAlgorithmsC() {
        this.graph = null;
    }


    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (DirectedWeightedGraphC) g;
    }


    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy= new DirectedWeightedGraphC((DirectedWeightedGraphC) this.graph);
        return copy;
    }
    @Override
    public boolean isConnected() {
        HashMap<Integer , Boolean> visited= new HashMap<>();
        int k = 0;
        for (Integer key : this.graph.getNodeCollection().keySet()) {
            visited.put(key,false);
            k = key;
        }
        BFS(k,visited);

        for (Integer i : visited.keySet()) {
            if (visited.get(i) == false){
                return false;
            }
        }
        // Create a reversed graph
        DirectedWeightedGraphC gr = (DirectedWeightedGraphC) getTranspose();
        DirectedWeightedGraphAlgorithmsC graphTranspose = new DirectedWeightedGraphAlgorithmsC(gr);
        // Mark all the vertices as not visited (For
        HashMap<Integer , Boolean> visited2= new HashMap<>();
        for (Integer i : visited.keySet()) {
            visited2.put(i,visited.get(i));
        }
        for (Integer i : visited2.keySet()) {
            visited.remove(i);
            visited.put(i,false);
        }
        visited=visited2;

        graphTranspose.BFS(k, visited);

        for (Integer i : visited.keySet()) {
            if (visited.get(i) == false){
                return false;
            }
        }
        return true;
    }

    private void BFS(int nodeKey ,HashMap<Integer , Boolean> visited) {

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited.remove(nodeKey);
        visited.put(nodeKey,true);
        queue.add(nodeKey);

        while (queue.size() != 0) {
            nodeKey = queue.poll();

            if(this.graph.getEdgeCollection().get(nodeKey)!= null) {
                for (Integer n : this.graph.getEdgeCollection().get(nodeKey).keySet()) {
                    if (!visited.get(n)) {
                        visited.remove(n);
                        visited.put(n, true);
                        queue.add(n);
                    }
                }
            }
        }
    }

    // Function that returns transpose of this graph
    private DirectedWeightedGraph getTranspose(){
        DirectedWeightedGraph g = new DirectedWeightedGraphC();
        for (Integer key : this.graph.getNodeCollection().keySet()) {
            NodeDataC node = new NodeDataC((NodeDataC) this.graph.getNodeCollection().get(key));
            g.addNode(node);
        }
        for (Integer keyN : this.graph.getEdgeCollection().keySet()) {
            for (Integer keyE : this.graph.getEdgeCollection().get(keyN).keySet()) {
                g.connect(keyE,keyN,this.graph.getEdgeCollection().get(keyN).get(keyE).getWeight());
            }
        }
        return g;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return (double) Dijkstra(src,dest)[0];
    }

    public int minWeight(HashMap<Integer,NodeData> Q){
        NodeData min = null;
        for (NodeData node : Q.values()){
            if (min==null) min = node;
            if (node.getWeight()<min.getWeight()){
                min = node;
            }
        }
        return min.getKey();
    }

    public void  Dijkstra2(int src){
        HashMap<Integer,NodeData> edges = new HashMap<>();
        PriorityQueue<NodeData> Q = new PriorityQueue<>(Comparator.comparing(NodeData::getWeight));
        for (Integer key : this.graph.getNodeCollection().keySet()){
            this.graph.getNodeCollection().get(key).setWeight(Double.MAX_VALUE);
            edges.put(key,this.graph.getNodeCollection().get(key));
            Q.add(this.graph.getNodeCollection().get(key));
        }
        this.graph.getNodeCollection().get(src).setWeight(0);
        Q.remove(this.graph.getNodeCollection().get(src));
        Q.add(this.graph.getNodeCollection().get(src));

        while (!Q.isEmpty()){
            NodeData u = Q.poll();
            edges.remove(u.getKey());
            if (this.graph.getEdgeCollection().get(u.getKey())!= null){
                for (EdgeData e : this.graph.getEdgeCollection().get(u.getKey()).values()){
                    if (edges.get(e.getDest())!= null) {
                        relax2(e ,u , edges);
                        Q.remove(edges.get(e.getDest()));
                        Q.add(edges.get(e.getDest()));
                    }
                }
            }
        }

    }

    public void relax2(EdgeData e , NodeData u , HashMap<Integer,NodeData> edges) {
        if (edges.get(e.getDest()).getWeight() > u.getWeight()+e.getWeight()) {
            edges.get(e.getDest()).setWeight(u.getWeight()+e.getWeight());
        }
    }

    public Object[] Dijkstra(int src, int dest){
        Object[] o= new Object[2];
        HashMap<Integer,Integer> p = new HashMap<>();
        HashMap<Integer,NodeData> Q = new HashMap<>();
        for (Integer key : this.graph.getNodeCollection().keySet()){
            p.put(key,null);
            this.graph.getNodeCollection().get(key).setWeight(Double.MAX_VALUE);
            Q.put(key,this.graph.getNodeCollection().get(key));
        }
        this.graph.getNodeCollection().get(src).setWeight(0);

        while (!Q.isEmpty()){
            NodeData u = this.graph.getNodeCollection().get(minWeight(Q));
            Q.remove(minWeight(Q));
            if (u.getKey()==dest){
                o[0]= this.graph.getNodeCollection().get(dest).getWeight();
                o[1]= p;
                return o;
            }
            if (this.graph.getEdgeCollection().get(u.getKey())!= null){
                for (EdgeData e : this.graph.getEdgeCollection().get(u.getKey()).values()){
                    if (Q.get(e.getDest())!= null) {
                        relax(e, Q, p ,u);
                    }
                }
            }

        }
        o[0]= 0.0;
        o[1]= p;
        return o;    }

    public void relax(EdgeData e , HashMap<Integer,NodeData> Q , HashMap<Integer,Integer> p , NodeData u) {
        if (Q.get(e.getDest()).getWeight() > u.getWeight()+e.getWeight()) {
            Q.get(e.getDest()).setWeight(u.getWeight()+e.getWeight());
            p.remove(e.getDest());
            p.put(e.getDest() , e.getSrc());
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        HashMap<Integer,Integer> p = (HashMap<Integer, Integer>) Dijkstra(src,dest)[1];
        List<NodeData> ans = new ArrayList<>();
        NodeData node = this.graph.getNodeCollection().get(dest);
        ans.add(0,node);
        while (ans.get(0)!=this.graph.getNodeCollection().get(src)) {
            node = this.graph.getNodeCollection().get(p.get(ans.get(0).getKey()));
            ans.add(0,node);
        }
        return ans;
    }

    @Override
    public NodeData center() {
        if (!this.isConnected()){
            return null;
        }
        double min = Double.MAX_VALUE;
        NodeData ans = null;
        for (Integer key : this.graph.getNodeCollection().keySet()) {
            double center = centerOfNode(this.graph.getNodeCollection().get(key));
            if (center<min){
                min = center;
                ans = this.graph.getNodeCollection().get(key);
            }
        }
        return ans;
    }

    private double centerOfNode(NodeData node){
        double max = Double.MIN_VALUE;
        Dijkstra2(node.getKey());
        for (Integer key : this.graph.getNodeCollection().keySet()){
            if (key!=node.getKey()){
                double temp = this.graph.getNodeCollection().get(key).getWeight();
                if (temp>=max){
                    max = temp;
                }
            }
        }
        return max;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> ans= new ArrayList<>();
        boolean iWasChanged = false;
        int i=0;
        while (!cities.isEmpty()){
            double shortPath= Double.POSITIVE_INFINITY;
            int next=-1;
            for (int j = 0; j < cities.size(); j++) {
                if(i!=j) {
                    if (!iWasChanged){
                        Dijkstra2(cities.get(i).getKey());
                        iWasChanged = false;
                    }
                    double dist = cities.get(j).getWeight();
                    if (shortPath >= dist) {
                        shortPath = dist;
                        next = j;
                    }
                }
            }
            ans.add(cities.get(i));
            cities.remove(i);
            if (i>next)
                i=next;
            else {
                i = next - 1;
                iWasChanged=true;
            }
        }

        return ans;
    }

    @Override
    public boolean save(String file) {
        try {
            FileWriter json_file = new FileWriter(file);
            BufferedWriter b = new BufferedWriter(json_file);
            b.write(this.graph.toString());
            b.close();
            json_file.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        this.graph.setMC(0);
        return true;
    }

    @Override
    public boolean load(String file) {
        try {
            Object ob = new JSONParser().parse(new FileReader(file)); //"src/data/G1.json"
            if (ob == null)
                throw new Exception("Error");
            JSONObject js = (JSONObject) ob;
            JSONArray edgesArr = (JSONArray) js.get("Edges");
            JSONArray nodesArr = (JSONArray) js.get("Nodes");
            Iterator itEdge = edgesArr.iterator();
            Iterator itNode = nodesArr.iterator();

            DirectedWeightedGraphC graph= new DirectedWeightedGraphC();
            Map mapNode;

            while (itNode.hasNext()) {
                mapNode = (Map) itNode.next();
                int id = Integer.parseInt(Objects.toString(mapNode.get("id")));
                String[] pos = ((String) mapNode.get("pos")).split(",");
                double x = Double.parseDouble(pos[0]);
                double y = Double.parseDouble(pos[1]);
                double z = Double.parseDouble(pos[2]);
                NodeDataC node = new NodeDataC(id,new GeoLocationC(x,y,z),0,"",0);
                graph.addNode(node);
            }

            Map mapEdge;
            while (itEdge.hasNext()) {
                mapEdge = (Map) itEdge.next();
                int src = Integer.parseInt(Objects.toString(mapEdge.get("src")));
                int dest = Integer.parseInt(Objects.toString(mapEdge.get("dest")));
                double w = Double.parseDouble(Objects.toString(mapEdge.get("w")));
                graph.connect(src,dest,w);
            }
            this.graph= graph;

        } catch (Exception e) {

            System.out.println(e);
            return false;

        }
        this.graph.setMC(0);
        return true;
    }
}