# Ejercicios recomendados de *Introduction to Java Programming and Data Structures* (Liang, 11.ª ed. Global)

Libro: `INGENIERIA-INFORMATICA/LIBROS-PIRATA-LENGUAJES-PROGRAMACIÓN/LIBRO-JAVA.pdf`.
Los números son los de los **Programming Exercises** al final de cada capítulo (edición *Global*;
algunos números difieren de la edición de EE. UU.). Los asteriscos `*` del libro indican la dificultad.

Leyenda: 🔴 imprescindible (se parece a algo que ya ha caído) · 🟠 muy recomendable · 🟢 si sobra tiempo

---

## Bloque A: ejercicio 1 del examen (arrays y matrices)

### Capítulo 7: Single-Dimensional Arrays
| Ej. | Título | Por qué | Prior. |
|---|---|---|---|
| 7.3 | Count occurrence of numbers | array de contadores (moda, cifrado César) | 🔴 |
| 7.9 / 7.10 | Find the (index of the) largest element | máximo y su posición | 🔴 |
| 7.12 | Reverse an array | invertir **en el mismo array** | 🔴 |
| 7.15 | Eliminate duplicates | candidato fuerte para el ej. 1 | 🔴 |
| **7.19** | Sorted? | **validar que un array está ordenado** (ord. 23/24) | 🔴 |
| 7.20 | Descending Bubble Sort | burbuja a mano | 🔴 |
| **7.31** | Merge two sorted lists | **mezcla lineal** (ord. 23/24) | 🔴 |
| 7.13 | Random number selector | aleatorio excluyendo valores (sorteo sin repetidos) | 🟠 |
| 7.17 | Sort students | ordenar dos arrays en paralelo (preparación para arrays de objetos) | 🟠 |
| 7.21 | Normalizing integers | recorrer con fórmula | 🟠 |
| 7.32 | Partition of a list | intercambios en un array | 🟠 |
| 7.6 | The Sieve of Eratosthenes | array de booleanos | 🟢 |
| 7.23 | Game: locker puzzle | bucles + array de booleanos | 🟢 |

Lee también la **sección 7.10 (Searching Arrays)**, con búsqueda lineal y binaria, y la **7.11 (Sorting Arrays)**, con selección.

### Capítulo 8: Multidimensional Arrays
| Ej. | Título | Por qué | Prior. |
|---|---|---|---|
| **8.1** | Sum elements row by row | **suma por filas o columnas** (extra. 25/26) | 🔴 |
| **8.2** | Average the major diagonal | **diagonales** (extra. 25/26) | 🔴 |
| 8.5 | Algebra: add two matrices | suma de matrices | 🔴 |
| 8.6 | Algebra: multiply two matrices | producto (sesión 05) | 🔴 |
| **8.10** | Largest row and column | **fila o columna máxima** con matriz aleatoria | 🔴 |
| 8.13 | Locate the smallest element | posición `[i][j]` del mínimo | 🔴 |
| 8.3 | Sort students on grades | notas en una matriz (extra. 24/25) | 🟠 |
| 8.14 | Explore matrix | filas, columnas y diagonales con todos los valores iguales | 🟠 |
| 8.16 | Sort two-dimensional array | ordenar filas por su primer elemento | 🟠 |
| 8.26 / 8.27 | Row sorting / Column sorting | ordenar dentro de una matriz | 🟠 |
| 8.28 / 8.29 | (Strictly) identical arrays | comparar matrices | 🟠 |
| 8.35 | Largest block | reto: submatriz máxima | 🟢 |
| 8.9 | Game: tic-tac-toe | diagonales + menú | 🟢 |

**Extras que no vienen en el libro pero conviene programar:** traspuesta, triangular inferior/superior,
rotar 90° (derecha e izquierda), matriz simétrica, recorrido en espiral.

---

## Bloque B: ejercicio 2 del examen (POO)

### Capítulo 9: Objects and Classes
| Ej. | Título | Por qué | Prior. |
|---|---|---|---|
| 9.1 | The Rectangle class | clase completa con constructor y getters | 🔴 |
| 9.2 | The Stock class | atributos y métodos de cálculo | 🟠 |
| 9.7 | The Account class | `LocalDate`/`Date`, validaciones | 🟠 |
| 9.4 | Use the Random class | `Random` con semilla | 🟢 |

### Capítulo 10: Object-Oriented Thinking
| Ej. | Título | Por qué | Prior. |
|---|---|---|---|
| **10.9** | The Course class | **clase contenedora array + contador** (`addStudent`, `dropStudent`) | 🔴 |
| 10.1 | The Time class | varios constructores, `toString` | 🟠 |
| 10.3 | The MyInteger class | métodos `static` y de instancia, `equals` | 🟠 |
| 10.4 | The MyPoint class | distancia entre objetos | 🟠 |
| 10.13 | MyRectangle2D class | métodos que reciben otro objeto (`contains`, `overlaps`) | 🟢 |
| 10.26 | New string split method | **entender `split` por dentro** | 🟠 |

Lee también las **secciones 10.10-10.11**: `String`, `StringBuilder`, `split` y expresiones regulares.

### Capítulo 11: Inheritance and Polymorphism
| Ej. | Título | Por qué | Prior. |
|---|---|---|---|
| **11.1** | The Triangle class | `extends GeometricObject`, `toString` | 🔴 |
| **11.2** | Person, Student, Employee, Faculty, Staff | jerarquía y `toString` sobrescrito | 🔴 |
| 11.3 | Subclasses of Account | `super(...)` y métodos sobrescritos | 🟠 |
| 11.4 | Maximum element in ArrayList | (hazlo con un array de objetos en vez de `ArrayList`) | 🟠 |

Lee la sección **"The Object's equals Method"** (cap. 11, justo después de 11.10 *Casting Objects*): la plantilla que piden en el examen.

### Capítulo 12: Exception Handling
| Ej. | Título | Por qué | Prior. |
|---|---|---|---|
| **12.1** | NumberFormatException | `try/catch` con `Integer.parseInt` | 🔴 |
| 12.3 | InputMismatchException | validar lectura con `Scanner` | 🔴 |
| 12.2 | ArrayIndexOutOfBoundsException | capturar índices fuera de rango | 🟠 |
| **12.5** | IllegalTriangleException | **excepción propia** lanzada en el constructor | 🔴 |
| 12.4 | IllegalArgumentException | `throw` en un setter | 🟠 |
| **12.8 / 12.9** | HexFormatException / BinaryFormatException | **excepción propia en un "parser"** (= `MiParseadoException`) | 🔴 |
| 12.7 / 12.10 | Checked Exception / Fraction | excepción *checked* + clase `Fraccion` (vista en el tema 06) | 🟠 |

### Capítulo 13: Abstract Classes and Interfaces
| Ej. | Título | Por qué | Prior. |
|---|---|---|---|
| **13.5** | Enable GeometricObject comparable | **`implements Comparable` + `compareTo`** | 🔴 |
| **13.6** | The ComparableCircle class | `compareTo` en una subclase | 🔴 |
| Enable Circle / Rectangle comparable | (mismo bloque de ejercicios) | `compareTo` + `equals` coherentes | 🔴 |
| 13.1 | Triangle class (abstract) | clase abstracta | 🟠 |
| 13.7 | The Colorable interface | interfaz propia | 🟠 |
| 13.17 | Math: The Complex class | clase `Complejo` (tema 06) | 🟠 |
| 13.22 | The ComparableSquare class | `Comparable` | 🟢 |
| Octagon / Sum the areas | (mismo bloque) | `Comparable` + `Cloneable` + polimorfismo | 🟢 |

### Capítulo 23: Sorting (solo la teoría)
- **23.2 Insertion Sort** y **23.3 Bubble Sort**: son los dos algoritmos que se piden en el examen.
- Ejercicio **23.1 (Generic bubble sort)**: burbuja con `Comparable[]`. 🔴

---

## Plan mínimo si vas con poco tiempo (≈ 20 ejercicios)
`7.12, 7.15, 7.19, 7.31, 8.1, 8.2, 8.10, 8.13, 9.1, 10.9, 11.1, 11.2, 12.1, 12.5, 12.8, 13.5, 13.6, 23.1`
y además los 6 exámenes de `practiceExams` **cronometrados (2 h)**.

## Cómo practicarlos para que se parezcan al examen
1. Mete los ejercicios de los capítulos 7-8 dentro de un **menú `do-while` + `switch`** con la opción "primero lee los datos".
2. En los de los capítulos 9-13, añade siempre `toString`, `equals` y `compareTo` con **criterio doble**, aunque el ejercicio no lo pida.
3. Para cada clase, crea un `FuenteDeDatos` con `String[]` tipo `"TIPO;campo1;campo2"` y un **parser con excepción propia**.
4. No uses `ArrayList` ni `Arrays.sort`: usa **array + contador** y la **inserción programada a mano**.
