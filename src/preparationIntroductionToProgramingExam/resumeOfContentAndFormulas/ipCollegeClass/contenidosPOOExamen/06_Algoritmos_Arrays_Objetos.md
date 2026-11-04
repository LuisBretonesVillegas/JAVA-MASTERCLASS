# 6. Algoritmos que se piden sobre arrays (de enteros o de objetos)

El apartado de más puntos (2-2,5) de cada ejercicio suele ser uno de estos algoritmos.

## Ordenación por inserción (24/25 extra., 25/26 ord., D'Hondt 23/24)
```java
for (int i = 1; i < n; i++) {
    Cancion aux = v[i];
    int j = i - 1;
    while (j >= 0 && v[j].compareTo(aux) > 0) { v[j + 1] = v[j]; j--; }
    v[j + 1] = aux;
}
```

## Burbuja (alternativa permitida en 25/26)
```java
for (int i = 0; i < n - 1; i++)
    for (int j = 0; j < n - 1 - i; j++)
        if (v[j].compareTo(v[j + 1]) > 0) { var t = v[j]; v[j] = v[j + 1]; v[j + 1] = t; }
```

## Selección
```java
for (int i = 0; i < n - 1; i++) {
    int min = i;
    for (int j = i + 1; j < n; j++) if (v[j] < v[min]) min = j;
    int t = v[i]; v[i] = v[min]; v[min] = t;
}
```

## Fisher-Yates: barajar o aleatorizar (24/25 extra. y 25/26 ord.)
"Recorrer desde la última hasta la primera e intercambiar con una posición aleatoria anterior o igual"
```java
Random r = new Random();
for (int i = v.length - 1; i > 0; i--) {
    int j = r.nextInt(i + 1);          // 0..i   (o (int)(Math.random()*(i+1)))
    int t = v[i]; v[i] = v[j]; v[j] = t;
}
```
⚠️ Para mostrar "original" y "aleatorizado", **copiar antes** el array: `int[] copia = v.clone();`.

## Cortar la baraja (24/25 ord.): intercambiar los bloques [0,k) y [k,n)
```java
int k = r.nextInt(n - 1) + 1;          // 1..n-1
Carta[] nuevo = new Carta[n];
int p = 0;
for (int i = k; i < n; i++) nuevo[p++] = cartas[i];
for (int i = 0; i < k; i++) nuevo[p++] = cartas[i];
cartas = nuevo;
```

## Sorteo aleatorio sin repetidos con una condición (23/24 extra.)
```java
Ciudadano elegido = null;
while (elegido == null) {
    Ciudadano c = censo[r.nextInt(censo.length)];
    if (c != presidente && c != vocal1 && cumpleRequisitos(c)) elegido = c;
}
```
(Alternativa: barajar con Fisher-Yates y coger los 3 primeros que cumplan la condición.)

## Mezcla lineal de 2 arrays ordenados (23/24 ord.)
```java
int[] r = new int[a.length + b.length];
int i = 0, j = 0, k = 0;
while (i < a.length && j < b.length) r[k++] = (a[i] <= b[j]) ? a[i++] : b[j++];
while (i < a.length) r[k++] = a[i++];
while (j < b.length) r[k++] = b[j++];
```

## Búsqueda lineal y binaria (dadas en la sesión 09 de 2025 y 2026, todavía no han caído → candidato)
```java
static int binaria(int[] v, int x) {           // v ORDENADO
    int ini = 0, fin = v.length - 1;
    while (ini <= fin) {
        int mid = (ini + fin) / 2;
        if (v[mid] == x) return mid;
        if (v[mid] < x) ini = mid + 1; else fin = mid - 1;
    }
    return -1;
}
// Con objetos: v[mid].compareTo(clave) en lugar de < y ==
```

## Reparto D'Hondt (23/24 ord.)
Repetir tantas veces como escaños: ordenar por `votosEnReparto`, dar 1 escaño al último (el mayor) y
recalcular su `votosEnReparto = votosObtenidos / (diputados + 1)`.

## Las 2 mejores de una columna (24/25 extra.)
```java
int p1 = -1, p2 = -1;
for (int f = 0; f < m.length; f++) {
    if (p1 == -1 || m[f][c] > m[p1][c]) { p2 = p1; p1 = f; }
    else if (p2 == -1 || m[f][c] > m[p2][c]) p2 = f;
}
```
