package co.edu.uniquindio.poo.veterinariafinal.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Veterinaria {

    private String nombre;
    private String nit;
    private ObservableList<Mascota> listMascotas;
    private List<Consulta> listConsultas;
    private ObservableList<Dueno> listDuenos;

    public Veterinaria(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        this.listMascotas = FXCollections.observableArrayList();
        this.listConsultas = new ArrayList<>();
        this.listDuenos = FXCollections.observableArrayList();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public List<Consulta> getListConsultas() { return listConsultas; }
    public void setListConsultas(List<Consulta> listConsultas) { this.listConsultas = listConsultas; }
    public ObservableList<Mascota> getListMascotas() { return listMascotas; }
    public void setListMascotas(ObservableList<Mascota> listMascotas) { this.listMascotas = listMascotas; }
    public ObservableList<Dueno> getListDuenos() { return listDuenos; }
    public void setListDuenos(ObservableList<Dueno> listDuenos) { this.listDuenos = listDuenos; }

    // Métodos para Dueños
    public boolean agregarDueno(Dueno dueno) {
        if (buscarDuenoPorId(dueno.getId()) == null) {
            listDuenos.add(dueno);
            return true;
        }
        return false;
    }

    public Dueno buscarDuenoPorId(String id) {
        return listDuenos.stream()
                .filter(dueno -> dueno.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarDueno(String id) {
        Dueno dueno = buscarDuenoPorId(id);
        if (dueno != null) {
            // Verificar si el dueño tiene mascotas
            if (!dueno.getListMascotas().isEmpty()) {
                return false; // No se puede eliminar dueño con mascotas
            }
            listDuenos.remove(dueno);
            return true;
        }
        return false;
    }

    public boolean actualizarDueno(Dueno duenoActualizado) {
        Dueno duenoExistente = buscarDuenoPorId(duenoActualizado.getId());
        if (duenoExistente != null) {
            duenoExistente.setNombre(duenoActualizado.getNombre());
            duenoExistente.setNumero(duenoActualizado.getNumero());
            duenoExistente.setPuntajeFidelidad(duenoActualizado.getPuntajeFidelidad());
            duenoExistente.setDireccion(duenoActualizado.getDireccion());
            return true;
        }
        return false;
    }

    // Métodos para Mascotas
    public boolean agregarMascota(Mascota mascota) {
        if (buscarMascotaPorCodigo(mascota.getId()) == null) {
            listMascotas.add(mascota);
            // Agregar la mascota a la lista del dueño
            if (mascota.getDueno() != null) {
                mascota.getDueno().getListMascotas().add(mascota);
            }
            return true;
        }
        return false;
    }

    public Mascota buscarMascotaPorCodigo(String id) {
        return listMascotas.stream()
                .filter(mascota -> mascota.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarMascota(String codigo) {
        Mascota mascota = buscarMascotaPorCodigo(codigo);
        if (mascota != null) {
            listMascotas.remove(mascota);
            // Remover la mascota de la lista del dueño
            if (mascota.getDueno() != null) {
                mascota.getDueno().getListMascotas().remove(mascota);
            }
            return true;
        }
        return false;
    }

    // Filtros por tipo de mascota
    public List<Perro> getListaPerros() {
        return listMascotas.stream()
                .filter(m -> m instanceof Perro)
                .map(m -> (Perro) m)
                .collect(Collectors.toList());
    }

    public List<Gato> getListaGatos() {
        return listMascotas.stream()
                .filter(m -> m instanceof Gato)
                .map(m -> (Gato) m)
                .collect(Collectors.toList());
    }

    public List<Ave> getListaAves() {
        return listMascotas.stream()
                .filter(m -> m instanceof Ave)
                .map(m -> (Ave) m)
                .collect(Collectors.toList());
    }

    public List<Reptil> getListaReptiles() {
        return listMascotas.stream()
                .filter(m -> m instanceof Reptil)
                .map(m -> (Reptil) m)
                .collect(Collectors.toList());
    }

    // Ranking de dueños frecuentes
    public List<Dueno> rankingDuenosFrecuentes() {
        Map<Dueno, Long> contador = listConsultas.stream()
                .collect(Collectors.groupingBy(c -> c.getMascota().getDueno(), Collectors.counting()));

        return contador.entrySet().stream()
                .sorted(Map.Entry.<Dueno, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Veterinaria{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", listMascotas=" + listMascotas.size() +
                ", listConsultas=" + listConsultas.size() +
                ", listDuenos=" + listDuenos.size() +
                '}';
    }
}