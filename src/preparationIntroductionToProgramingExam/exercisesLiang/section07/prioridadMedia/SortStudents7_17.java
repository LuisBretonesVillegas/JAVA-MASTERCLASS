/*
Write a program that prompts the user to enter the number of stu-
dents, the students’ names, and their scores and prints student names in decreasing
order of their scores. Assume the name is a string without spaces, use the Scanner’s
next() method to read a name.
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07.prioridadMedia;

import java.util.Scanner;

public class SortStudents7_17 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of students:");
        int numberOfStudents = sc.nextInt();
        System.out.print("Enter the student name: ");
        String name = sc.nextLine();
        System.out.println("Enter the student grade:");
        int grade = sc.nextInt();
        

    }

    public void sortStudents(int grades, String name) {

    }

}
