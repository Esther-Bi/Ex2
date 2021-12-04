package api;

public class NodeDataC implements NodeData{

    private int key;
    private GeoLocation Location;
    private double weight;
    private String info;
    private int tag;

    public NodeDataC(int key, GeoLocation location, double weight, String info, int tag) {
        this.key = key;
        Location = location;
        this.weight = weight;
        this.info = info;
        this.tag = tag;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.Location;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight= w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }

    @Override
    public void setLocation(GeoLocation p) {
        double x= p.x();
        double y= p.y();
        double z= p.z();
        this.Location= new GeoLocationC(x,y,z);
    }
}