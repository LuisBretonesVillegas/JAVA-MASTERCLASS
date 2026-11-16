/*
Write a method that returns the sum of all the ele-
ments in a specified row in a matrix using the following header:
public static double sumRow(double[][] m, int rowIndex)
Write a test program that reads a 3-by-4 matrix and displays the sum of each row.
Here is a sample run:
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section08.prioridadAlta;

import java.util.Scanner;

public class SumElementsRowByRow8_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double[][] matrix = new double[3][4];

        System.out.println("Enter a 3-by-4 matrix row by row:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            System.out.println("Sum of the elements at row " + i + " is " + sumRow(matrix, i));
        }
    }

    public static double sumRow(double[][] m, int rowIndex) {
        double sum = 0;
        for (int j = 0; j < m[rowIndex].length; j++) {
            sum += m[rowIndex][j];
        }
        return sum;
    }
}
