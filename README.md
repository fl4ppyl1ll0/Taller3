# Taller 03 - Spooky Season 🎃

Simulación en consola (Java) de minijuegos de Halloween donde cada jugador se transforma en un monstruo distinto
(Zombi, Vampiro, Momia u Hombre Lobo) para cada desafío y compite por el mejor puntaje.
Proyecto del curso de Programación Orientada a Objetos.

## Integrantes


Florencia Araceli Lillo Ossandón - 21.950.521-3 - fl4ppyl1ll0 https://github.com/fl4ppyl1ll0


## Requisitos

- Java JDK 8 o superior (se usa solo la librería estándar permitida por el enunciado).
- Git.

## Instrucciones de ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/<fl4ppyl1ll0 >/<taller3>.git
cd <taller3>
```

### 2. Compilar

```bash
mkdir -p out
javac -d out src/taller03/*.java
```

### 3. Ejecutar

Ejecutar **desde la carpeta raíz del proyecto**, para que el programa encuentre `Monstruos.txt` y `Registros.txt`:

```bash
java -cp out taller03.Main
```

> En IntelliJ / Eclipse / NetBeans: marcar `src` como carpeta de fuentes, ejecutar `taller03.Main` y configurar
> el directorio de trabajo (*working directory*) en la raíz del proyecto.

## Archivos de datos

| Archivo | Formato | Notas |
|---------|---------|-------|
| `Monstruos.txt` | `Tipo;Vida;Velocidad;Fuerza;Resistencia;...` (atributos propios al final) | Obligatorio. Las líneas inválidas se descartan informando por pantalla. |
| `Registros.txt` | `Jugador;PuntajeMaximo` | Opcional: si no existe, se crea al guardar. Se reescribe al terminar cada sesión. |

Formatos completos de `Monstruos.txt`:

```
Zombi;Vida;Velocidad;Fuerza;Resistencia;FuerzaDeMordida
Vampiro;Vida;Velocidad;Fuerza;Resistencia;VelocidadDeVuelo;ResistenciaAlSol
Momia;Vida;Velocidad;Fuerza;Resistencia;LongitudVendajes
HombreLobo;Vida;Velocidad;Fuerza;Resistencia;Ferocidad;FaseLunar
```

## Cómo se juega

1. Se ingresa un nombre de usuario (no vacío y sin `;`).
2. En el menú principal se puede jugar, ver el catálogo, el historial de la sesión, el ranking, terminar sesión o salir.
3. En cada minijuego se elige un monstruo (cada uno se usa **una sola vez por sesión**) y opcionalmente una habilidad.
4. `puntaje = Math.round(potencial × 10 × multiplicador)`; el multiplicador es **1.5** si la habilidad usada coincide con la premiada del minijuego, y **1.0** en caso contrario.

| Minijuego | Habilidad premiada |
|-----------|--------------------|
| Carrera del Cementerio | Volar |
| Caza de Calabazas | Morder |
| Lanzamiento de Lápidas | Enfurecerse |
| Sobrevivir al Amanecer | Regenerarse |

## Diseño

![Diagrama UML](docs/diagrama_uml.png)

- **Herencia:** `Monstruo` (abstracta) → `Zombi`, `Vampiro`, `Momia`, `HombreLobo`.
- **Interfaces de habilidad:** `Mordedor`, `Volador`, `Regenerable`, `Furioso`. Solo definen qué mensaje mostrar; no conocen los minijuegos ni el multiplicador. Se detectan con `instanceof` únicamente para armar el menú de habilidades.
- **Patrón Visitor (bonificación):** cada minijuego implementa `Minijuego` (que extiende `VisitorMinijuego`) y contiene las 4 fórmulas de potencial. Los monstruos solo implementan `aceptar(VisitorMinijuego)`, sin fórmulas ni `if`/`switch` por tipo.
- **Arquitectura Main / Sistema (bonificación):**
  - `Main`: entrada/salida (Scanner, lectura de archivos, `FileWriter`, menús). No crea objetos del modelo ni calcula.
  - `Sistema`: lógica y datos (validación, colecciones, sesión, puntajes, ranking). No imprime, no usa Scanner ni archivos.
- **Colecciones:** `ArrayList` (monstruos, jugadores, minijuegos) y `LinkedList` (historial de la sesión).
- **Ranking:** ordenamiento por inserción implementado por el equipo (puntaje descendente; empate por nombre alfabético).

## Control de errores

El programa no se cae ante: opciones de menú inválidas (letras, vacío, fuera de rango), nombres vacíos o con `;`,
archivos inexistentes o con líneas mal formadas, índices inválidos, intentos de repetir minijuego o monstruo,
varios monstruos del mismo tipo, falta de monstruos disponibles y entrada cerrada (Ctrl+D / Ctrl+Z).

## Pruebas sugeridas

```bash
# Archivo con líneas inválidas: tipo desconocido, campos de más/menos, no numéricos, fuera de rango
printf 'Zombi;100;20;70;60;90\nGolem;1;1;1;1;1\nMomia;120;30;45;85\nVampiro;90;80;55;30;70;abc\nHombreLobo;110;60;90;50;75;9\nZombi;50;10;20;30;40\n' > Monstruos.txt
java -cp out taller03.Main
```

Casos a probar manualmente: nombre vacío / `a;b`, opción `abc` y `7` en el menú, repetir un minijuego,
jugar más minijuegos que monstruos disponibles, dos monstruos del mismo tipo, borrar `Registros.txt`,
terminar sesión sin jugar, superar y no superar un récord.

## Estructura del repositorio

```
.
├── README.md
├── Monstruos.txt
├── Registros.txt
├── docs/
│   ├── diagrama_uml.png
│   ├── diagrama_uml.pdf
│   └── uml.dot
└── src/taller03/
    ├── Main.java, Sistema.java
    ├── Monstruo.java, Zombi.java, Vampiro.java, Momia.java, HombreLobo.java
    ├── Mordedor.java, Volador.java, Regenerable.java, Furioso.java
    ├── VisitorMinijuego.java, Minijuego.java
    ├── CarreraCementerio.java, CazaCalabazas.java, LanzamientoLapidas.java, SobrevivirAmanecer.java
    └── Jugador.java, RegistroPartida.java
```
