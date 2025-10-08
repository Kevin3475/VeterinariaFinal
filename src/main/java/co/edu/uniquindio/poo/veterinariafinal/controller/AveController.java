package co.edu.uniquindio.poo.veterinariafinal.controller;

import co.edu.uniquindio.poo.veterinariafinal.model.Ave;
import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.Vuela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AveController {

    private final ObservableList<Ave> listaAves = FXCollections.observableArrayList();

    public ObservableList<Ave> getListaAves() {
        return listaAves;
    }

    public void agregarAve(String nombre, String raza, int edad, double pesoKg, String id, Dueno dueno,
                           String tipoPlumaje, int sonidosImitados, Vuela vuela) {

        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser positiva");
        }
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }
        if (dueno == null) {
            throw new IllegalArgumentException("El dueño no puede ser nulo");
        }

        Ave ave = new Ave(nombre, raza, edad, pesoKg, id, dueno, tipoPlumaje, sonidosImitados, vuela);
        listaAves.add(ave);
    }

    public void eliminarAve(Ave ave) {
        listaAves.remove(ave);
    }

    public Ave buscarAvePorId(String id) {
        return listaAves.stream()
                .filter(ave -> ave.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}