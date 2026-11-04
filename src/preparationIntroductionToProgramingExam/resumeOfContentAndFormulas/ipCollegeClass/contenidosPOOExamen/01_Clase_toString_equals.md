# 1. Clase modelo: atributos, constructor, getters/setters, `toString` y `equals`

La clase modelo cae en el 100 % de los exámenes. `toString` sale siempre y `equals` en 4 de 6:
Provincia (ord. 23/24), Carta (ord. 24/25), Cancion (ord. 25/26) y Album (extra. 25/26).
Suele valer 1 punto y es de lo más fácil de sacar.

| Parte | Qué hay que escribir |
|---|---|
| [Atributos](#atributos-y-constructor) | `private`, uno por dato |
| [Constructor](#atributos-y-constructor) | inicializa todos los atributos con `this.x = x` |
| [Getters / setters](#getters-y-setters) | `getX()`, `setX()` con validación |
| [`toString`](#tostring) | devuelve un `String` con el formato exacto del enunciado |
| [`equals`](#equals) | compara el contenido de dos objetos |

---

## Atributos y constructor
```java
public class Cancion {
    private String titulo;
    private String artista;
    private int duracion;       // en segundos
    private String genero;

    public Cancion(String titulo, String artista, int duracion, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.genero = genero;
    }
}
```
- Los atributos van en `private` (encapsulación). `this.titulo` es el atributo y `titulo` el parámetro.
- Constantes de clase: `public static final String[] PALOS_VALIDOS = {"Oros","Copas","Espadas","Bastos"};`
- Clase "de utilidad" (`FuenteDeDatos`, `CancionParser`): `public final class X`, con métodos y constantes `static`.

## Getters y setters
```java
public String getTitulo() { return titulo; }
public int getDuracion()  { return duracion; }
public void setDuracion(int duracion) {
    if (duracion > 0) this.duracion = duracion;     // validar antes de asignar
}
```
Solo se implementan si los pide el enunciado o los necesitas desde otra clase, por ejemplo `getDiputados()` en D'Hondt.

## toString
```java
@Override
public String toString() {
    return "Cancion [titulo=" + titulo + ", artista=" + artista
         + ", duracion=" + duracion + "s, genero=" + genero + "]";
}
```
- **Copia el formato exacto** de la salida de ejemplo del enunciado: corchetes, comas, unidades (`s`)…
- `System.out.println(obj)` llama automáticamente a `obj.toString()`.
- `toString` tipo "ficha" en varias líneas (extra. 25/26, Album):
  ```java
  StringBuilder sb = new StringBuilder();
  sb.append("ALBUM: ").append(titulo).append("\n");
  sb.append("ARTISTA: ").append(artista).append("  AÑO: ").append(año).append("\n");
  for (int i = 0; i < numCanciones; i++)
      sb.append(i + 1).append(") ").append(canciones[i]).append("\n");
  return sb.toString();
  ```

---

## equals

### Para qué sirve
`==` entre objetos compara **referencias** (si son el mismo objeto en memoria).
`equals` compara el **contenido** (si representan lo mismo).
```java
Cancion a = new Cancion("Imagine", "John Lennon", 183, "Pop");
Cancion b = new Cancion("Imagine", "John Lennon", 183, "Pop");
a == b        // false → dos objetos distintos
a.equals(b)   // true  → solo si has sobrescrito equals (si no, se comporta como ==)
```

### Plantilla (hay que saberla de memoria)
```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;                          // 1. es el mismo objeto
    if (obj == null) return false;                         // 2. el otro es null
    if (getClass() != obj.getClass()) return false;        // 3. es de otra clase
    Cancion other = (Cancion) obj;                         // 4. casting
    return duracion == other.duracion                      // 5. primitivos con ==
        && titulo.equals(other.titulo)                     //    objetos (String…) con equals
        && artista.equals(other.artista);
}
```

### Paso a paso
1. La cabecera es **exactamente** `public boolean equals(Object obj)`. Si pones `equals(Cancion c)`, estás **sobrecargando**, no sobrescribiendo, y no funciona en `contains`, `indexOf`…
2. `@Override` hace que el compilador avise si la cabecera está mal.
3. `getClass() != obj.getClass()` es la versión estricta. También se usa `if (!(obj instanceof Cancion)) return false;`, que admite subclases.
4. Compara **solo los atributos que diga el enunciado**:
   - Album (extra. 25/26): "iguales si tienen el mismo **título, artista y año**".
   - Provincia (ord. 23/24): nombre y número de diputados.
   - Carta (ord. 24/25): número y palo.
5. Para cada tipo de atributo:
   | Tipo | Cómo se compara |
   |---|---|
   | `int`, `char`, `boolean`, `long` | `a == b` |
   | `double` | `Double.compare(a, b) == 0` |
   | `String` u otro objeto | `a.equals(b)` |
   | `String` que puede ser `null` | `Objects.equals(a, b)` (`java.util.Objects`) |
   | `LocalDate` | `fecha.equals(otra.fecha)` |
   | array | `Arrays.equals(a, b)` |

### Versión de la solución oficial (ord. 23/24, Provincia), con control de null
```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Provincia other = (Provincia) obj;
    if (nombre == null) {
        if (other.nombre != null) return false;
    } else if (!nombre.equals(other.nombre)) return false;
    if (numDiputados != other.numDiputados) return false;
    return true;
}
```
Eclipse la genera automáticamente con *Source → Generate hashCode() and equals()*. En el examen se escribe a mano.

### Dónde se usa equals en el examen
- Evitar duplicados al añadir a una lista: `if (!contiene(c)) agregar(c);` (ver [05](05_Herencia_Composicion_Contenedores.md)).
- Buscar un objeto en un array: `if (cartas[i].equals(buscada)) return i;`
- Comprobar etiquetas al parsear: `if (!campos[0].equals("CANCION")) throw …` (ver [03](03_Parseo_split.md)).
- `compareTo` debe ser coherente: si `a.equals(b)`, entonces `a.compareTo(b) == 0` (ver [02](02_Comparable_compareTo.md)).

### Errores típicos
- Comparar `String` con `==`, que compara referencias, no contenido.
- Escribir `equals(Cancion c)` en vez de `equals(Object obj)`.
- Olvidar el *cast* `(Cancion) obj`: `obj.titulo` no compila.
- Comparar todos los atributos cuando el enunciado pide solo algunos.
- Si se sobrescribe `equals`, en teoría también hay que sobrescribir `hashCode()`. En IP no lo piden, pero conviene saberlo:
  ```java
  @Override public int hashCode() { return Objects.hash(titulo, artista, duracion); }
  ```

---

## Fechas (`LocalDate`, extra. 23/24)
```java
LocalDate f = LocalDate.parse("1990-05-12");              // formato ISO yyyy-MM-dd
int edad = Period.between(f, LocalDate.now()).getYears();  // java.time.Period
```
