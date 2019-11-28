package earthquakes.searches;

public class EqSearch {
    private int distance;
    private int minmag;

    public EqSearch(){
    }

    public int getDistance() {
        return this.distance;
    }
    public int getMinmag() {
        return this.minmag;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setMinmag(int minmag) {
        this.minmag = minmag;
    }
}