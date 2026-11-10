# Sistema de Biblioteca — Colecciones en Java

Proyecto educativo en Java que implementa un pequeño sistema de biblioteca para ilustrar el uso de las **colecciones del Java Collections Framework** (`HashMap`, `ArrayList` y `HashSet`), junto con los principios de **orientación a objetos** (interfaces, clases abstractas, herencia y polimorfismo).

## 📋 Descripción

El sistema gestiona un catálogo de materiales (libros y revistas), una lista de espera por material cuando este no está disponible, y el registro de membresías de usuarios. A través de un programa de demostración (`main`) se ejercitan las operaciones típicas de cada colección:

- **`HashMap<String, Material>`** para el catálogo: búsqueda por código en tiempo constante.
- **`HashMap<String, List<String>>`** para las listas de espera: una `ArrayList` por material.
- **`HashSet<String>`** para las membresías: unicidad garantizada y consulta en tiempo constante.

## 🗂️ Estructura del proyecto

```
Colecciones-en-Java/
├── Catalogable.java        # Interfaz: contrato getCodigo()
├── Material.java           # Clase abstracta base de los materiales
├── Libro.java              # Material: libro (autor, ISBN)
├── Revista.java            # Material: revista (edición, temática)
├── SistemaBiblioteca.java   # Sistema principal + método main (demo)
├── Taller_Colecciones_Java.pdf
└── README.md
```

## 🧩 Modelo de dominio

### `Catalogable` (interfaz)
Define el contrato común para todo material catalogable:

```java
public interface Catalogable {
    String getCodigo();
}
```

### `Material` (clase abstracta)
Clase base que implementa `Catalogable`. Encapsula los atributos comunes (`codigo`, `titulo`, `año`, `disponible`) y declara el método abstracto `getTipo()`, que cada subclase implementa.

### `Libro` y `Revista`
Subclases concretas de `Material`:

| Clase | Atributos específicos | `getTipo()` |
|-------|-----------------------|-------------|
| `Libro` | `autor`, `isbn` | `"Libro"` |
| `Revista` | `edicion`, `tematica` | `"Revista"` |

El método `mostrarInfo()` usa polimorfismo en tiempo de ejecución para invocar el `getTipo()` correcto según el tipo real del objeto.

## ⚙️ Funcionalidades de `SistemaBiblioteca`

| Colección | Método | Descripción |
|-----------|--------|-------------|
| `HashMap` | `registrarMaterial(Material)` | Agrega un material al catálogo por su código. |
| `HashMap` | `buscarMaterial(String)` | Busca un material por código (`null` si no existe). |
| `HashMap` | `mostrarCatalogo()` | Recorre e imprime todos los materiales (polimorfismo). |
| `ArrayList` | `agregarEspera(codigo, usuario)` | Encola un usuario en la lista de espera del material. |
| `ArrayList` | `siguienteEnEspera(codigo)` | Retira y devuelve el primer usuario de la fila (FIFO). |
| `HashSet` | `registrarMiembro(usuario)` | Registra un miembro; `false` si ya existía. |
| `HashSet` | `tieneMembresía(usuario)` | Verifica si el usuario tiene membresía. |
| `HashSet` | `tieneMembresia(usuario)` | Alias sin acento (compatibilidad con evaluadores). |

## 🚀 Requisitos

- **JDK 8 o superior** (se usa sintaxis compatible con Java 8+).
- No se requieren dependencias externas.

## ▶️ Compilación y ejecución

Desde la raíz del proyecto:

```bash
# Compilar todos los archivos .java
javac *.java

# Ejecutar la demostración
java SistemaBiblioteca
```

En Windows (PowerShell / CMD) los comandos son equivalentes:

```powershell
javac *.java
java SistemaBiblioteca
```

## 🖥️ Salida esperada (resumen)

```
Buscar BK-001:
[Libro] Clean Code (2008) - disponible
Buscar XXX-999: null

Catalogo completo (polimorfismo):
[Libro] Clean Code (2008) - disponible
[Libro] Effective Java (2018) - disponible
[Revista] IEEE Software (2024) - disponible
[Revista] Nature (2024) - disponible

Siguiente en espera BK-001: Ana
Siguiente en espera BK-001: Luis

Registrar Ana: true
Registrar Luis: true
Registrar Maria: true
Registrar Ana de nuevo: false
Tiene membresia Ana: true
Tiene membresia Pedro: false
```

## 🎯 Objetivos de aprendizaje

- Comprender la diferencia entre `List`, `Set` y `Map`.
- Usar `HashMap` para búsquedas eficientes por clave.
- Usar `ArrayList` como cola FIFO (encolar al final, desencolar al inicio).
- Usar `HashSet` para garantizar unicidad de elementos.
- Aplicar herencia, clases abstractas, interfaces y polimorfismo.

## 📄 Licencia

Proyecto con fines educativos. Uso libre para aprendizaje y docencia.
```
C:\Users\USER\Downloads\Colecciones-en-Java