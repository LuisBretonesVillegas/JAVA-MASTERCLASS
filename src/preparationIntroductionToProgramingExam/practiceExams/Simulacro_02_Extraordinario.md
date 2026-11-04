# Simulacro 2: convocatoria extraordinaria

**Introducción a la Programación · Simulacro estilo UAL · Duración: 2 horas · Sin apuntes ni ordenador**

Sigue el esquema de siempre: **Ejercicio 1 (5 puntos): matrices** y **Ejercicio 2 (5 puntos): POO**.
Está basado en los extraordinarios 24/25 (matriz de reales con `printf`, media por fila, las 2 mejores por columna) y
25/26 (matriz aleatoria + parser con excepción propia), y añade lo que todavía no ha caído: **recorrer por columnas**,
**detectar rachas** en una fila, **criterio triple** en `compareTo` y **ordenación por burbuja**.

> Hazlo primero en papel y cronometrado. Las soluciones están al final de cada ejercicio, plegadas.

---

## Ejercicio 1 (5 puntos)

Escribe un programa **`TemperaturasApp`** en Java que trabaje con una matriz de **reales** (`double[][]`), donde cada fila es
una semana y cada columna un día (lunes a domingo). Hay que implementar el programa completo con métodos estáticos.

**1. (1 punto)** Escribe el código que presente el siguiente Menú Principal y lea la opción introducida por el usuario.
Las opciones 2 y 3 solo se pueden ejecutar si antes se ha ejecutado la opción 1.
```
--- Menú principal ---
1. Generar temperaturas
2. Media de cada día de la semana
3. Detectar olas de calor
4. Salir
Elige una opción:
```

**2. (1 punto)** *Generar temperaturas.* Pide el número de semanas, validando que esté entre **1 y 10**. Crea la matriz
`semanas × 7` con temperaturas **reales aleatorias en [20.0, 42.0)** y muéstrala con `printf` (una cifra decimal),
con la cabecera de los días:
```
Elige una opción: 1
Número de semanas (1-10): 0
Debe estar entre 1 y 10.
Número de semanas (1-10): 3
            Lun   Mar   Mié   Jue   Vie   Sáb   Dom
Semana 1:  24,1  36,6  39,6  23,1  24,0  21,1  37,7
Semana 2:  37,2  32,8  24,6  41,2  37,0  24,6  36,4
Semana 3:  33,3  21,9  36,6  41,0  23,2  32,8  25,7
```

**3. (1 punto)** *Media de cada día de la semana.* Calcula y muestra la temperatura media de **cada columna**
(la media de todos los lunes, de todos los martes…):
```
Elige una opción: 2
Lun: 31,5 ºC
Mar: 30,4 ºC
Mié: 33,6 ºC
Jue: 35,1 ºC
Vie: 28,1 ºC
Sáb: 26,2 ºC
Dom: 33,3 ºC
```

**4. (2 puntos)** *Detectar olas de calor.* Una **ola de calor** son **3 o más días seguidos** de la misma semana por encima
de **35 ºC**. Para cada semana que tenga alguna, muestra la duración de la ola **más larga** y el día en que empieza.
Si ninguna semana tiene, muestra un mensaje.
```
Elige una opción: 3
Semana 1: ola de calor de 3 días desde el Mar.
Semana 2: ola de calor de 4 días desde el Jue.
```
*(Ejemplo con la semana 1 = `30 36 37 38 30 36 36` y la semana 2 = `36 36 20 36 36 36 36`.)*

<details>
<summary>Ver solución del ejercicio 1</summary>

```java
import java.util.Scanner;

public class TemperaturasApp {

    public static final String[] DIAS = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] temp = null;
        int opcion;
        do {
            System.out.println("--- Menú principal ---");
            System.out.println("1. Generar temperaturas");
            System.out.println("2. Media de cada día de la semana");
            System.out.println("3. Detectar olas de calor");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    int semanas;
                    do {
                        System.out.print("Número de semanas (1-10): ");
                        semanas = sc.nextInt();
                        if (semanas < 1 || semanas > 10) System.out.println("Debe estar entre 1 y 10.");
                    } while (semanas < 1 || semanas > 10);
                    temp = generarTemperaturas(semanas);
                    mostrar(temp);
                    break;
                case 2:
                    if (temp == null) System.out.println("Primero debe ejecutar la opción 1.");
                    else mediaPorDia(temp);
                    break;
                case 3:
                    if (temp == null) System.out.println("Primero debe ejecutar la opción 1.");
                    else detectarOlasDeCalor(temp);
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

    // Reales aleatorios en [20.0, 42.0): min + Math.random() * (max - min)
    public static double[][] generarTemperaturas(int semanas) {
        double[][] t = new double[semanas][7];
        for (int i = 0; i < t.length; i++)
            for (int j = 0; j < t[i].length; j++)
                t[i][j] = 20 + Math.random() * 22;
        return t;
    }

    public static void mostrar(double[][] t) {
        System.out.print("         ");
        for (String d : DIAS) System.out.printf("%6s", d);
        System.out.println();
        for (int i = 0; i < t.length; i++) {
            System.out.printf("Semana %d:", i + 1);
            for (int j = 0; j < t[i].length; j++) System.out.printf("%6.1f", t[i][j]);
            System.out.println();
        }
    }

    // Recorrido por COLUMNAS: el bucle externo es j (día) y el interno i (semana).
    public static void mediaPorDia(double[][] t) {
        for (int j = 0; j < 7; j++) {
            double suma = 0;
            for (int i = 0; i < t.length; i++) suma += t[i][j];
            System.out.printf("%s: %.1f ºC%n", DIAS[j], suma / t.length);
        }
    }

    // Racha más larga de días > 35 en cada fila.
    public static void detectarOlasDeCalor(double[][] t) {
        boolean alguna = false;
        for (int i = 0; i < t.length; i++) {
            int racha = 0, inicio = -1, mejorRacha = 0, mejorInicio = -1;
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j] > 35) {
                    if (racha == 0) inicio = j;      // empieza una racha nueva
                    racha++;
                    if (racha > mejorRacha) { mejorRacha = racha; mejorInicio = inicio; }
                } else {
                    racha = 0;                       // se corta la racha
                }
            }
            if (mejorRacha >= 3) {
                System.out.printf("Semana %d: ola de calor de %d días desde el %s.%n",
                        i + 1, mejorRacha, DIAS[mejorInicio]);
                alguna = true;
            }
        }
        if (!alguna) System.out.println("No hay ninguna ola de calor.");
    }
}
```

**Criterios de corrección orientativos**
- 1: menú y control "primero la opción 1".
- 2: validación `do-while`, fórmula del aleatorio real, `printf("%6.1f")` y cabecera.
- 3: recorrido por columnas y división entre el número de **filas**.
- 4: contador de racha que se reinicia, guardar el inicio y la racha máxima, mensaje si no hay ninguna.

</details>

---

## Ejercicio 2 (5 puntos)

Basándose en el siguiente diagrama de clases, implemente en Java **únicamente** los apartados que se piden para
calcular la clasificación de una liga de fútbol a partir de los resultados de los partidos.

| Clase | Atributos | Métodos |
|---|---|---|
| `Equipo` *implements* `Comparable` | `- nombre: String`<br>`- ciudad: String`<br>`- puntos: int`<br>`- golesFavor: int`<br>`- golesContra: int` | `Equipo(nombre, ciudad)`<br>`getDiferencia(): int`<br>`sumarResultado(favor: int, contra: int): void`<br>`toString(): String`<br>`equals(o: Object): boolean`<br>`compareTo(o: Object): int` |
| `Partido` | `- local, visitante: String`<br>`- golesLocal, golesVisitante: int` | `Partido(local, visitante, golesLocal, golesVisitante)` + getters |
| `Liga` | `- nombre: String`<br>`- equipos: Equipo[]`<br>`- numEquipos: int` | `Liga(nombre: String, maxEquipos: int)`<br>`agregarEquipo(e: Equipo): void`<br>`buscar(nombre: String): Equipo`<br>`registrar(p: Partido): void throws ResultadoInvalidoException`<br>`ordenar(): void`<br>`mostrarClasificacion(): void` |
| «final» `PartidoParser` | | `parse(linea: String): Partido throws ResultadoInvalidoException` |
| `ResultadoInvalidoException` *extends* `Exception` | | `ResultadoInvalidoException(message: String)` |
| «final» `FuenteDeDatos` | `EQUIPOS: String[]`<br>`PARTIDOS: String[]` | |
| `LigaApp` | | `main(args: String[]): void` |

**Relaciones:** `Liga` ◆── 0..* `Equipo` (composición) · `PartidoParser` crea `Partido` y lanza `ResultadoInvalidoException` ·
`LigaApp` usa `FuenteDeDatos`, `PartidoParser` y `Liga`.

**1. (1 punto)** Implemente la clase **`Equipo`**: constructor (puntos y goles empiezan en 0), `getDiferencia()`
(goles a favor − goles en contra), `sumarResultado(favor, contra)` (suma los goles y **3 puntos** si gana, **1** si empata,
**0** si pierde), `toString()` con el formato de la salida y `equals()`: dos equipos son iguales si tienen el
**mismo nombre, sin distinguir mayúsculas**.

**2. (1,5 puntos)** Haga que `Equipo` implemente **`Comparable`** con un **criterio triple**, el de una clasificación:
1º más **puntos**, 2º mejor **diferencia de goles**, 3º **nombre** alfabéticamente.

**3. (1 punto)** Implemente **`ResultadoInvalidoException`** (hereda de `Exception`) y **`PartidoParser.parse(String)`**, que procesa
cadenas con el formato `"PARTIDO;local;visitante;golesLocal;golesVisitante"` y lanza la excepción si el formato es incorrecto,
los goles no son números, hay goles **negativos** o un equipo juega **contra sí mismo**.

**4. (1,5 puntos)** Implemente de la clase **`Liga`**:
- `buscar(nombre)`, que devuelve el equipo con ese nombre o `null` (**usando `equals`**).
- `registrar(Partido p)`, que actualiza a los dos equipos y lanza `ResultadoInvalidoException` si alguno **no está inscrito**.
- `ordenar()`, con el algoritmo de **burbuja** usando `compareTo`.
- `mostrarClasificacion()`, que ordena y muestra la tabla numerada.

Y **`LigaApp`**, que inscribe los equipos de `FuenteDeDatos.EQUIPOS` (formato `"nombre;ciudad"`), registra los partidos de
`FuenteDeDatos.PARTIDOS` **capturando** los errores sin parar el programa y muestra la clasificación.

```java
public final class FuenteDeDatos {
    public static final String[] EQUIPOS = {"Almería;Almería", "Granada;Granada", "Málaga;Málaga", "Cádiz;Cádiz"};
    public static final String[] PARTIDOS = {
        "PARTIDO;Almería;Granada;2;1",
        "PARTIDO;Málaga;Cádiz;0;0",
        "PARTIDO;Granada;Málaga;3;0",
        "PARTIDO;Cádiz;Almería;1;2",
        "PARTIDO;Almería;Sevilla;1;0",
        "PARTIDO;Málaga;Granada;-1;2",
        "PARTIDO;Granada;Cádiz;1;1",
        "PARTIDO;Almería;Málaga;dos;0",
        "PARTIDO;Málaga;Almería;1;1"
    };
}
```
Salida esperada (fíjate en que Cádiz y Málaga empatan a puntos y se desempatan por la diferencia de goles):
```
Partido ignorado -> Equipo no inscrito en la liga: Sevilla
Partido ignorado -> Goles negativos: PARTIDO;Málaga;Granada;-1;2
Partido ignorado -> Goles no numéricos: PARTIDO;Almería;Málaga;dos;0
--- Clasificación Liga Andaluza ---
1. Almería     7 pts  GF  5  GC  3  DG +2
2. Granada     4 pts  GF  5  GC  3  DG +2
3. Cádiz       2 pts  GF  2  GC  3  DG -1
4. Málaga      2 pts  GF  1  GC  4  DG -3
```

<details>
<summary>Ver solución del ejercicio 2</summary>

**2.1 y 2.2: `Equipo`**
```java
public class Equipo implements Comparable {
    private String nombre;
    private String ciudad;
    private int puntos;
    private int golesFavor;
    private int golesContra;

    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.puntos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
    }

    public String getNombre()  { return nombre; }
    public int getPuntos()     { return puntos; }
    public int getDiferencia() { return golesFavor - golesContra; }

    public void sumarResultado(int favor, int contra) {
        golesFavor += favor;
        golesContra += contra;
        if (favor > contra) puntos += 3;
        else if (favor == contra) puntos += 1;
    }

    @Override
    public String toString() {
        return String.format("%-10s %2d pts  GF %2d  GC %2d  DG %+d",
                nombre, puntos, golesFavor, golesContra, getDiferencia());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo other = (Equipo) obj;
        return nombre.equalsIgnoreCase(other.nombre);
    }

    // 1º puntos (desc), 2º diferencia de goles (desc), 3º nombre (asc).
    @Override
    public int compareTo(Object o) {
        Equipo otro = (Equipo) o;
        if (this.puntos != otro.puntos) return otro.puntos - this.puntos;
        if (this.getDiferencia() != otro.getDiferencia()) return otro.getDiferencia() - this.getDiferencia();
        return this.nombre.compareTo(otro.nombre);
    }
}
```

**2.3: `ResultadoInvalidoException` y `PartidoParser`** (`Partido` es una clase con constructor y getters)
```java
public class ResultadoInvalidoException extends Exception {
    public ResultadoInvalidoException(String message) {
        super(message);
    }
}

public final class PartidoParser {
    // Formato: "PARTIDO;local;visitante;golesLocal;golesVisitante"
    public static Partido parse(String linea) throws ResultadoInvalidoException {
        String[] c = linea.split(";");
        if (c.length != 5 || !c[0].equals("PARTIDO"))
            throw new ResultadoInvalidoException("Formato incorrecto: " + linea);
        int gl, gv;
        try {
            gl = Integer.parseInt(c[3].trim());
            gv = Integer.parseInt(c[4].trim());
        } catch (NumberFormatException e) {
            throw new ResultadoInvalidoException("Goles no numéricos: " + linea);
        }
        if (gl < 0 || gv < 0)
            throw new ResultadoInvalidoException("Goles negativos: " + linea);
        if (c[1].trim().equalsIgnoreCase(c[2].trim()))
            throw new ResultadoInvalidoException("Un equipo no puede jugar contra sí mismo: " + linea);
        return new Partido(c[1].trim(), c[2].trim(), gl, gv);
    }
}
```

**2.4: `Liga` y `LigaApp`**
```java
public class Liga {
    private String nombre;
    private Equipo[] equipos;
    private int numEquipos;

    public Liga(String nombre, int maxEquipos) {
        this.nombre = nombre;
        this.equipos = new Equipo[maxEquipos];
        this.numEquipos = 0;
    }

    public void agregarEquipo(Equipo e) {
        if (numEquipos < equipos.length && buscar(e.getNombre()) == null) {
            equipos[numEquipos] = e;
            numEquipos++;
        }
    }

    // Usa equals: se crea un equipo "de búsqueda" con ese nombre.
    public Equipo buscar(String nombre) {
        Equipo clave = new Equipo(nombre, "");
        for (int i = 0; i < numEquipos; i++)
            if (equipos[i].equals(clave)) return equipos[i];
        return null;
    }

    public void registrar(Partido p) throws ResultadoInvalidoException {
        Equipo local = buscar(p.getLocal());
        Equipo visitante = buscar(p.getVisitante());
        if (local == null || visitante == null)
            throw new ResultadoInvalidoException("Equipo no inscrito en la liga: "
                    + (local == null ? p.getLocal() : p.getVisitante()));
        local.sumarResultado(p.getGolesLocal(), p.getGolesVisitante());
        visitante.sumarResultado(p.getGolesVisitante(), p.getGolesLocal());   // al revés
    }

    public void ordenar() {                                  // burbuja
        for (int i = 0; i < numEquipos - 1; i++)
            for (int j = 0; j < numEquipos - 1 - i; j++)
                if (equipos[j].compareTo(equipos[j + 1]) > 0) {
                    Equipo aux = equipos[j];
                    equipos[j] = equipos[j + 1];
                    equipos[j + 1] = aux;
                }
    }

    public void mostrarClasificacion() {
        ordenar();
        System.out.println("--- Clasificación " + nombre + " ---");
        for (int i = 0; i < numEquipos; i++)
            System.out.println((i + 1) + ". " + equipos[i]);
    }
}

public class LigaApp {
    public static void main(String[] args) {
        Liga liga = new Liga("Liga Andaluza", FuenteDeDatos.EQUIPOS.length);
        for (String e : FuenteDeDatos.EQUIPOS) {
            String[] c = e.split(";");
            liga.agregarEquipo(new Equipo(c[0], c[1]));
        }
        for (String linea : FuenteDeDatos.PARTIDOS) {
            try {
                liga.registrar(PartidoParser.parse(linea));
            } catch (ResultadoInvalidoException ex) {
                System.out.println("Partido ignorado -> " + ex.getMessage());
            }
        }
        liga.mostrarClasificacion();
    }
}
```

**Criterios de corrección orientativos**
- 2.1: `sumarResultado` con los 3/1/0 puntos, `toString` con `String.format`, `equals` con `equalsIgnoreCase`.
- 2.2: los dos primeros criterios **descendentes** (`otro - this`) y el tercero ascendente.
- 2.3: validar longitud y etiqueta, `NumberFormatException` → excepción propia, goles negativos y mismo equipo.
- 2.4: `buscar` con `equals`, el visitante suma los goles **al revés**, burbuja hasta `numEquipos`, `try/catch` dentro del bucle.

</details>
