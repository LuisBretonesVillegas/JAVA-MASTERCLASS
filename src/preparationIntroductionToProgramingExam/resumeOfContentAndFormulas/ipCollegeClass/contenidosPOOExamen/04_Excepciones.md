# 4. Excepciones: `try/catch`, `throw`, `throws` y excepciones propias

Ha caído en los 2 exámenes de 25/26 (ord. y extra.), así que es muy probable que vuelva a salir.

## Jerarquía mínima
```
Throwable
 └─ Exception                 ← "checked": obliga a capturarla o declararla con throws
     ├─ IOException, ParseException (java.text)…
     └─ RuntimeException      ← "unchecked": no obliga
         ├─ NumberFormatException, ArithmeticException
         ├─ ArrayIndexOutOfBoundsException, NullPointerException
         ├─ InputMismatchException (Scanner)
         └─ IllegalArgumentException
```

## Excepción propia (el examen pide "que herede de `java.lang.Exception`")
```java
public class MiParseadoException extends Exception {
    public MiParseadoException(String message) {
        super(message);
    }
    public MiParseadoException(String message, Throwable cause) {   // encadenada
        super(message, cause);
    }
}
```

## Lanzar (`throw`) y declarar (`throws`)
```java
public static Album parse(String s) throws MiParseadoException {
    if (s == null || s.isEmpty())
        throw new MiParseadoException("Cadena vacía");
    try {
        int año = Integer.parseInt(…);
    } catch (NumberFormatException e) {
        throw new MiParseadoException("Año no numérico", e);     // envolver la causa
    }
    …
}
```

## Capturar en la App y seguir con el resto (patrón del examen)
```java
for (String linea : FuenteDeDatos.DATOS_CANCIONES) {
    try {
        Cancion c = CancionParser.parse(linea);
        lista.agregarCancion(c);
    } catch (ParseException e) {
        System.out.println("Línea ignorada: " + e.getMessage());   // no se para el programa
    }
}
```

## Validar la entrada de teclado sin que el programa falle
```java
int n;
while (true) {
    try {
        System.out.print("Número: ");
        n = Integer.parseInt(sc.nextLine());
        break;
    } catch (NumberFormatException e) {
        System.out.println("No es un número, repite.");
    }
}
```

## Claves
- `finally` se ejecuta siempre (se usa para cerrar recursos).
- Varios `catch`: **primero el más específico** y después el más general.
- Si se usa `java.text.ParseException`, su constructor es `(String, int errorOffset)`. En el examen de 25/26 la `ParseException` era **propia**: `class ParseException extends Exception`.
- `throw` se escribe dentro del método (lanza la excepción); `throws` va en la cabecera (la declara).
