/* 1 star
The reverse method in Section 7.7 reverses an array by
copying it to a new array. Rewrite the method that reverses the array passed in
the argument and returns this array. Write a test program that prompts the user to
enter 10 numbers, invokes the method to reverse the numbers, and displays the
numbers.
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07.prioridadAlta;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseAnArray7_12 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[10];
        System.out.println("Enter positive 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println("The reversed array is: " + Arrays.toString(reverseArray(array))); //Arrays.toString para q no se vea el hashcode del array sino el array
    }

    public static int[] reverseArray(int[] array) {
        int[] arrayCopy = new int[array.length];
        int j = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            arrayCopy[j] = array[i];
            j--;
        }
        /*
        se peude reemplazar por
         for (int k : array) {
        arrayCopy[j] = k;
        j--;
    }
         */
        return arrayCopy;
    }

}
