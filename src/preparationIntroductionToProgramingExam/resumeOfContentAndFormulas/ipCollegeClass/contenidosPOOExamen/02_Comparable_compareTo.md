# 2. `Comparable` y `compareTo`, casi siempre con criterio doble

Ha caído en **los 6 exámenes**. En la UAL se usa la versión **sin genéricos** (`Comparable` y `compareTo(Object o)`),
aunque la versión `Comparable<T>` también es válida.

## Contrato
`a.compareTo(b)` devuelve: **negativo** si `a < b`, **0** si son iguales y **positivo** si `a > b`.
El orden queda **ascendente**. Para el orden descendente se invierte (`b` respecto de `a`).

## Plantilla de criterio doble (número y luego texto)
```java
public class Cancion implements Comparable {
    @Override
    public int compareTo(Object o) {
        Cancion otra = (Cancion) o;
        // 1º criterio: duración de menor a mayor
        if (this.duracion != otra.duracion)
            return Integer.compare(this.duracion, otra.duracion);   // o this.duracion - otra.duracion
        // 2º criterio: título alfabéticamente
        return this.titulo.compareTo(otra.titulo);
    }
}
```

## Criterio triple (extra. 23/24: apellido1, apellido2, nombre)
```java
int c = this.apellido1.compareTo(o.apellido1);
if (c != 0) return c;
c = this.apellido2.compareTo(o.apellido2);
if (c != 0) return c;
return this.nombre.compareTo(o.nombre);
```

## Orden por una posición en un array (cartas: palo según un orden fijo)
```java
// PALOS = {"Oros","Copas","Espadas","Bastos"}
private int indicePalo() {
    for (int i = 0; i < PALOS.length; i++)
        if (PALOS[i].equals(palo)) return i;
    return -1;
}
public int compareTo(Object o) {
    CartaEspañola c = (CartaEspañola) o;
    int dif = this.indicePalo() - c.indicePalo();
    return (dif != 0) ? dif : this.numero - c.numero;
}
```

## Comparar `double`
No restar y hacer *cast* a `int` (0.3 - 0.1 se convertiría en 0). Usar `Double.compare(a, b)` o `if (a < b) return -1; …`

## Usar `compareTo` desde un algoritmo genérico
```java
public static void ordenacionInsercion(Comparable[] v) {
    for (int i = 1; i < v.length; i++) {
        Comparable aux = v[i];
        int j = i - 1;
        while (j >= 0 && v[j].compareTo(aux) > 0) {
            v[j + 1] = v[j];
            j--;
        }
        v[j + 1] = aux;
    }
}
```
⚠️ Si el array tiene huecos `null` (array + contador) hay que recorrer solo hasta `numElementos`, no hasta `length`.
