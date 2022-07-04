package C01_WorkingWithAbstraction.L02_PointInRectangle;

public class Rectangle {
    private final Point pointA;
    private final Point pointC;

    public Rectangle(Point pointA, Point pointC) {
        this.pointA = pointA;
        this.pointC = pointC;
    }

    public boolean isInside(Point p){
        return p.isGreaterOrEqual(pointA) && p.isLessOrEqual(pointC);
    }

}
