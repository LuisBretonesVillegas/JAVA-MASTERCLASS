# Análisis de los exámenes de IP (UAL), cursos 23/24 a 25/26

Exámenes analizados: 6 distintos, todos en `../../practiceExams/`.
Los PDF de `IP/EXAMENES` están repetidos (2023-ORD = 23/24 ord, 2024-EXTRA = 23/24 extra,
2025-ORD = 25/26 ord); se han usado las versiones oficiales de los repositorios del curso.

## 1. Estructura fija
- **Ejercicio 1 (5 puntos): arrays o matrices** dentro de un programa con **menú `do-while` + `switch`**.
- **Ejercicio 2 (5 puntos): POO** a partir de un **diagrama de clases** (a veces solo se describe en el texto).
- Siempre se programa a mano, en papel, sin `Arrays.sort` ni colecciones.

## 2. Tabla de exámenes

| Convocatoria | Ej. 1 (arrays) | Reparto | Ej. 2 (POO) | Reparto |
|---|---|---|---|---|
| Ord. 23/24 (16-ene-24) | `OrdenarApp`: leer 2 arrays **validando que estén ordenados** + **mezcla lineal** | 1 + 1,5 + 2,5 | Elecciones: `Provincia` (`toString`, `equals`), `Votacion` (`compareTo`), **parsear con `split`**, reparto mayoritario + **D'Hondt** usando `ordenacionInsercion(Comparable[])` | 1+1+1+1+2 |
| Extra. 23/24 (02-feb-24) | `MatricesApp` cuadrada: generar **aleatoria [-10,10]**, mostrar con `[ ]`, **traspuesta**, **triangular inferior** | 1×5 | Mesas electorales: `Ciudadano` (`compareTo` por apellido1, apellido2, nombre; `LocalDate`, `getEdad`), **parsear con separadores `# : ; ,`**, **sorteo aleatorio sin repetidos** | 1,5+1,5+2 |
| Ord. 24/25 (15-ene-25) | `MatrizApp` filas×columnas: leer **validando [0,10]**, contar pares/impares, **rotar 90° a la derecha** | 1+1+1+2 | Baraja: `Carta` (`toString`, `equals`), `CartaEspañola.compareTo` (palo y número), `BarajaEspañola` completa, **`cortarBaraja()`** (índice aleatorio, intercambiar bloques) | 1+1+1+2 |
| Extra. 24/25 (03-feb-25) | `NotasApp` `double[][]`: notas aleatorias con `printf`, **media por fila**, **las 2 mejores notas por columna** | 1+1+1+2 | Baraja francesa: `obtenerIndicePalo`, `compareTo` (número y palo), `BarajaFrancesa.crearBaraja` (52 cartas), **`barajar()` (Fisher-Yates)**, **`ordenar()` por inserción** | 1+1+1,5+1,5 |
| Ord. 25/26 (15-ene-26) | `AleatorizarApp`: leer array **validando [1,100]** + **Fisher-Yates** | 1 + 1,5 + 2,5 | Canciones: `Cancion` (constructor, `toString`, `equals`), **`Comparable`** (duración y título), `ListaDeReproduccion.agregarCancion` + **ordenar (inserción/burbuja)**, `CancionParser.parse` con **`ParseException`**, App con `try/catch` | 1+1,5+2,5 |
| Extra. 25/26 (03-feb-26) | `MatricesApp` N×N aleatoria [-10,10]: **diagonales principal y secundaria**, **fila de suma mayor** | 1+1+1+2 | Álbumes: `Album` (composición con `Cancion[]`, `toString` tipo ficha, `equals`), `Comparable` (año y título), **excepción propia `MiParseadoException extends Exception`**, `AlbumParser.parse` de formato anidado `ALBUM;…;CANCION;…` | 1,5+1+2,5 |

## 3. Patrones detectados

### Ejercicio 1 (arrays/matrices)
1. **Menú siempre (1 punto)**: `do { … switch … } while (opcion != SALIR)` con el control "primero ejecuta la opción 1".
2. **Lectura con validación (1-1,5 p)**: rango (`[0,10]`, `[1,100]`), tamaño positivo, orden ascendente.
   Patrón: `do { leer } while (fuera de rango)` o `i--` al detectar un error.
3. **Algoritmo "estrella" (2-2,5 p)**: se alterna entre:
   - Array 1D: **mezcla lineal**, **Fisher-Yates**. Candidatos próximos: búsqueda binaria, eliminar duplicados, invertir, rotar k posiciones, moda.
   - Matriz: **traspuesta, triangular, rotar 90°, diagonales, suma de filas/columnas, 2 mejores por columna**. Candidatos: simétrica, matriz identidad, producto de matrices, máximo y su posición, rotar a la izquierda, espiral.
4. **Ordinario ⇒ suele ser array 1D; extraordinario ⇒ matriz.** Excepción: el ordinario 24/25 fue de matriz.
5. Aleatorios: `(int)(Math.random()*21) - 10` para `[-10,10]`; reales `Math.random()*10`.
6. `printf("%4d")`, `printf("%.2f")` para mostrar.

### Ejercicio 2 (POO)
Lo que **cae siempre**:
| Elemento | Veces (de 6) |
|---|---|
| Clase "modelo" con constructor + `toString()` | 6/6 |
| `equals(Object)` sobrescrito | 4/6 |
| `compareTo` / `implements Comparable` con **criterio doble** (A y, si empata, B) | 6/6 |
| **Parsear un `String` con `split`** y construir objetos (`Integer.parseInt`, `LocalDate.parse`) | 4/6 |
| Clase contenedora con **array de objetos + contador** (`agregar`, `mostrar`) | 5/6 |
| **Algoritmo sobre el array de objetos**: inserción, barajar (Fisher-Yates), cortar, sorteo aleatorio sin repetidos, D'Hondt | 6/6 |
| **Excepciones**: `throw`, `try/catch`, **excepción propia** | 2/6 (las 2 de 25/26, tendencia al alza) |
| Herencia (`CartaEspañola extends Carta`, `BarajaEspañola extends Baraja`) | 2/6 |
| Clase `final` con constantes `static final String[]` (FuenteDeDatos, PALOS_VALIDOS) | 3/6 |

### El patrón más importante
**El ejercicio 2 del examen es casi igual que la práctica de la sesión 10 de ese curso:**
- Curso 24/25: la sesión 10 fue `Baraja`, `Carta`, `CartaEspañola`, `CartaFrancesa`, `BarajasApp` → **examen de barajas**.
- Curso 25/26: la sesión 10 fue `Cancion`, `ListaDeReproduccion`, `FuenteDeDatos`, `MultimediaParser`, `ParseException` → **examen de canciones/álbumes**.

Igual con el ejercicio 1, que sale de las sesiones 04-06 (`MezclaLineal`, `Matrices`, `Estadistica`).
**Cuando la sesión 10 de IP2027 esté publicada, esa es la práctica que hay que dominar.**

## 4. Predicción para la convocatoria 26/27
- Ej. 1: menú + lectura validada + un algoritmo de array/matriz no repetido (búsqueda binaria,
  eliminar duplicados, rotar, matriz simétrica, fila/columna máxima, espiral…).
- Ej. 2: dominio nuevo (biblioteca, tienda, liga deportiva, vuelos…) con:
  `toString` + `equals` + `Comparable` (doble criterio) + **parser con `split` y excepción propia** +
  clase contenedora con array y **ordenación por inserción**, o barajar/sorteo.

## 5. ¿Hacen falta más exámenes?
Con 6 exámenes de 3 cursos el patrón es estable. Serían útiles:
- Exámenes **22/23 o anteriores** (para ver si la herencia o las interfaces propias pesan más).
- Exámenes **parciales o de evaluación continua** de IP2027, si los hay.
- El **enunciado de la sesión 10 de IP2027** en cuanto se publique (es el mejor indicador).
