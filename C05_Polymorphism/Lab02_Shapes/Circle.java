package C05_Polymorphism.Lab02_Shapes;

public class Circle extends  Shape{
    private Double radius;
    private final Double perimeter;
    private final Double area;

    public Circle(Double radius, Double perimeter, Double area) {
        this.radius = radius;
        this.perimeter = perimeter;
        this.area = area;
    }

    public final Double getRadius() {
        return radius;
    }

    public final Double getPerimeter() {
        return perimeter;
    }

    public final Double getArea() {
        return area;
    }

    @Override
    public Double calculatePerimeter() {
        return perimeter;
    }

    @Override
    public Double calculateArea() {
        return area;
    }
}
