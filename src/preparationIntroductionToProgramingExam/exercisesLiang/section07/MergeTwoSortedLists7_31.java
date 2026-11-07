/* 2 stars
Write the following method that merges two sorted lists
into a new sorted list:
public static int[] merge(int[] list1, int[] list2)
Implement the method in a way that takes at most list1.length + list2.
l e n g t h comparisons. See liveexample.pearsoncmg.com/dsanimation/
MergeSortNeweBook.html for an animation of the implementation. Write a test
program that prompts the user to enter two sorted lists and displays the merged
list. Here is a sample run. Note the first number in the input indicates the number
of the elements in the list. This number is not part of the list.
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07;

import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoSortedLists7_31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter list1 size and contents: ");
        int size = sc.nextInt();
        int[] list1 = new int[size];
        for(int i = 0; i<size; i++){
            list1[i] = sc.nextInt();
        }
        System.out.println("Enter list2 size and contents: ");
        int size2 = sc.nextInt();
        int[] list2 = new int[size2];
        for(int i = 0; i<size2; i++){
            list2[i] = sc.nextInt();
        }
        int[] mergedLists = merge(list1, list2);
        System.out.println("The merged list is: " + Arrays.toString(mergedLists));
    }
    public static int[] merge(int[] list1, int[] list2) {
        int[] mergedLists = new int[list1.length + list2.length];

        for(int i = 0; i<list1.length; i++){
            mergedLists[i] = list1[i];
        }
        int j = 0;
        for(int i = list1.length; i<mergedLists.length; i++){
            mergedLists[i] = list2[j];
            j++;
        }
        Arrays.sort(mergedLists);
       return mergedLists;
    }

}
