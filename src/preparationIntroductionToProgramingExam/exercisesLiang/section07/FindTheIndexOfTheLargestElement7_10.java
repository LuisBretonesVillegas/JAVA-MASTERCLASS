/*
 Write a method that returns the index of the
largest element in an array of integers. If there is more than one element, return
the largest index. Use the following header:
public static int indexOfLargestElement(double[] array)
Write a test program that prompts the user to enter 10 numbers, invokes this
method to return the index of the largest element, and displays the index and the
element itself.
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07;

import java.util.Scanner;

public class FindTheIndexOfTheLargestElement7_10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] array = new double[10];
        System.out.println("Enter ten positive numbers: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextDouble();
        }
        System.out.println("The largest element is on index: " + indexOfLargestElement(array));
    }

    public static int indexOfLargestElement(double[] array) {
        int largestElementIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > largestElementIndex) largestElementIndex = i;
        }
        return largestElementIndex;

    }

}
