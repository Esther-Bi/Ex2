import api.DirectedWeightedGraphAlgorithmsC;
import api.DirectedWeightedGraphC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class GraphPanel extends JPanel implements MouseListener {

    String message;
    private DirectedWeightedGraphAlgorithmsC algo;
    int isLoad = 0;
    boolean Ftsp = false;
    private List<Integer> ansOfTsp;
    private boolean FCenter = false;
    private int ansOfCenter;


    public GraphPanel() {
        super();
        this.setBackground(new Color(7, 43, 73)); //change color of background
        this.addMouseListener(this);
        message = "To Start Load a Graph";
    }

    public void set(DirectedWeightedGraphAlgorithmsC algo){
        this.algo = algo;
    }

    void reset() {
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("MV Boli", Font.PLAIN, 25)); //set font of text
        g.setColor(Color.white);
        if (this.isLoad == 0) g.drawString(message, 100, 100);

        for (Integer keys : ((DirectedWeightedGraphC) algo.getGraph()).getEdgeCollection().keySet()) {
            for (Integer keyd : ((DirectedWeightedGraphC) algo.getGraph()).getEdgeCollection().get(keys).keySet()) {

                double x1 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(keys).getLocation().x());
                double y1 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(keys).getLocation().y());
                double x2 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(keyd).getLocation().x());
                double y2 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(keyd).getLocation().y());

                double hx = (((DirectedWeightedGraphC) algo.getGraph()).maxXValue()) - (((DirectedWeightedGraphC) algo.getGraph()).minXValue());
                double hy = (((DirectedWeightedGraphC) algo.getGraph()).maxYValue()) - (((DirectedWeightedGraphC) algo.getGraph()).minYValue());

                double XNew1 = ((x1 - (((DirectedWeightedGraphC) algo.getGraph()).minXValue())) / hx) * (Toolkit.getDefaultToolkit().getScreenSize().width - 100);
                double YNew1 = ((y1 - (((DirectedWeightedGraphC) algo.getGraph()).minYValue())) / hy) * (Toolkit.getDefaultToolkit().getScreenSize().height - 100);
                double XNew2 = ((x2 - (((DirectedWeightedGraphC) algo.getGraph()).minXValue())) / hx) * (Toolkit.getDefaultToolkit().getScreenSize().width - 100);
                double YNew2 = ((y2 - (((DirectedWeightedGraphC) algo.getGraph()).minYValue())) / hy) * (Toolkit.getDefaultToolkit().getScreenSize().height - 100);

                Arrow arrow = new Arrow((int) XNew1 + 50, (int) YNew1 + 50, (int) XNew2 + 50, (int) YNew2 + 50 ,Color.white , 2);
                arrow.draw(g);

            }
        }

        for (Integer key : ((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().keySet()) {
            double x1 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(key).getLocation().x());
            double y1 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(key).getLocation().y());

            double hx = (((DirectedWeightedGraphC) algo.getGraph()).maxXValue()) - (((DirectedWeightedGraphC) algo.getGraph()).minXValue());
            double hy = (((DirectedWeightedGraphC) algo.getGraph()).maxYValue()) - (((DirectedWeightedGraphC) algo.getGraph()).minYValue());

            double XNew = ((x1 - (((DirectedWeightedGraphC) algo.getGraph()).minXValue())) / hx) * (Toolkit.getDefaultToolkit().getScreenSize().width - 100);
            double YNew = ((y1 - (((DirectedWeightedGraphC) algo.getGraph()).minYValue())) / hy) * (Toolkit.getDefaultToolkit().getScreenSize().height - 100);
            g.setColor(Color.orange);
            g.fillOval((int) XNew + 50, (int) YNew + 50, 10, 10);
            g.setFont(new Font("serif", Font.PLAIN, 10));

            double d = x1;
            BigDecimal bd = new BigDecimal(d);
            bd = bd.round(new MathContext(3));
            double roundedX = bd.doubleValue();
            double e = y1;
            BigDecimal be = new BigDecimal(e);
            be = be.round(new MathContext(3));
            double roundedY = be.doubleValue();

            g.drawString(roundedX + "," + roundedY, (int) XNew + 20 + 50, (int) YNew + 50);
            g.setColor(Color.magenta);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString(key + "", (int) XNew + 50, (int) YNew + 50);
            if (this.FCenter && this.ansOfCenter == key) {
                g.setColor(Color.red);
                g.fillOval((int) XNew + 50, (int) YNew + 50, 20, 20);
                this.FCenter = false;
            }
            g.setColor(Color.WHITE);
        }

        if (this.Ftsp){
            for (int i=0 ; i<this.ansOfTsp.size()-1 ; i++){
                double x1 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(this.ansOfTsp.get(i)).getLocation().x());
                double y1 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(this.ansOfTsp.get(i)).getLocation().y());
                double x2 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(this.ansOfTsp.get(i+1)).getLocation().x());
                double y2 = (((DirectedWeightedGraphC) algo.getGraph()).getNodeCollection().get(this.ansOfTsp.get(i+1)).getLocation().y());

                double hx = (((DirectedWeightedGraphC) algo.getGraph()).maxXValue()) - (((DirectedWeightedGraphC) algo.getGraph()).minXValue());
                double hy = (((DirectedWeightedGraphC) algo.getGraph()).maxYValue()) - (((DirectedWeightedGraphC) algo.getGraph()).minYValue());

                double XNew1 = ((x1 - (((DirectedWeightedGraphC) algo.getGraph()).minXValue())) / hx) * (Toolkit.getDefaultToolkit().getScreenSize().width - 100);
                double YNew1 = ((y1 - (((DirectedWeightedGraphC) algo.getGraph()).minYValue())) / hy) * (Toolkit.getDefaultToolkit().getScreenSize().height - 100);
                double XNew2 = ((x2 - (((DirectedWeightedGraphC) algo.getGraph()).minXValue())) / hx) * (Toolkit.getDefaultToolkit().getScreenSize().width - 100);
                double YNew2 = ((y2 - (((DirectedWeightedGraphC) algo.getGraph()).minYValue())) / hy) * (Toolkit.getDefaultToolkit().getScreenSize().height - 100);
                Arrow arrow = new Arrow((int) XNew1 + 50, (int) YNew1 + 50, (int) XNew2 + 50, (int) YNew2 + 50 ,Color.red , 2);
                arrow.draw(g);
            }
        }
        this.Ftsp=false;
    }

    public void load(DirectedWeightedGraphAlgorithmsC algo){
        this.algo= algo;
        this.isLoad++;
    }

    public void useTsp(DirectedWeightedGraphAlgorithmsC algo , List<Integer> ans){
        this.ansOfTsp = ans;
        this.algo= algo;
        this.Ftsp= true;
    }

    public void useCenter(DirectedWeightedGraphAlgorithmsC algo , int keyOfCenter){
        this.ansOfCenter = keyOfCenter;
        this.algo= algo;
        this.FCenter= true;
    }

    public void useShortestPath(DirectedWeightedGraphAlgorithmsC algo , List<Integer> ans){
        this.ansOfTsp = ans;
        this.algo= algo;
        this.Ftsp= true;
    }

    public void Node_Edge(DirectedWeightedGraphAlgorithmsC algo){
        this.algo= algo;
    }
}