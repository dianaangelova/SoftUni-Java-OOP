package C05_Polymorphism.Lab02_Shapes;

public class Circle extends Shape {
    private Double radius;
    private Double perimeter;
    private Double area;

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
        if (perimeter == null) {
            perimeter = 2 * Math.PI * radius;
        }
        return perimeter;
    }

    @Override
    public Double calculateArea() {
        if (area == null) {
            area = Math.PI * radius * radius;
        }
        return area;
    }
}
