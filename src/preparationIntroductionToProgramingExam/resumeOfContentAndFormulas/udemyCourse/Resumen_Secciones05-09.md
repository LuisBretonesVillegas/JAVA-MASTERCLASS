# Curso Udemy (Java Masterclass): resumen de las secciones 05 a 09

Resumen de lo que hay en `src/section05` … `src/section09`, con las fórmulas y patrones de código
que sirven para el examen de IP.

---

## Sección 05: expresiones, condicionales y métodos estáticos

| Ejercicio | Concepto clave | Fórmula / truco |
|---|---|---|
| `PositiveNegativeOrZero` | `if / else if / else` | — |
| `SpeedConverter` | `Math.round`, devolver `-1` si la entrada no es válida | `millas = km / 1.609` |
| `MegaBytesConverter` | división entera y módulo | `MB = KB / 1024`, `restoKB = KB % 1024` |
| `BarkingDog` | condiciones booleanas compuestas | `ladra && (hora < 8 \|\| hora > 22)` |
| `LeapYear` | condición de año bisiesto | `(a % 4 == 0 && a % 100 != 0) \|\| a % 400 == 0` |
| `DecimalComparator` | comparar decimales hasta 3 cifras | `(int)(a * 1000) == (int)(b * 1000)` |
| `EqualSumChecker` | operadores de comparación | `a + b == c` |
| `TeenNumberChecker` | métodos auxiliares (`isTeen`) | `13 <= n && n <= 19` |
| `AreaCalculator` | **sobrecarga de métodos** | círculo `π·r²`; rectángulo `x·y` |
| `MinutesToYearsDaysCalculator` | división entera en cascada | `años = min / 525600`; `días = (min / 1440) % 365` |
| `IntEqualityPrinter` | validar parámetros negativos | — |
| `PlayingCat` | rangos de temperatura según el verano | `25..35` (o `25..45` si es verano) |

**Ideas clave**
- Métodos `public static tipo nombre(params)`; cada método tiene una sola responsabilidad.
- Validar los parámetros al principio y devolver un valor centinela (`-1`, `false`).
- **Sobrecarga** = mismo nombre de método con parámetros distintos (también sale en IP).

---

## Sección 06: bucles (`for`, `while`, `do-while`) y descomposición de números

| Ejercicio | Patrón |
|---|---|
| `NumberInWord`, `NumberOfDaysInMonth` | `switch`, días de febrero (reutiliza `isLeapYear`) |
| `SumOddRange` | `for` con condición `i % 2 != 0` |
| `NumberPalindrome` | **invertir un número**: `inv = inv*10 + n%10; n /= 10;` |
| `FirstLastDigitSum` | último dígito `n % 10`; primero: `while (n >= 10) n /= 10;` |
| `EvenDigitSum` | recorrer dígitos y filtrar los pares |
| `SharedDigit`, `LastDigitChecker` | comparar dígitos entre números |
| `GreatestCommonDivisor` | **Euclides**: `while (b != 0) { t = b; b = a % b; a = t; }` |
| `FactorPrinter` | divisores: `if (n % i == 0)` |
| `PerfectNumber` | la suma de los divisores propios es igual a `n` |
| `NumberToWords` | invertir el número y contar dígitos (cuidado con los ceros finales) |
| `FlourPacker` | aritmética de cajas: `usarGrandes = min(grandes, objetivo/5)` |
| `LargestPrime` | factor primo mayor: dividir mientras `n % i == 0` |
| `DiagonalStar` | **dibujar figuras** con dos bucles anidados (`i == j`, `j == n-i-1`, bordes) |
| `InputCalculator` | `Scanner` + `hasNextInt()` para leer hasta una entrada no válida; media |
| `PaintJob` | `Math.ceil(area / areaPorCubo)` |

**Plantilla de lectura validada (muy útil en IP)**
```java
Scanner sc = new Scanner(System.in);
int n;
do {
    System.out.print("Introduce un número positivo: ");
    n = sc.nextInt();
} while (n <= 0);
```

---

## Sección 07: POO I (clases, constructores, encapsulación, herencia)

| Ejercicio | Concepto |
|---|---|
| `SimpleCalculator`, `Person`, `Wall` | atributos `private`, getters/setters, validación en el setter |
| `Point` | sobrecarga de `distance()`: `√((x2-x1)² + (y2-y1)²)` |
| `ComplexNumber` | objeto que se modifica a sí mismo (`add`, `subtract`) |
| `exercise34` Floor / Carpet / Calculator | **composición**: un `Calculator` contiene un `Floor` y un `Carpet` |
| `exercise36` Circle → Cylinder | **herencia**: `extends`, `super(radius)`; volumen = `área · altura` |
| `exercise37` Rectangle → Cuboid | herencia: `super(width, length)`; volumen = `área · altura` |

**Ideas clave**
- Constructor con `this.atributo = parámetro`; si el valor no es válido, se guarda 0.
- `super(...)` tiene que ser la **primera línea** del constructor de la subclase.
- La subclase reutiliza los métodos del padre (`getArea()`) y añade los suyos (`getVolume()`).

---

## Sección 08: POO II (composición, encapsulación, polimorfismo)

| Ejercicio | Concepto |
|---|---|
| `exercise38` Bedroom (Wall, Ceiling, Bed, Lamp) | **composición**: una clase "tiene" objetos de otras |
| `Printer` | **encapsulación**: el estado solo cambia mediante métodos (`addToner`, `printPages`) |
| `exercise40` Car → Ford / Holden / Mitsubishi | **polimorfismo**: `@Override` de `startEngine`, `accelerate`, `brake` |

```java
Car c = new Ford(6, "Focus");   // variable de tipo padre, objeto de tipo hijo
c.startEngine();                // se ejecuta el método de Ford (enlace dinámico)
```

---

## Sección 09: arrays

| Ejercicio | Concepto |
|---|---|
| `SortedArray` | `Arrays.copyOf`, `Arrays.sort`, invertir un array, imprimir con formato |
| `MinimumElement` | leer N elementos y buscar el mínimo |

> ⚠️ **Fallo en `MinimumElement.findMin`**: `minimumValue = array[0];` está *dentro* del bucle,
> así que se reinicia en cada vuelta. Hay que inicializarlo **antes** del `for`:
> ```java
> int min = array[0];
> for (int i = 1; i < array.length; i++) if (array[i] < min) min = array[i];
> ```

**Fórmulas de arrays que aparecen en IP**
- Invertir: `for (i = 0; i < n/2; i++) swap(a, i, n-1-i);`
- Máximo/mínimo: inicializar con `a[0]`, nunca con `0`.
- Copiar: `int[] b = Arrays.copyOf(a, a.length);` (con `b = a` solo se copia la referencia).
- Ordenar: `Arrays.sort(a)`. **En el examen de IP piden programar el algoritmo a mano** (inserción, burbuja).

---

## Qué cubre Udemy y qué falta para el examen de IP

| Tema del examen | ¿Cubierto en las secciones 05-09? |
|---|---|
| Menús `do-while` + `switch` | Parcial (aquí no hay menús) |
| Arrays 1D y algoritmos (mezcla lineal, Fisher-Yates) | Básico |
| **Matrices 2D** | ❌ No (ver `ipCollegeClass`) |
| Clases, constructores, herencia | ✅ |
| `toString`, `equals`, `compareTo` / `Comparable` | ❌ No (ver `ipCollegeClass/contenidosPOOExamen`) |
| `split` / parsear cadenas, excepciones propias | ❌ No |
