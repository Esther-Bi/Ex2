package api;

public class GeoLocationC implements GeoLocation{

    private double x;
    private double y;
    private double z;

    public GeoLocationC(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public GeoLocationC(GeoLocation other){
        this.x = other.x();
        this.y = other.y();
        this.z = other.z();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        // d = ((x2 - x1)2 + (y2 - y1)2 + (z2 - z1)2)1/2
        double dist = Math.sqrt(Math.pow((this.x()-g.x()) , 2) + Math.pow((this.y()-g.y()) , 2) + Math.pow((this.z()-g.z()) , 2));
        return dist;
    }

    @Override
    public String toString() {
        return "\"" + x + "," + y + "," + z + "\"";
    }
}
