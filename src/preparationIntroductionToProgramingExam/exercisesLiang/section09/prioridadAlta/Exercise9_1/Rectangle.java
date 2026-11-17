package preparationIntroductionToProgramingExam.exercisesLiang.section09.prioridadAlta.Exercise9_1;

public class Rectangle {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(4, 40);
        Rectangle rectangle2 = new Rectangle(3.5, 35.9);
        System.out.println("The area of the first rectangle is: " + rectangle1.getArea());
        System.out.println("The area of the second rectangle is: " + rectangle2.getArea());
        System.out.println("The perimeter of the first rectangle is: " + rectangle1.getPerimeter());
        System.out.println("The perimeter of the second rectangle is: " + rectangle2.getPerimeter());
    }

    private double width = 1, height = 1;

    public Rectangle() {

    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return width * 2 + height * 2;
    }

}
