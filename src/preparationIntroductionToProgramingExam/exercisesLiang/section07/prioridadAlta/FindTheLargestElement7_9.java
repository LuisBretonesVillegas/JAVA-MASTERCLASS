/* 0 stars
Write a test program that prompts the user to enter ten numbers, invokes this
method to return the maximum value, and displays the maximum value. Here is
a sample run of the program:
public static double max(double[] array)
Write a test program that prompts the user to enter ten numbers, invokes this
method to return the maximum value, and displays the maximum value. Here is
a sample run of the program:
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07.prioridadAlta;

import java.util.Scanner;

public class FindTheLargestElement7_9 {
    public static void main(String[] args) {
        double[] array = new double[10];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 10 positive numbers: ");
        for (int i = 0; i < 10; i++) {
            array[i] = sc.nextDouble();
        }
        System.out.println("The maximum number is: " + max(array));
    }

    public static double max(double[] array) {
        double maxValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) maxValue = array[i];
        }

        return maxValue;
    }

}
