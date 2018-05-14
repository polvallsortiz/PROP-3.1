package presentationcontrol;

public class Point{
    private Double x;
    private Double y;

    Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public  Double getY() {
        return y;
    }

    private Double sign(Point p1, Point p2, Point p3) {
        Double first = p1.getX() - p3.getX();
        Double second = p2.getY() - p3.getY();
        Double third = p2.getX() - p3.getX();
        Double fourth = p1.getY() - p3.getY();
        return(first * second - third * fourth);
    }

    public Boolean pointInTriangle(Point t0, Point t1, Point t2) {
        Boolean b1,b2,b3;
        b1 = sign(this,t0,t1) < 0.0;
        b2 = sign(this,t1,t2) < 0.0;
        b3 = sign(this,t2,t0) < 0.0;

        return((b1 == b2) && (b2 == b3));
    }

    //SAME USED IN THE HEXAGON SELECTED AREA
    public Boolean pointInSquare(Point sq0, Point sq1, Point sq3) {
        Boolean vert,hor;
        vert = (this.y > sq0.getY() && this.y < sq3.getY());
        hor = (this.x > sq0.getX() && this.x < sq1.getX());
        return vert && hor;
    }

}
