package C01_WorkingWithAbstraction.Lecture02_PointInRectangle;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isGreaterOrEqual(Point p) {
        return this.x >= p.x && this.y >= p.y;
    }

    public boolean isLessOrEqual(Point p) {
        return this.x <= p.x && this.y <= p.y;
    }
}
