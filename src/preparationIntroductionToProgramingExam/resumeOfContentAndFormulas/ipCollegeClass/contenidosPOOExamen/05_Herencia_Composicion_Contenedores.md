# 5. Herencia, clases abstractas, composición y clases contenedoras

## Herencia (examen de barajas 24/25: `CartaEspañola extends Carta`, `BarajaEspañola extends Baraja`)
```java
public abstract class Baraja {
    protected Carta[] cartas;
    protected int numCartas;

    public Baraja(int capacidad) {
        cartas = new Carta[capacidad];
        crearBaraja();                    // la subclase decide cómo se crea
    }
    protected abstract void crearBaraja();

    public void barajar() { … }          // común a todas (ver 06)
    public void ordenar()  { … }
}

public class BarajaFrancesa extends Baraja {
    public static final String[] PALOS = {"Tréboles","Diamantes","Corazones","Picas"};
    public BarajaFrancesa() { super(52); }

    @Override
    protected void crearBaraja() {
        numCartas = 0;
        for (String palo : PALOS)
            for (int n = 1; n <= 13; n++)
                cartas[numCartas++] = new CartaFrancesa(n, palo);
    }
}
```
Claves:
- `extends` = "es un"; `super(...)` debe ser la primera línea del constructor; `@Override`.
- `protected` permite que las subclases accedan al atributo.
- **Clase abstracta**: no se puede instanciar y puede tener métodos `abstract` sin cuerpo.
- **Interfaz** (`interface Prestable { void prestar(); }`): la clase hace `implements`. `Comparable` es una interfaz.
- **Polimorfismo**: `Baraja b = new BarajaEspañola();` → al llamar a `b.crearBaraja()` se ejecuta el método del hijo.
- `instanceof` y *cast*: `if (m instanceof Podcast p) { … }` (Java 16+) o `((Podcast) m).getEpisodio()`.

## Composición (extra. 25/26: un `Album` tiene `Cancion[]`)
"Parte-todo fuerte": el todo crea y guarda las partes en su interior.

## Clase contenedora: array + contador (aparece en 5 de 6 exámenes)
```java
public class ListaDeReproduccion {
    private String nombre;
    private Cancion[] canciones;
    private int numCanciones;           // cuántas posiciones están ocupadas

    public ListaDeReproduccion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.canciones = new Cancion[capacidadMaxima];
        this.numCanciones = 0;
    }

    public void agregarCancion(Cancion c) {
        if (numCanciones < canciones.length) {
            canciones[numCanciones] = c;
            numCanciones++;
        } else {
            System.out.println("Lista llena");      // o lanzar una excepción
        }
    }

    public void mostrarCanciones() {
        for (int i = 0; i < numCanciones; i++)      // hasta numCanciones, NO hasta length
            System.out.println(canciones[i]);
    }

    public boolean contiene(Cancion c) {             // usa equals
        for (int i = 0; i < numCanciones; i++)
            if (canciones[i].equals(c)) return true;
        return false;
    }

    public void eliminar(int pos) {                  // desplazar a la izquierda
        for (int i = pos; i < numCanciones - 1; i++) canciones[i] = canciones[i + 1];
        canciones[--numCanciones] = null;
    }
}
```

## Métodos de `Object` que conviene conocer
`toString()`, `equals(Object)`, `hashCode()` (si se sobrescribe `equals`, en teoría también `hashCode`), `getClass()`.
