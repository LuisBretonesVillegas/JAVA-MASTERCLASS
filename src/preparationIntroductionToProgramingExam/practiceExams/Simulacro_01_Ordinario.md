# Simulacro 1: convocatoria ordinaria

**Introducción a la Programación · Simulacro estilo UAL · Duración: 2 horas · Sin apuntes ni ordenador**

Sigue el esquema de siempre: **Ejercicio 1 (5 puntos): arrays** y **Ejercicio 2 (5 puntos): POO**.
Está basado en lo que ha caído en los ordinarios 23/24 (validar orden + mezcla lineal) y 25/26
(parser + `ParseException` + ordenación), con algoritmos que aún no han salido: **eliminar duplicados** y **búsqueda binaria**.

> Hazlo primero en papel y cronometrado. Las soluciones están al final de cada ejercicio, plegadas.

---

## Ejercicio 1 (5 puntos)

Escribe un programa **`BuscadorApp`** en Java que trabaje con un array de enteros **ordenado**.
Hay que implementar el programa completo con métodos estáticos.

**1. (1 punto)** Escribe el código que presente el siguiente Menú Principal y lea la opción introducida por el usuario.
Las opciones 2 y 3 solo se pueden ejecutar si antes se ha ejecutado la opción 1; si no, se muestra un mensaje de error.
Tras cada opción se vuelve a mostrar el menú.
```
--- Menú principal ---
1. Lectura y validación de datos
2. Eliminar duplicados
3. Búsqueda binaria
4. Salir
Elige una opción:
```

**2. (1,5 puntos)** *Lectura y validación de datos.* Pide el tamaño del array (mayor que 0) y sus elementos, validando que
cada número esté en el rango **[-50, 50]** y que el array quede **ordenado de forma ascendente** (se permiten repetidos).
Si un valor no es válido, se vuelve a pedir esa misma posición.
```
Elige una opción: 1
Introduce el tamaño del array: 6
Valor 1: -3
Valor 2: 60
El número debe estar entre -50 y 50.
Valor 2: 2
Valor 3: 2
Valor 4: 7
Valor 5: 5
El array debe estar ordenado de forma ascendente.
Valor 5: 7
Valor 6: 12
Array leído y validado correctamente.
```

**3. (1 punto)** *Eliminar duplicados.* Muestra el array original y crea un **array nuevo, del tamaño justo**, sin elementos
repetidos. Ese nuevo array pasa a ser el array con el que trabaja el programa.
```
Elige una opción: 2
Array original: -3 2 2 7 7 12
Sin duplicados: -3 2 7 12
```

**4. (1,5 puntos)** *Búsqueda binaria.* Pide un valor y búscalo con el algoritmo de **búsqueda binaria** (no vale la búsqueda lineal).
Muestra la posición en la que está o un mensaje si no está.
```
Elige una opción: 3
Valor a buscar: 7
Encontrado en la posición 2.
Elige una opción: 3
Valor a buscar: 5
El valor 5 no está en el array.
```

<details>
<summary>Ver solución del ejercicio 1</summary>

```java
import java.util.Scanner;

public class BuscadorApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] datos = null;
        int opcion;
        do {
            System.out.println("--- Menú principal ---");
            System.out.println("1. Lectura y validación de datos");
            System.out.println("2. Eliminar duplicados");
            System.out.println("3. Búsqueda binaria");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    datos = leerYValidar(sc);
                    System.out.println("Array leído y validado correctamente.");
                    break;
                case 2:
                    if (datos == null) {
                        System.out.println("Primero debe ejecutar la opción 1.");
                    } else {
                        System.out.print("Array original: ");
                        mostrar(datos);
                        datos = eliminarDuplicados(datos);
                        System.out.print("Sin duplicados: ");
                        mostrar(datos);
                    }
                    break;
                case 3:
                    if (datos == null) {
                        System.out.println("Primero debe ejecutar la opción 1.");
                    } else {
                        System.out.print("Valor a buscar: ");
                        int x = sc.nextInt();
                        int pos = busquedaBinaria(datos, x);
                        if (pos >= 0) System.out.println("Encontrado en la posición " + pos + ".");
                        else System.out.println("El valor " + x + " no está en el array.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
        sc.close();
    }

    public static int[] leerYValidar(Scanner sc) {
        int n;
        do {
            System.out.print("Introduce el tamaño del array: ");
            n = sc.nextInt();
            if (n <= 0) System.out.println("El tamaño debe ser mayor que 0.");
        } while (n <= 0);
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Valor " + (i + 1) + ": ");
            v[i] = sc.nextInt();
            if (v[i] < -50 || v[i] > 50) {
                System.out.println("El número debe estar entre -50 y 50.");
                i--;                                    // se repite la misma posición
            } else if (i > 0 && v[i] < v[i - 1]) {
                System.out.println("El array debe estar ordenado de forma ascendente.");
                i--;
            }
        }
        return v;
    }

    // Como el array está ordenado, los repetidos están siempre juntos.
    public static int[] eliminarDuplicados(int[] v) {
        int[] aux = new int[v.length];
        int k = 0;
        for (int i = 0; i < v.length; i++) {
            if (i == 0 || v[i] != v[i - 1]) {
                aux[k] = v[i];
                k++;
            }
        }
        int[] resultado = new int[k];                   // array del tamaño justo
        for (int i = 0; i < k; i++) resultado[i] = aux[i];
        return resultado;
    }

    public static int busquedaBinaria(int[] v, int x) {
        int ini = 0, fin = v.length - 1;
        while (ini <= fin) {
            int medio = (ini + fin) / 2;
            if (v[medio] == x) return medio;
            if (v[medio] < x) ini = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }

    public static void mostrar(int[] v) {
        for (int i = 0; i < v.length; i++) System.out.print(v[i] + " ");
        System.out.println();
    }
}
```

**Criterios de corrección orientativos**
- 1: menú en `do-while` + `switch`, opción por defecto y control "primero la opción 1".
- 2: validar tamaño, rango y orden; volver a pedir la **misma** posición (`i--`).
- 3: aprovechar que está ordenado (comparar con el anterior); array nuevo de tamaño `k`.
- 4: `ini`, `fin`, `medio`; condición `ini <= fin`; devolver `-1` si no está.

</details>

---

## Ejercicio 2 (5 puntos)

Basándose en el siguiente diagrama de clases, implemente en Java **únicamente** los apartados que se piden para gestionar
los libros de una biblioteca.

| Clase | Atributos | Métodos |
|---|---|---|
| `Libro` *implements* `Comparable` | `- isbn: String`<br>`- titulo: String`<br>`- autor: String`<br>`- año: int`<br>`- paginas: int` | `Libro(isbn, titulo, autor, año, paginas)`<br>`toString(): String`<br>`equals(o: Object): boolean`<br>`compareTo(o: Object): int` |
| `Estanteria` | `- libros: Libro[]`<br>`- numLibros: int` | `Estanteria(capacidad: int)`<br>`contiene(l: Libro): boolean`<br>`agregar(l: Libro): boolean`<br>`ordenar(): void`<br>`mostrar(): void` |
| «final» `LibroParser` | | `parse(linea: String): Libro throws FormatoLibroException` |
| `FormatoLibroException` *extends* `Exception` | | `FormatoLibroException(message: String)`<br>`FormatoLibroException(message: String, cause: Throwable)` |
| «final» `FuenteDeDatos` | `DATOS: String[]` | |
| `BibliotecaApp` | | `main(args: String[]): void` |

**Relaciones:** `Estanteria` ◆── 0..* `Libro` (composición) · `LibroParser` crea `Libro` y lanza `FormatoLibroException` ·
`BibliotecaApp` usa `FuenteDeDatos`, `LibroParser` y `Estanteria`.

**1. (1 punto)** Implemente la clase **`Libro`** con su constructor y los métodos `toString()` y `equals()`.
Dos libros son iguales si tienen el **mismo ISBN**. Formato de `toString()`:
```
Libro [ISBN-003, Rayuela, Cortázar, 1963, 600 págs]
```

**2. (1 punto)** Haga que `Libro` implemente **`Comparable`**. El método `compareTo` debe ordenar primero por **autor**
(alfabéticamente) y, si el autor coincide, por **año, del más reciente al más antiguo**.

**3. (1,5 puntos)** Implemente la excepción **`FormatoLibroException`**, que hereda de `java.lang.Exception`, y la clase de utilidad
**`LibroParser`** con el método estático `parse(String)`, que recibe una cadena con el formato
`"LIBRO;isbn;titulo;autor;año;paginas"` y devuelve el objeto `Libro`. Debe lanzar `FormatoLibroException` si:
el número de campos no es 6, la etiqueta no es `LIBRO`, el año o las páginas no son números, o las páginas son ≤ 0.

**4. (1,5 puntos)** Implemente la clase **`Estanteria`** (array de libros + contador):
- `agregar(Libro l)` añade el libro si hay sitio y **no está ya** (usa `equals` mediante `contiene`). Devuelve `true` si lo añade.
- `ordenar()` ordena los libros con el algoritmo de **ordenación por inserción** usando `compareTo`.
- `mostrar()` muestra los libros.

Y la clase **`BibliotecaApp`**, que crea una `Estanteria` con capacidad para todos los datos, recorre `FuenteDeDatos.DATOS`
usando el parser (**capturando** las excepciones sin detener el programa), avisa de los repetidos y muestra los libros
antes y después de ordenarlos.

```java
public final class FuenteDeDatos {
    public static final String[] DATOS = {
        "LIBRO;ISBN-001;Cien años de soledad;García Márquez;1967;471",
        "LIBRO;ISBN-002;El amor en los tiempos del cólera;García Márquez;1985;368",
        "LIBRO;ISBN-003;Rayuela;Cortázar;1963;600",
        "LIBRO;ISBN-001;Cien años de soledad;García Márquez;2007;496",
        "REVISTA;Muy Interesante;2024",
        "LIBRO;ISBN-006;La sombra del viento;Ruiz Zafón;dos mil uno;565",
        "LIBRO;ISBN-007;Marina;Ruiz Zafón;1999;292",
        "LIBRO;ISBN-008;El juego del ángel;Ruiz Zafón;2008;672"
    };
}
```
Salida esperada:
```
Repetido o estantería llena: ISBN-001
Línea ignorada -> Formato incorrecto: REVISTA;Muy Interesante;2024
Línea ignorada -> Año o páginas no numéricos: LIBRO;ISBN-006;La sombra del viento;Ruiz Zafón;dos mil uno;565
--- Libros cargados ---
Libro [ISBN-001, Cien años de soledad, García Márquez, 1967, 471 págs]
Libro [ISBN-002, El amor en los tiempos del cólera, García Márquez, 1985, 368 págs]
Libro [ISBN-003, Rayuela, Cortázar, 1963, 600 págs]
Libro [ISBN-007, Marina, Ruiz Zafón, 1999, 292 págs]
Libro [ISBN-008, El juego del ángel, Ruiz Zafón, 2008, 672 págs]
--- Libros ordenados ---
Libro [ISBN-003, Rayuela, Cortázar, 1963, 600 págs]
Libro [ISBN-002, El amor en los tiempos del cólera, García Márquez, 1985, 368 págs]
Libro [ISBN-001, Cien años de soledad, García Márquez, 1967, 471 págs]
Libro [ISBN-008, El juego del ángel, Ruiz Zafón, 2008, 672 págs]
Libro [ISBN-007, Marina, Ruiz Zafón, 1999, 292 págs]
```

<details>
<summary>Ver solución del ejercicio 2</summary>

**2.1 y 2.2: `Libro`**
```java
public class Libro implements Comparable {
    private String isbn;
    private String titulo;
    private String autor;
    private int año;
    private int paginas;

    public Libro(String isbn, String titulo, String autor, int año, int paginas) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.paginas = paginas;
    }

    public String getIsbn()  { return isbn; }
    public String getAutor() { return autor; }
    public int getAño()      { return año; }

    @Override
    public String toString() {
        return "Libro [" + isbn + ", " + titulo + ", " + autor + ", " + año + ", " + paginas + " págs]";
    }

    // Dos libros son iguales si tienen el mismo ISBN.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro other = (Libro) obj;
        return isbn.equals(other.isbn);
    }

    // 1º autor (alfabético), 2º año (del más reciente al más antiguo).
    @Override
    public int compareTo(Object o) {
        Libro otro = (Libro) o;
        int c = this.autor.compareTo(otro.autor);
        if (c != 0) return c;
        return Integer.compare(otro.año, this.año);   // invertido: descendente
    }
}
```

**2.3: `FormatoLibroException` y `LibroParser`**
```java
public class FormatoLibroException extends Exception {
    public FormatoLibroException(String message) {
        super(message);
    }
    public FormatoLibroException(String message, Throwable cause) {
        super(message, cause);
    }
}

public final class LibroParser {
    // Formato: "LIBRO;isbn;titulo;autor;año;paginas"
    public static Libro parse(String linea) throws FormatoLibroException {
        if (linea == null || linea.isEmpty())
            throw new FormatoLibroException("Línea vacía");
        String[] c = linea.split(";");
        if (c.length != 6 || !c[0].equals("LIBRO"))
            throw new FormatoLibroException("Formato incorrecto: " + linea);
        try {
            int año = Integer.parseInt(c[4].trim());
            int paginas = Integer.parseInt(c[5].trim());
            if (paginas <= 0)
                throw new FormatoLibroException("Número de páginas no válido: " + paginas);
            return new Libro(c[1].trim(), c[2].trim(), c[3].trim(), año, paginas);
        } catch (NumberFormatException e) {
            throw new FormatoLibroException("Año o páginas no numéricos: " + linea, e);
        }
    }
}
```

**2.4: `Estanteria` y `BibliotecaApp`**
```java
public class Estanteria {
    private Libro[] libros;
    private int numLibros;

    public Estanteria(int capacidad) {
        libros = new Libro[capacidad];
        numLibros = 0;
    }

    public boolean contiene(Libro l) {
        for (int i = 0; i < numLibros; i++)          // hasta numLibros, no hasta length
            if (libros[i].equals(l)) return true;
        return false;
    }

    public boolean agregar(Libro l) {
        if (numLibros == libros.length || contiene(l)) return false;
        libros[numLibros] = l;
        numLibros++;
        return true;
    }

    public void ordenar() {                           // inserción
        for (int i = 1; i < numLibros; i++) {
            Libro aux = libros[i];
            int j = i - 1;
            while (j >= 0 && libros[j].compareTo(aux) > 0) {
                libros[j + 1] = libros[j];
                j--;
            }
            libros[j + 1] = aux;
        }
    }

    public void mostrar() {
        for (int i = 0; i < numLibros; i++)
            System.out.println(libros[i]);
    }
}

public class BibliotecaApp {
    public static void main(String[] args) {
        Estanteria estanteria = new Estanteria(FuenteDeDatos.DATOS.length);
        for (String linea : FuenteDeDatos.DATOS) {
            try {
                Libro l = LibroParser.parse(linea);
                if (!estanteria.agregar(l))
                    System.out.println("Repetido o estantería llena: " + l.getIsbn());
            } catch (FormatoLibroException e) {
                System.out.println("Línea ignorada -> " + e.getMessage());   // el programa sigue
            }
        }
        System.out.println("--- Libros cargados ---");
        estanteria.mostrar();
        estanteria.ordenar();
        System.out.println("--- Libros ordenados ---");
        estanteria.mostrar();
    }
}
```

**Criterios de corrección orientativos**
- 2.1: atributos `private`, constructor completo, `toString` con el formato exacto, `equals` con la plantilla (`Object`, `getClass`, cast).
- 2.2: `implements Comparable`, criterio doble y el segundo **descendente**.
- 2.3: `extends Exception`, `throws` en la cabecera, `split(";")`, comprobar longitud y etiqueta, capturar `NumberFormatException` y relanzarla como excepción propia.
- 2.4: recorrer hasta `numLibros`, no duplicar (usa `equals`), inserción correcta, `try/catch` **dentro** del bucle.

</details>
