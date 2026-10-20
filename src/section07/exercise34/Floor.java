package section07.exercise34;

public class Floor {

    private double width, length;

    public Floor(double width, double length) {
        this.width = width;
        if (this.width < 0) this.width = 0;
        this.length = length;
        if (this.length < 0) this.length = 0;
    }

    public double getArea() {
        return width * length;
    }
}
