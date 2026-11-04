# Contenidos extra para el examen de IP

Complementa:
- `../resumeOfContentAndFormulas/udemyCourse/Resumen_Secciones05-09.md`
- `../resumeOfContentAndFormulas/ipCollegeClass/Resumen_IP2027_Sesiones01-05.md`
- `../resumeOfContentAndFormulas/ipCollegeClass/Analisis_Examenes_IP.md`
- `../resumeOfContentAndFormulas/ipCollegeClass/contenidosPOOExamen/` (temas de POO que aún no se han dado)
- `Seleccion_Ejercicios_Libro_Java.md` (ejercicios del libro de Liang)

---

## 1. Plan de estudio por fases
| Fase | Qué hacer | Material |
|---|---|---|
| 1 | Menú + lectura validada de memoria, sin mirar | `Resumen_IP2027…` (esqueleto de menú) |
| 2 | Algoritmos 1D: invertir, máx/mín, duplicados, mezcla lineal, Fisher-Yates, binaria | libro, cap. 7 |
| 3 | Matrices: traspuesta, triangulares, diagonales, rotar, suma de filas/columnas, 2 mejores | libro, cap. 8 |
| 4 | Clase modelo + `toString`/`equals`/`compareTo` | `contenidosPOOExamen/01-02`, libro caps. 9, 11 y 13 |
| 5 | Parser con `split` + excepción propia + contenedor + inserción | `contenidosPOOExamen/03-06`, libro cap. 12 |
| 6 | Exámenes cronometrados (2 h) en papel, del más antiguo al más reciente | `../practiceExams/` |
| 7 | Rehacer la práctica de la **sesión 10 de IP2027** cuando salga | 🔑 la más parecida al examen |

## 2. Errores típicos que restan puntos
- Inicializar el máximo o el mínimo a `0` en vez de a `v[0]` (te pasa en `section09/MinimumElement`).
- Recorrer hasta `array.length` en un array con contador (huecos `null` → `NullPointerException`).
- Comparar `String` con `==`.
- Olvidar `break` en un `switch` clásico.
- Hacer `nextInt()` seguido de `nextLine()` sin vaciar el buffer.
- Traspuesta: calcularla y después **mostrar la original** (le pasa a la solución oficial del extra. 23/24, `mostrarMatriz(matriz)`).
- No comprobar "primero ejecuta la opción 1" antes de las demás opciones del menú.
- En `split`, no escapar `|` o `.`.
- `compareTo` con `double` hecho con resta y *cast* a `int`.
- No copiar el formato exacto de `toString` del enunciado.
- `Random.nextInt(n)` da `0..n-1`; para `[a,b]` es `nextInt(b-a+1)+a`.

## 3. Chuleta de API permitida y habitual
```java
Math.random(), Math.sqrt, Math.pow, Math.abs, Math.max/min, Math.round
new Random().nextInt(n)
Integer.parseInt, Double.parseDouble, Integer.compare, Double.compare
String: length, charAt, equals, equalsIgnoreCase, compareTo, split, trim, substring,
        indexOf, contains, startsWith, toUpperCase, String.format, String.valueOf
StringBuilder: append, reverse, toString, insert, deleteCharAt
LocalDate.parse, LocalDate.now(), Period.between(a,b).getYears()
System.out.printf("%d %5.2f %-10s %n")
array.clone(), System.arraycopy(src, 0, dst, 0, n)
```

## 4. Simulacro (estilo 26/27), en papel y en 2 horas

**Ejercicio 1 (5 p).** `BuscadorApp`, con un menú: 1. Leer y validar · 2. Eliminar duplicados · 3. Buscar (binaria) · 4. Salir.
1. (1 p) Menú y control de "primero la opción 1".
2. (1,5 p) Leer N (>0) enteros en `[-50, 50]`, **validando que estén en orden ascendente**.
3. (1 p) Crear un array nuevo **sin duplicados** y mostrarlo.
4. (1,5 p) Pedir un valor y buscarlo con **búsqueda binaria**: mostrar la posición o "no encontrado".

**Ejercicio 2 (5 p).** Biblioteca: `Libro`, `Estanteria`, `LibroParser`, `FormatoLibroException`, `BibliotecaApp`, `FuenteDeDatos`.
1. (1 p) `Libro(isbn, titulo, autor, año, paginas)` con `toString` y `equals` (iguales si tienen el mismo ISBN).
2. (1 p) `Libro implements Comparable`: por autor y, si empatan, por año, de más reciente a más antiguo.
3. (1,5 p) `FormatoLibroException extends Exception` y `LibroParser.parse("LIBRO;isbn;titulo;autor;año;paginas")`,
   que lanza la excepción si faltan campos o los números no son válidos.
4. (1,5 p) `Estanteria` con `Libro[]` + contador: `agregar` (que no admita repetidos según `equals`), `ordenar()` por **inserción** y `mostrar()`.
   `BibliotecaApp` recorre `FuenteDeDatos.DATOS` (`String[]` con alguna línea errónea), captura las excepciones, ordena y muestra.

*(Para corregirlo, compara con las soluciones de 23/24 ord. (validación de orden) y de 25/26 ord. y extra. (parser + inserción).)*
