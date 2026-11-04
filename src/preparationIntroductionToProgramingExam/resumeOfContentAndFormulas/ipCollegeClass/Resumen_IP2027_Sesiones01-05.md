# IP 2026/27: resumen de las sesiones 01-05 (proyecto `IP2027`)

Origen: `C:\Users\lbv\IdeaProjects\IP2027\BretonesVillegasLuisIP2027\src\main\java\org\ip\sesionXX`
(cada ejercicio está también en Python en `src/main/python`).

---

## Sesión 01: tipos, operadores, E/S y `Math`
| Programa | Qué practica | Fórmula |
|---|---|---|
| `HolaMundo` | estructura de una clase con `main` | — |
| `Circunferencia` | `Math.PI`, `Math.pow` | longitud `2πr`, área `πr²` |
| `Distancia` | `Math.sqrt` | `√((x2-x1)²+(y2-y1)²)` |
| `CalculadoraPPI` | densidad de píxeles | `ppi = √(ancho²+alto²) / pulgadas` |
| `EnteroAleatorio` | `Math.random()` | entero en `[min,max]`: `(int)(Math.random()*(max-min+1)) + min` |
| `DescomposicionMonedas` | `/` y `%` en cascada; array de constantes `VALORES` | `n = cant / v; cant %= v;` |
| `Relacion1Ej02/03` | ejercicios de la relación 1 | — |

## Sesión 02: condicionales
| Programa | Qué practica |
|---|---|
| `BuenosDias` | `if / else if` por franjas horarias |
| `DiasSemana` | `switch` |
| `ConversionesNumericas` | *casting*, `Integer.parseInt`, `Integer.toBinaryString` |
| `EcuacionSegundoGrado(Argumentos)` | discriminante `Δ = b²-4ac`, `x = (-b ± √Δ)/2a`; leer `args[]` |
| `ImpuestoIRPF` | tramos con `if` encadenados |
| `ValentinaNova` | `switch` con `String`, `printf("%6.2f€%n")`, descuentos acumulables, `nextLine()` para vaciar el buffer |

## Sesión 03: bucles
| Programa | Qué practica |
|---|---|
| `DibujarFiguras` | **menú `do-while` + `switch`**, bucles anidados (cuadrado sólido/hueco, triángulo, ASCII art) |
| `Euclides` | MCD: `while (b != 0) { r = a % b; a = b; b = r; }` |
| `FibonacciTerminoN`, `FibonacciHastaLimite` | `a, b → b, a+b` |
| `Primos` | primo si no tiene divisores en `2..√n` |

## Sesión 04: arrays unidimensionales (base del ejercicio 1 del examen)
| Programa | Qué practica |
|---|---|
| `ArrayUnidimensional` | menú; leer **validando el orden ascendente**; invertir; mayor y menor |
| `Estadistica` | cargar datos aleatorios o manuales; submenú; media, varianza, desviación, moda |
| `MezclaLineal` | **mezcla lineal de dos arrays ordenados** (salió en el examen ordinario 23/24) |

**Fórmulas estadísticas**
```
media     = Σx / n
varianza  = Σ(x - media)² / n
desviación = √varianza
moda      = valor con más repeticiones (array de contadores o doble bucle)
```

## Sesión 05: arrays bidimensionales (matrices)
| Programa | Qué practica |
|---|---|
| `Matrices` | producto por escalar, suma, **multiplicación** (A es N×M, B es M×P) |
| `CifradoCesarConArrays` | matriz de alfabetos; desplazamiento normalizado `((c + d) % 26 + 26) % 26` |
| `PixelArt` | recorrer una matriz y pintar con códigos ANSI |

**Fórmulas de matrices**
```
suma:           C[i][j] = A[i][j] + B[i][j]
producto:       C[i][j] = Σk A[i][k] * B[k][j]
traspuesta:     T[i][j] = M[j][i]
diag. princ.:   M[i][i]
diag. secund.:  M[i][n-1-i]
rotar 90° dcha: R[j][filas-1-i] = M[i][j]   (R tiene columnas×filas)
triang. inf.:   mostrar M[i][j] si i >= j, si no 0
```

---

## Esqueleto de menú (aparece en TODOS los exámenes)
```java
Scanner sc = new Scanner(System.in);
int[] datos = null;
boolean leido = false;
int opcion;
do {
    System.out.println("1. Leer y validar\n2. Operación\n3. Salir");
    System.out.print("Elige una opción: ");
    opcion = sc.nextInt();
    switch (opcion) {
        case 1 -> { datos = leer(sc); leido = true; }
        case 2 -> { if (leido) operar(datos); else System.out.println("Primero la opción 1."); }
        case 3 -> System.out.println("Saliendo...");
        default -> System.out.println("Opción no válida");
    }
} while (opcion != 3);
sc.close();
```

## Lo que todavía no se ha dado en IP2027 (según los cursos 2025 y 2026)
Las sesiones 06-10 de años anteriores tratan: tablas de decisión, `String`/`StringBuffer`/envoltorios/fechas,
**clases y objetos (`toString`, `equals`)**, **búsqueda y ordenación**, **excepciones propias**,
**herencia/interfaces (`Comparable`)** y **parseo con `split`**. Todo esto es la parte de POO del examen;
está desarrollado en [`contenidosPOOExamen/`](contenidosPOOExamen/README.md).
