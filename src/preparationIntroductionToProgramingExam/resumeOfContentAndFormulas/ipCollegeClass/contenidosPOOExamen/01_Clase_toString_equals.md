# 1. Clase modelo: atributos, constructor, getters/setters, `toString`, `equals`

Cae en el 100 % de los exámenes (normalmente vale 1 punto, que es "fácil" de sacar).

```java
public class Cancion {
    // 1. Atributos privados (encapsulación)
    private String titulo;
    private String artista;
    private int duracion;       // en segundos
    private String genero;

    // 2. Constructor que inicializa TODOS los atributos
    public Cancion(String titulo, String artista, int duracion, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.genero = genero;
    }

    // 3. Getters (y setters si los piden)
    public String getTitulo()   { return titulo; }
    public int getDuracion()    { return duracion; }
    public void setDuracion(int d) { if (d > 0) this.duracion = d; }

    // 4. toString: copiar EXACTAMENTE el formato que pide el enunciado
    @Override
    public String toString() {
        return "Cancion [titulo=" + titulo + ", artista=" + artista
             + ", duracion=" + duracion + "s, genero=" + genero + "]";
    }

    // 5. equals: plantilla que hay que saberse de memoria
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                          // misma referencia
        if (obj == null || getClass() != obj.getClass()) return false;
        Cancion other = (Cancion) obj;                         // casting
        return duracion == other.duracion                      // primitivos con ==
            && titulo.equals(other.titulo)                     // objetos con equals
            && artista.equals(other.artista);
    }
}
```

## Claves
- `==` compara **referencias**; `equals` compara **contenido**. Para `String` se usa siempre `equals`.
- En `equals` comparar **solo los atributos que diga el enunciado** (p. ej. "iguales si coinciden título, artista y año").
- Si un atributo `String` puede ser `null`: `Objects.equals(a, b)` (de `java.util.Objects`).
- `toString` tipo "ficha" en varias líneas (extra. 25/26):
  ```java
  StringBuilder sb = new StringBuilder();
  sb.append("ALBUM: ").append(titulo).append("\n");
  sb.append("ARTISTA: ").append(artista).append("  AÑO: ").append(año).append("\n");
  for (int i = 0; i < numCanciones; i++)
      sb.append(i + 1).append(") ").append(canciones[i]).append("\n");
  return sb.toString();
  ```
- Constantes: `public static final String[] PALOS_VALIDOS = {"Oros","Copas","Espadas","Bastos"};`
- Clase "de utilidad" (`FuenteDeDatos`, `Parser`): `public final class X` con métodos/constantes `static`.

## Fechas (`LocalDate`, extra. 23/24)
```java
LocalDate f = LocalDate.parse("1990-05-12");              // formato ISO yyyy-MM-dd
int edad = Period.between(f, LocalDate.now()).getYears();  // java.time.Period
```
