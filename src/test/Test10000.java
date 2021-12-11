package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import api.*;
//import codes.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class Test10000 {
    private DirectedWeightedGraphAlgorithms algoGraph;
    Test10000() {
        //******** important for the tests work *********// |
        //                                                  V
        //graph = Ex2.getGrapg("data/G3.json"); // enter here the path for G1 json file
        algoGraph = new DirectedWeightedGraphAlgorithmsC();
        algoGraph.load("data/G3.json");
//        algoGraph = Ex2.getGrapgAlgo("data/10000Nodes.json");
        //algoGraph.init(graph);
    }
    @Test
    void findCenter(){
        int center = algoGraph.center().getKey();
        assertEquals(40, center);
    }
    @Test
    void shortestPathDist(){
        double dist = algoGraph.shortestPathDist(0,9999);
        assertEquals(dist, dist);
    }
    @Test
    void shortestPath(){
        List path = algoGraph.shortestPath(0,9999);
        assertEquals(path, path);
    }
    @Test
    void tsp(){
        List<NodeData> tsp = new ArrayList<>();
        for (int j = 0; j < 10000  ; j++) {
            tsp.add(algoGraph.getGraph().getNode(j));
        }
        List<NodeData> test;
        test = algoGraph.tsp(tsp);
        assertEquals(test, test);
    }
    @Test
    void isConnected(){
        boolean connect = algoGraph.isConnected();
        assertEquals(true, connect);
    }


}

