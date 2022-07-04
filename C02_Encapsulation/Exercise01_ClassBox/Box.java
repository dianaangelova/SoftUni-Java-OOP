package C02_Encapsulation.Exercise01_ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setHeight(height);
        setLength(length);
        setWidth(width);
    }

    private void setLength(double length) {
        checkForNegative(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
        checkForNegative(width, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
        checkForNegative(height, "Height");
        this.height = height;
    }

    private void checkForNegative(double argument, String argumentType) {
        if (argument <= 0) {
            throw new IllegalArgumentException(argumentType + " cannot be zero or negative.");
        }
    }

    public double calculateLateralSurfaceArea() {
        double lateralSurfaceArea;
        lateralSurfaceArea = 2 * length * height + 2 * width * height;
        return lateralSurfaceArea;
    }

    public double calculateSurfaceArea() {
        double surfaceArea;
        surfaceArea = 2 * length * width + 2 * length * height + 2 * width * height;
        return surfaceArea;
    }

    public double calculateVolume() {
        double volume;
        volume = length * width * height;
        return volume;

    }
}
