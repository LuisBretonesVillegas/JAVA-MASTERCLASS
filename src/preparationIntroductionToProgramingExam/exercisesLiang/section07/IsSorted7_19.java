/* 2 stars
Write the following method that returns true if the list is already sorted
in decreasing order.
public static boolean isSorted(int[] list)
Write a test program that prompts the user to enter a list and displays whether
the list is sorted or not. Here is a sample run. Note that the first number in the
input indicates the number of the elements in the list. This number is not part
of the list.
 */

package preparationIntroductionToProgramingExam.exercisesLiang.section07;

import java.util.Arrays;
import java.util.Scanner;

public class IsSorted7_19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[10];
        System.out.println("Enter positive 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            array[i] = sc.nextInt();
        }
        if (isSorted(array) == true) System.out.println("The list is sorted.");
        else System.out.println("The list is not sorted. ");
    }

    public static boolean isSorted(int[] list) {
        //Copy with the Arrays.copyOf method, it can also be done with:
        /*
        for(int i = 0; i<list.length; i++){
        sortedList[i] = list[i];
        }
         */
        int[] sortedList = Arrays.copyOf(list, list.length);
        //Sort the array with Array.sort method
        Arrays.sort(sortedList);
        //Generate a array to invert the sorted one
        int[] decreasingOrderSortedList = new int[sortedList.length];

        int j = sortedList.length - 1;
        for (int i = 0; i < sortedList.length; i++) {
            decreasingOrderSortedList[i] = sortedList[j];
            j--;
        }


        //Compare list to sorted list. Need to use Arrays.equals bcs == compares hashes
        if (Arrays.equals(list, decreasingOrderSortedList)) return true;
        else return false;
    }
}
