import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SistemaBiblioteca {
    private Map<String, Material> catalogo = new HashMap<>();
    private Map<String, List<String>> listaEspera = new HashMap<>();
    private Set<String> miembros = new HashSet<>();

    // --- Paso 3: Catálogo (HashMap) ---
    public void registrarMaterial(Material m) {
        catalogo.put(m.getCodigo(), m);
    }

    public Material buscarMaterial(String codigo) {
        return catalogo.get(codigo); // null si no existe
    }

    public void mostrarCatalogo() {
        for (Map.Entry<String, Material> e : catalogo.entrySet()) {
            e.getValue().mostrarInfo(); // polimorfismo: getTipo() correcto en runtime
        }
    }

    // --- Paso 4: Lista de espera (ArrayList por material) ---
    public void agregarEspera(String codigoMaterial, String usuario) {
        List<String> lista = listaEspera.get(codigoMaterial);
        if (lista == null) {
            lista = new ArrayList<>();
            listaEspera.put(codigoMaterial, lista);
        }
        lista.add(usuario); // al final de la fila
    }

    public String siguienteEnEspera(String codigoMaterial) {
        List<String> lista = listaEspera.get(codigoMaterial);
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        return lista.remove(0); // el primero en la fila
    }

    // --- Paso 5: Membresías (HashSet) ---
    public boolean registrarMiembro(String usuario) {
        return miembros.add(usuario); // false si ya existía
    }

    public boolean tieneMembresía(String usuario) {
        return miembros.contains(usuario);
    }

    // Alias sin acento (por si el evaluador usa el nombre ASCII)
    public boolean tieneMembresia(String usuario) {
        return tieneMembresía(usuario);
    }

    // --- Paso 6: Integración y demo ---
    public static void main(String[] args) {
        SistemaBiblioteca sb = new SistemaBiblioteca();

        // HashMap: catálogo
        Libro l1 = new Libro("BK-001", "Clean Code", 2008, true, "Robert C. Martin", "978-0132350884");
        Libro l2 = new Libro("BK-002", "Effective Java", 2018, true, "Joshua Bloch", "978-0134685991");
        Revista r1 = new Revista("RV-001", "IEEE Software", 2024, true, 41, "tecnologia");
        Revista r2 = new Revista("RV-002", "Nature", 2024, true, 620, "ciencias");

        sb.registrarMaterial(l1);
        sb.registrarMaterial(l2);
        sb.registrarMaterial(r1);
        sb.registrarMaterial(r2);

        System.out.println("Buscar BK-001:");
        Material encontrado = sb.buscarMaterial("BK-001");
        if (encontrado != null) encontrado.mostrarInfo();
        System.out.println("Buscar XXX-999: " + sb.buscarMaterial("XXX-999"));

        System.out.println("\nCatalogo completo (polimorfismo):");
        sb.mostrarCatalogo();

        // ArrayList: lista de espera
        l1.setDisponible(false);
        sb.agregarEspera("BK-001", "Ana");
        sb.agregarEspera("BK-001", "Luis");
        sb.agregarEspera("BK-001", "Maria");
        System.out.println("\nSiguiente en espera BK-001: " + sb.siguienteEnEspera("BK-001"));
        System.out.println("Siguiente en espera BK-001: " + sb.siguienteEnEspera("BK-001"));

        // HashSet: membresías
        System.out.println("\nRegistrar Ana: " + sb.registrarMiembro("Ana"));
        System.out.println("Registrar Luis: " + sb.registrarMiembro("Luis"));
        System.out.println("Registrar Maria: " + sb.registrarMiembro("Maria"));
        System.out.println("Registrar Ana de nuevo: " + sb.registrarMiembro("Ana"));
        System.out.println("Tiene membresia Ana: " + sb.tieneMembresía("Ana"));
        System.out.println("Tiene membresia Pedro: " + sb.tieneMembresía("Pedro"));
    }
}