import api.DirectedWeightedGraphAlgorithmsC;
import api.NodeData;
import api.NodeDataC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import static gui.buttons.MenuBarExample.scaleImageIcon;

public class GFrame extends JFrame implements KeyListener , ActionListener {
    GraphPanel panel;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu algoMenu;
    JMenu editMenu;

    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;


    // algorithms
    JMenuItem tspItem;
    JMenuItem centerItem;
    JMenuItem shortestPathItem;
    JMenuItem connectedItem;
    JMenuItem shortestPathDistItem;

    // edit graph
    JMenuItem addNodeItem;
    JMenuItem addEdgeItem;
    JMenuItem removeNodeItem;
    JMenuItem removeEdgeItem;

    DirectedWeightedGraphAlgorithmsC algo = new DirectedWeightedGraphAlgorithmsC();
    JFileChooser j = new JFileChooser();


    public GFrame(DirectedWeightedGraphAlgorithmsC algo) {
        super();
        this.algo=algo;
        panel = new GraphPanel();
        panel.set(algo);
        this.add(panel);
        this.addKeyListener(this);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//        this.setSize(500,500);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        algoMenu = new JMenu("Algorithms");
        editMenu = new JMenu("Edit Graph");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        tspItem = new JMenuItem("TSP");
        centerItem = new JMenuItem("Center");
        shortestPathItem = new JMenuItem("Shortest Path");
        connectedItem = new JMenuItem("Is Connected");
        shortestPathDistItem = new JMenuItem("Shortest Path Dist");
        addNodeItem = new JMenuItem("add Node");
        addEdgeItem = new JMenuItem("add Edge");
        removeNodeItem = new JMenuItem("Remove Node");
        removeEdgeItem = new JMenuItem("Remove Edge");

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        algoMenu.add(tspItem);
        algoMenu.add(centerItem);
        algoMenu.add(shortestPathItem);
        algoMenu.add(connectedItem);
        algoMenu.add(shortestPathDistItem);

        editMenu.add(addNodeItem);
        editMenu.add(addEdgeItem);
        editMenu.add(removeNodeItem);
        editMenu.add(removeEdgeItem);

        menuBar.add(fileMenu);
        menuBar.add(algoMenu);
        menuBar.add(editMenu);

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        tspItem.addActionListener(this);
        centerItem.addActionListener(this);
        shortestPathItem.addActionListener(this);
        connectedItem.addActionListener(this);
        shortestPathDistItem.addActionListener(this);
        addNodeItem.addActionListener(this);
        addEdgeItem.addActionListener(this);
        removeNodeItem.addActionListener(this);
        removeEdgeItem.addActionListener(this);

        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        String file = args[0];
        Ex2.runGUI(file);
//        Ex2.runGUI("G1.json");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode()==32){
//            panel.reset();
//            this.repaint();
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loadItem) {
            int returnValue = j.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = j.getSelectedFile();
                algo.load(selectedFile.toString());
            }
            panel.load(algo);
            panel.repaint();
        }
        if(e.getSource()==saveItem) {
            int returnValue = j.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = j.getSelectedFile();
                algo.save(selectedFile.toString());
            }
        }
        if(e.getSource()==exitItem) {
            System.exit(0);
        }
        if(e.getSource()==tspItem) {
            JFrame textWind = new JFrame();
            String getMessage = JOptionPane.showInputDialog(textWind, "Enter cityKey,cityKey,...");
            String cities= getMessage;

            String[] citiesArr = cities.split(",");
            List<NodeData> ci = new ArrayList<>();
            for (int i=0 ; i<citiesArr.length ; i++){
                ci.add(this.algo.getGraph().getNode(Integer.parseInt(citiesArr[i])));
            }
            List<NodeData> ans0 = this.algo.tsp(ci);
            List<Integer> ans = new ArrayList<>();
            for (int i=0 ; i<ans0.size()-1 ; i++){
                List<NodeData> temp = this.algo.shortestPath(ans0.get(i).getKey() , ans0.get(i+1).getKey());
                for (int j=0 ; j<temp.size()-1 ; j++){
                        ans.add(temp.get(j).getKey());
                }
                if (i==ans0.size()-2){
                    ans.add(temp.get(temp.size()-1).getKey());
                }
            }
            panel.useTsp(algo , ans);
            panel.repaint();
            String s = ans.get(0) + "" ;
            int last = ans.get(0);
            for (int i=1 ; i<ans.size() ; i++){
                if (ans.get(i)!=last){
                    s = s+"->"+ans.get(i);
                    last = ans.get(i);
                }
            }
            JOptionPane.showMessageDialog(null, s , "TSP" ,JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource()==centerItem) {
            int keyOfCenter = this.algo.center().getKey();
            panel.useCenter(algo , keyOfCenter);
            panel.repaint();

        }
        if(e.getSource()==shortestPathItem) {
            JFrame textWind = new JFrame();
            String getMessage = JOptionPane.showInputDialog(textWind, "Enter src,dest");
            String path= getMessage;

            String[] pathArr = path.split(",");
            List<NodeData> ans = this.algo.shortestPath(Integer.parseInt(pathArr[0]) , Integer.parseInt(pathArr[1]));
            List<Integer> ans1 = new ArrayList<>();
            for (int i=0 ; i<ans.size() ; i++){
                ans1.add(ans.get(i).getKey());
            }
            panel.useShortestPath(algo , ans1);
            panel.repaint();
            String s = ans1.get(0) + "" ;
            for (int i=1 ; i<ans1.size() ; i++){
                s = s+"->"+ans1.get(i);
            }
            JOptionPane.showMessageDialog(null, s , "Shortest Path" ,JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource()==shortestPathDistItem) {
            JFrame textWind = new JFrame();
            String getMessage = JOptionPane.showInputDialog(textWind, "Enter src,dest");
            String path= getMessage;
            String[] pathArr = path.split(",");
            double ans = this.algo.shortestPathDist(Integer.parseInt(pathArr[0]) , Integer.parseInt(pathArr[1]));
            panel.repaint();
            JOptionPane.showMessageDialog(null, ans ,"Shortest Path Distance" ,JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource()==connectedItem) {
            boolean ans = this.algo.isConnected();
            panel.repaint();
            JOptionPane.showMessageDialog(null, ans , "Is Connected" ,JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource()==addNodeItem) {
            JFrame textWind = new JFrame();
            String getMessage = JOptionPane.showInputDialog(textWind, "Enter Node: (int)key ,(double)x ,(double)y");
            String cities= getMessage;

            String[] data = cities.split(",");
            int key= Integer.parseInt(data[0]);
            double x= Double.parseDouble(data[1]);
            double y= Double.parseDouble(data[2]);

            NodeData a = new NodeDataC(key,x,y);
            algo.getGraph().addNode(a);
            panel.Node_Edge(algo);
            panel.repaint();
        }
        if(e.getSource()==addEdgeItem) {
            JFrame textWind = new JFrame();
            String getMessage = JOptionPane.showInputDialog(textWind, "Enter Edge: (int)key_src ,(int)key_dst ,(double)w");
            String cities= getMessage;

            String[] data = cities.split(",");
            int key_src= Integer.parseInt(data[0]);
            int key_dst= Integer.parseInt(data[1]);
            double w= Double.parseDouble(data[2]);

            algo.getGraph().connect(key_src,key_dst,w);
            panel.Node_Edge(algo);
            panel.repaint();
        }
        if(e.getSource()==removeNodeItem) {
            JFrame textWind = new JFrame();
            String getMessage = JOptionPane.showInputDialog(textWind, "Enter Node: (int)key");
            String cities= getMessage;

            int key= Integer.parseInt(cities);
            algo.getGraph().removeNode(key);
            panel.Node_Edge(algo);
            panel.repaint();
        }
        if(e.getSource()==removeEdgeItem) {
            JFrame textWind = new JFrame();
            String getMessage = JOptionPane.showInputDialog(textWind, "Enter Node: (int)key_src ,(int)key_dst");
            String cities= getMessage;

            String[] data = cities.split(",");
            int key_src= Integer.parseInt(data[0]);
            int key_dst= Integer.parseInt(data[1]);

            algo.getGraph().removeEdge(key_src,key_dst);
            panel.Node_Edge(algo);
            panel.repaint();
        }

    }

}