/*
Write a method that returns a random number from a
list of numbers passed in the argument. The method header is specified as follows:
public static int getRandom(int... numbers)
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07.prioridadMedia;

public class RandomNumberSelector7_13 {
    public static void main(String[] args) {
        System.out.println(getRandom(1, 2, 3, 4));
    }
    public static int getRandom(int... numbers){

    int index = (int) (Math.random()* numbers.length);
        return numbers[index];

    }

}
