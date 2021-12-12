package test;
import api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {
    private DirectedWeightedGraphAlgorithms algoGraph;
    GraphTest() {
        algoGraph = new DirectedWeightedGraphAlgorithmsC();
        algoGraph.load("data/G3.json");
    }
    @Test
    void getNode() {
        GeoLocationC p = new GeoLocationC(31,400,0.0);
        NodeData n = new NodeDataC(200,p,0,"",0);
        algoGraph.getGraph().addNode(n);
        assertEquals(n.getKey(),algoGraph.getGraph().getNode(200).getKey());
        assertEquals(n.getLocation().x(),algoGraph.getGraph().getNode(200).getLocation().x());
    }
    @Test
    void getEdge() {
        assertEquals(1.1502834534021935,algoGraph.getGraph().getEdge(10,11).getWeight());
        assertEquals(null,algoGraph.getGraph().getEdge(16,14));
    }

    @Test
    void addNode() {
        GeoLocationC p = new GeoLocationC(31,400,0.0);
        NodeData n = new NodeDataC(200,p,0,"",0);
        algoGraph.getGraph().addNode(n);
        assertEquals(n.getLocation().x(),algoGraph.getGraph().getNode(200).getLocation().x());
    }

    @Test
    void connect() {
        algoGraph.getGraph().connect(16,14,8);
        assertEquals(16,algoGraph.getGraph().getEdge(16,14).getSrc());
    }

    @Test
    void removeNode() {
        assertEquals(5, algoGraph.getGraph().removeNode(5).getKey());
        assertEquals(35.210540382566585,algoGraph.getGraph().removeNode(8).getLocation().x());
    }
    @Test
    void removeEdge() {
        assertEquals(5,algoGraph.getGraph().removeEdge(4,5).getDest());
        assertEquals(null ,algoGraph.getGraph().removeEdge(4,5));
    }

    @Test
    void nodeSize() {
        assertEquals(48,algoGraph.getGraph().nodeSize());
        algoGraph.getGraph().removeNode(10);
        assertEquals(47,algoGraph.getGraph().nodeSize());
    }

    @Test
    void edgeSize() {
        assertEquals(166,algoGraph.getGraph().edgeSize());
    }
}

