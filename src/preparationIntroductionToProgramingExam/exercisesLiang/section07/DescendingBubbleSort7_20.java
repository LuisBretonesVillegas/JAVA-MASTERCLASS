/* 1 star
Write a sort method that uses the bubble-sort algorithm. The bub-
ble-sort algorithm makes several passes through the array. On each pass, suc-
cessive neighboring pairs are compared. If a pair is not in order, its values are
swapped; otherwise, the values remain unchanged. The technique is called a
bubble sort or sinking sort because the smaller values gradually “bubble” their
way to the top, and the larger values “sink” to the bottom. Write a test program
that reads in 10 double numbers, invokes the method, and displays the sorted
numbers.
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07;

import java.util.Scanner;

public class DescendingBubbleSort7_20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] list = new double[10];
        System.out.println("Enter 10 numbers: ");
        for(int i = 0; i<list.length; i++){
            list[i] = sc.nextDouble();
        }
        bubbleSort(list);
    }
    public static void bubbleSort(double[] list) {
        boolean needNextPass = true;
        //Bubble method inverted
        for (int k = 1; k < list.length && needNextPass; k++) {
            // si no hay intercambios en la pasada, el array ya está ordenado
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (list[i] < list[i + 1]) {
                    double aux = list[i+1];
                    list[i+1] = list[i];
                    list[i] = aux;
                    needNextPass = true;
                }
            }
        }
        for(int i = 0; i<list.length;i++){
            System.out.print(list[i] + " ");
        }
    }
}
