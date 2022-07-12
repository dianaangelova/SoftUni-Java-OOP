package C05_Polymorphism.Lab02_Shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;
    private Double perimeter;
    private Double area;

    public Rectangle(Double height, Double width, Double perimeter, Double area) {
        this.height = height;
        this.width = width;
        this.perimeter = perimeter;
        this.area = area;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
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
            perimeter = 2 * width + 2 * height;
        }
        return perimeter;
    }

    @Override
    public Double calculateArea() {
        if (area == null) {
            area = height * width;
        }
        return area;
    }
}
