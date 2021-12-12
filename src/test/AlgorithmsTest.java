package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import api.*;
import org.junit.jupiter.api.Test;

public class AlgorithmsTest {
    private DirectedWeightedGraphAlgorithms algoGraphG1;
    private DirectedWeightedGraphAlgorithms algoGraphG2;
    private DirectedWeightedGraphAlgorithms algoGraphG3;
    private DirectedWeightedGraphAlgorithms algoGraph1000;
    private DirectedWeightedGraphAlgorithms algoGraph10000;
    AlgorithmsTest() {
        algoGraphG1 = new DirectedWeightedGraphAlgorithmsC();
        algoGraphG2 = new DirectedWeightedGraphAlgorithmsC();
        algoGraphG3 = new DirectedWeightedGraphAlgorithmsC();
        algoGraph1000 = new DirectedWeightedGraphAlgorithmsC();
        algoGraph10000 = new DirectedWeightedGraphAlgorithmsC();

        algoGraphG1.load("data/G1.json");
        algoGraphG2.load("data/G2.json");
        algoGraphG3.load("data/G3.json");
        algoGraph1000.load("data/1000Nodes.json");
        algoGraph10000.load("data/10000Nodes.json");

    }
    @Test
    void findCenter(){
        int center = algoGraphG1.center().getKey();
        assertEquals(8, center);
        center = algoGraphG2.center().getKey();
        assertEquals(0, center);
        center = algoGraphG3.center().getKey();
        assertEquals(40, center);
        center = algoGraph1000.center().getKey();
        assertEquals(362, center);
        center = algoGraph10000.center().getKey();
        assertEquals(3846, center);
    }
    @Test
    void shortestPathDist(){
        double dist = algoGraphG1.shortestPathDist(0,16);
        assertEquals(1.3118716362419698, dist);
        dist = algoGraphG2.shortestPathDist(0,29);
        assertEquals(7.819910602212574, dist);
        dist = algoGraphG3.shortestPathDist(0,47);
        assertEquals(17.146479706314675, dist);
        dist = algoGraph1000.shortestPathDist(0,999);
        assertEquals(672.951969037067, dist);
        dist = algoGraph10000.shortestPathDist(0,9999);
        assertEquals(1165.776078062164, dist);
    }
    @Test
    void shortestPath(){
        List<Integer> exp = new ArrayList();
        List<Integer> act = new ArrayList();
        List<NodeData> path = algoGraphG1.shortestPath(0,16);
        for (int i=0 ; i<path.size() ; i++){
            act.add(path.get(i).getKey());
        }
        exp.add(0); exp.add(16);
        assertEquals(exp, act);

        path = algoGraphG2.shortestPath(0,29);
        exp = new ArrayList();
        act = new ArrayList();
        for (int i=0 ; i<path.size() ; i++){
            act.add(path.get(i).getKey());
        }
        exp.add(0); exp.add(1); exp.add(2); exp.add(3); exp.add(4); exp.add(28); exp.add(29);
        assertEquals(exp, act);

        path = algoGraphG3.shortestPath(0,47);
        exp = new ArrayList();
        act = new ArrayList();
        for (int i=0 ; i<path.size() ; i++){
            act.add(path.get(i).getKey());
        }
        exp.add(0); exp.add(2); exp.add(3); exp.add(13); exp.add(14); exp.add(15); exp.add(39); exp.add(40);
        exp.add(41); exp.add(42); exp.add(43); exp.add(44); exp.add(46); exp.add(47);
        assertEquals(exp, act);


        path = algoGraph1000.shortestPath(0,999);
        exp = new ArrayList();
        act = new ArrayList();
        for (int i=0 ; i<path.size() ; i++){
            act.add(path.get(i).getKey());
        }
        exp.add(0); exp.add(769); exp.add(631); exp.add(195); exp.add(765); exp.add(661); exp.add(999);
        assertEquals(exp, act);

        path = algoGraph10000.shortestPath(0,9999);
        exp = new ArrayList();
        act = new ArrayList();
        for (int i=0 ; i<path.size() ; i++){
            act.add(path.get(i).getKey());
        }
        exp.add(0); exp.add(3744); exp.add(3730); exp.add(1877); exp.add(1339); exp.add(7476); exp.add(698);
        exp.add(6391); exp.add(9495); exp.add(2605); exp.add(9999);
        assertEquals(exp, act);
    }
    @Test
    void tsp(){
        List<NodeData> tsp = new ArrayList<>();
        for (int j = 0; j < 17  ; j++) {
            tsp.add(algoGraphG1.getGraph().getNode(j));
        }
        List<NodeData> test;
        test = algoGraphG1.tsp(tsp);
        List<Integer> exp = new ArrayList<>();
        List<Integer> act = new ArrayList<>();
        for (int j = 0; j < test.size()  ; j++) {
            act.add(test.get(j).getKey());
        }
        exp.add(0); exp.add(1); exp.add(16); exp.add(15); exp.add(2); exp.add(3); exp.add(14);
        exp.add(6); exp.add(4); exp.add(13); exp.add(7); exp.add(5); exp.add(8); exp.add(12); exp.add(11); exp.add(9); exp.add(10);
        assertEquals(exp, act);
    }
    @Test
    void isConnected(){
        boolean connect = algoGraphG1.isConnected();
        assertEquals(true, connect);
        connect = algoGraphG2.isConnected();
        assertEquals(true, connect);
        algoGraphG2.getGraph().removeEdge(16,0);
        algoGraphG2.getGraph().removeEdge(0,16);
        algoGraphG2.getGraph().removeEdge(10,11);
        algoGraphG2.getGraph().removeEdge(11,10);
        connect = algoGraphG2.isConnected();
        assertEquals(false, connect);
        connect = algoGraphG3.isConnected();
        assertEquals(true, connect);
        connect = algoGraph1000.isConnected();
        assertEquals(true, connect);
        connect = algoGraph10000.isConnected();
        assertEquals(true, connect);
    }
}

