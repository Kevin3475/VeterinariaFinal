package co.edu.uniquindio.poo.veterinariafinal.controller;

import co.edu.uniquindio.poo.veterinariafinal.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PerroController {

    private final ObservableList<Perro> listaPerros = FXCollections.observableArrayList();

    public ObservableList<Perro> obtenerListaPerros() {
        return listaPerros;
    }

    public String guardarPerro(String nombre, String raza, int edad, double peso, String id,
                               String nombreDueno, TamanoPerro tamano, String nivelAdiestramiento,
                               String necesidadPaseo) {

        if (nombre.isEmpty() || raza.isEmpty() || id.isEmpty() || nombreDueno.isEmpty()
                || tamano == null || nivelAdiestramiento.isEmpty() || necesidadPaseo.isEmpty()) {
            return "⚠️ Todos los campos son obligatorios.";
        }

        if (edad <= 0) {
            return "⚠️ La edad debe ser positiva.";
        }

        if (peso <= 0) {
            return "⚠️ El peso debe ser positivo.";
        }

        // Verificar si ya existe un perro con ese ID
        if (buscarPerroPorId(id) != null) {
            return "⚠️ Ya existe un perro con ese ID.";
        }

        Dueno dueno = new Dueno("temp", nombreDueno, "N/A", "N/A", "0");
        Perro nuevoPerro = new Perro(nombre, raza, edad, peso, id, dueno, nivelAdiestramiento, necesidadPaseo, tamano);
        listaPerros.add(nuevoPerro);

        return "✅ Perro registrado correctamente.";
    }

    public void eliminarPerro(Perro perroSeleccionado) {
        listaPerros.remove(perroSeleccionado);
    }

    public void actualizarPerro(Perro perro, String nombre, String raza, int edad, double peso,
                                String id, Dueno dueno, TamanoPerro tamano, String nivelAdiestramiento,
                                String necesidadPaseo) {
        perro.setNombre(nombre);
        perro.setRaza(raza);
        perro.setEdad(edad);
        perro.setPesoKg(peso);
        perro.setId(id);
        perro.setDueno(dueno);
        perro.setTamanoPerro(tamano);
        perro.setNivelAdiestramiento(nivelAdiestramiento);
        perro.setNecesidadPaseo(necesidadPaseo);
    }

    public Perro buscarPerroPorId(String id) {
        return listaPerros.stream()
                .filter(perro -> perro.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}