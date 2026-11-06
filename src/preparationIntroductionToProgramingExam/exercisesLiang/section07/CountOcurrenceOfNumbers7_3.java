/* 2 stars
Write a program that reads the integers between
1 and 50 and counts the occurrences of each. Assume the input ends with 0. Here
is a sample run of the program:
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07;

import java.util.Scanner;

public class CountOcurrenceOfNumbers7_3 {

    public static void main(String[] args) {

        int[] array = new int[51];

        Scanner sc = new Scanner(System.in);
        int number;
        System.out.println("Enter the integers between 1 and 50: ");
        do {
            number = sc.nextInt();

            for (int i = 0; i < array.length; i++) {
                if(i+1 == number) array[i] += 1;
            }

        } while (number != 0);

        for(int i = 0; i< array.length; i++){
            if(array[i] == 1) System.out.println((i+1) + " occurs " + array[i] + " time");
            if(array[i] > 1) System.out.println((i+1) + " occurs " + array[i] + " times");
        }
    }
}
