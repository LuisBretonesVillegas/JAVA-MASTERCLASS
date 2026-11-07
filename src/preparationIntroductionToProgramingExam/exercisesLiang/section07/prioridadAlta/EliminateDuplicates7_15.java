/* 0 star // REPETIR
Write a method that returns a new array by eliminating the
duplicate values in the array using the following method header:
public static int[] eliminateDuplicates(int[] list)
Write a test program that reads in 10 integers, invokes the method, and displays
the distinct numbers separated by exactly one space. Here is a sample run of the
program:

HINT 1:
1. Necesitas un segundo array (o un contador) para guardar solo los valores únicos. No intentes borrar del array original: los arrays tienen tamaño fijo y no se puede quitar un elemento.
2. Recorre el array original elemento por elemento. Antes de añadir cada uno al array de únicos, pregúntate: ¿este valor ya está en el array de únicos?
3. Esa pregunta se responde con otro bucle, que recorre solo la parte ya rellenada del array de únicos. Un metodo auxiliar tipo contains(array, count, value) que devuelva boolean te deja el código más limpio.
4. Lleva un contador de cuántos únicos has guardado hasta ahora. Sirve para dos cosas:
   - saber en qué posición insertar el siguiente;
   - limitar el bucle de búsqueda, para no comparar contra los ceros de las posicion, un 0 real en la entrada parecería un duplicado.

HINT 2:
  1. Tienes un valor x que quieres comprobar y un array donde ya has guardado los valores únicos.
  2. Recorres ese array con un for, solo hasta el contador de los que llevas guardados.
  3. En cada vuelta comparas array[i] con x.
  4. Si son iguales, ya sabes que x había salido. No hace falta seguir buscando, así que puedes cortar ahí.
  5. Si terminas el bucle sin haber encontrado ninguna coincidencia, x es nuevo.
 */
package preparationIntroductionToProgramingExam.exercisesLiang.section07.prioridadAlta;

import java.util.Arrays;
import java.util.Scanner;

public class EliminateDuplicates7_15 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[10];
        System.out.println("Enter 10 positive numbers: ");
        for (int i = 0; i < 10; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(eliminateDuplicates(array)));
    }

    public static int[] eliminateDuplicates(int[] list) {

        int[] auxArray = new int[list.length];
        int count = 0;
        boolean isDuplicate;
        for (int i = 0; i < list.length; i++) {
            isDuplicate = false;
            //compares a aux array with the list and looks for duplicates
            //Only searches until count so it skips default zeros that appear when duplicates start appearing
            for (int j = 0; j < count; j++) {
                if (auxArray[j] == list[i]) {
                    isDuplicate = true;
                }
            }
            //Only adds element if duplicate is false, values are sorted consecutively
            if (isDuplicate == false) {
                auxArray[count] = list[i];
                count++;
            }
        }
        //List is bigger than needed so we copy only the first count elements into a count size array
        int[] arrayWithoutDuplicates = new int[count];
        for (int i = 0; i < arrayWithoutDuplicates.length; i++) {
            arrayWithoutDuplicates[i] = auxArray[i];
        }
        return arrayWithoutDuplicates;
    }


}
