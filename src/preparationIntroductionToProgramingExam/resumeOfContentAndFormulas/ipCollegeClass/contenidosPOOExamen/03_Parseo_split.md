# 3. Parsear cadenas con `split` y construir objetos

Ha caído en 4 de 6 exámenes y suele valer entre 1 y 2,5 puntos.

## API necesaria
| Método | Uso |
|---|---|
| `s.split(",")` | devuelve un `String[]` |
| `s.split(";")`, `s.split("#")`, `s.split(":")` | separadores normales |
| `s.split("\\|")`, `s.split("\\.")` | **hay que escapar** los caracteres especiales de regex: `| . * + ? ( ) [ ] $ ^` |
| `s.split("\\s+")` | uno o varios espacios |
| `s.trim()` | quita los espacios de los extremos |
| `Integer.parseInt(s)`, `Double.parseDouble(s)` | lanzan `NumberFormatException` si `s` no es un número |
| `LocalDate.parse("2002-05-12")` | fecha ISO |
| `s.equalsIgnoreCase("CANCION")`, `s.startsWith("ALBUM")` | comprobar etiquetas |

## Nivel 1: un objeto ("Almería,6")
```java
public static Provincia parsearProvincia(String datos) {
    String[] p = datos.split(",");
    return new Provincia(p[0].trim(), Integer.parseInt(p[1].trim()));
}
```

## Nivel 2: lista de objetos ("PP,134340 PSOE,95791 Vox,75573")
```java
String[] bloques = cadena.split(" ");
Votacion[] v = new Votacion[bloques.length];
for (int i = 0; i < bloques.length; i++) {
    String[] campos = bloques[i].split(",");
    v[i] = new Votacion(campos[0], Integer.parseInt(campos[1]));
}
```

## Nivel 3: jerárquico con varios separadores (`# : ; ,`, extra. 23/24)
`"Mesa1:López,García,José,1950-01-15,Universidad;Martínez,…#Mesa2:…"`
```java
String[] mesas = datos.split("#");                  // 1) mesas
for (String m : mesas) {
    String[] partes = m.split(":");                 // 2) nombre : electores
    String[] personas = partes[1].split(";");       // 3) cada persona
    for (String p : personas) {
        String[] c = p.split(",");                  // 4) campos
        new Ciudadano(c[0], c[1], c[2], LocalDate.parse(c[3]), c[4]);
    }
}
```

## Nivel 4: con etiquetas y validación + excepción (25/26)
`"CANCION;título;artista;duracion;genero"`
```java
public static Cancion parse(String linea) throws ParseException {
    if (linea == null) throw new ParseException("Línea nula");
    String[] c = linea.split(";");
    if (c.length != 5 || !c[0].equals("CANCION"))
        throw new ParseException("Formato incorrecto: " + linea);
    try {
        int dur = Integer.parseInt(c[3].trim());
        return new Cancion(c[1], c[2], dur, c[4]);
    } catch (NumberFormatException e) {
        throw new ParseException("Duración no numérica: " + c[3]);
    }
}
```

## Formato anidado (extra. 25/26)
`"ALBUM;El Mal Querer;Rosalía;2018;CANCION;Malamente;150;CANCION;Pienso en tu mirá;193"`
```java
String[] t = s.split(";");
if (t.length < 4 || !t[0].equals("ALBUM") || (t.length - 4) % 3 != 0)
    throw new MiParseadoException("Formato de álbum incorrecto");
int numCanciones = (t.length - 4) / 3;
Album a = new Album(t[1], t[2], Integer.parseInt(t[3]), numCanciones);
for (int i = 4; i < t.length; i += 3) {             // avanzar de 3 en 3
    if (!t[i].equals("CANCION")) throw new MiParseadoException("Falta CANCION");
    a.agregarCancion(new Cancion(t[i + 1], Integer.parseInt(t[i + 2])));
}
return a;
```
💡 Truco: **dibujar los índices** del array resultante (`t[0]=ALBUM, t[1]=título…`) antes de programar.
