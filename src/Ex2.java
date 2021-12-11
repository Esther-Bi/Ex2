import api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        DirectedWeightedGraphAlgorithmsC graphAlgo = new DirectedWeightedGraphAlgorithmsC();
        graphAlgo.load(json_file);
        ans = graphAlgo.getGraph();
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        DirectedWeightedGraphAlgorithmsC graphAlgo = new DirectedWeightedGraphAlgorithmsC();
        graphAlgo.load(json_file);
        ans = graphAlgo;
        return ans;    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
        new GFrame((DirectedWeightedGraphAlgorithmsC) alg);

    }

    public static void main (String args[]){
        DirectedWeightedGraphAlgorithmsC algoGraph = new DirectedWeightedGraphAlgorithmsC();
        algoGraph.load("data/G3.json");//1000Nodes
        NodeData a;
        a = algoGraph.center();
        System.out.println(a.getKey());
//        List<NodeData> tsp = new ArrayList<>();
//        for (int j = 0; j < 17  ; j++) {
//            tsp.add(algoGraph.getGraph().getNode(j));
//        }
//        List<NodeData> test;
//        test = algoGraph.tsp(tsp);
//        String s="";
//        for(int i=0; i<test.size(); i++){
//            s= s+ test.get(i).getKey()+" , ";
//        }
//        System.out.println(s);

    }
}