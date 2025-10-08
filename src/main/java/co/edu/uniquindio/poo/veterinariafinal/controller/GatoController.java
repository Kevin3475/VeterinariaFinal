package co.edu.uniquindio.poo.veterinariafinal.controller;

import co.edu.uniquindio.poo.veterinariafinal.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GatoController {

    private final ObservableList<Gato> listaGatos = FXCollections.observableArrayList();

    public ObservableList<Gato> getListaGatos() {
        return listaGatos;
    }

    public void registrarGato(String nombre, String raza, int edad, double pesoKg, String id,
                              Dueno dueno, TipoGato tipoGato, int horasSueno, String nivelIndependencia) {

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

        Gato gato = new Gato(nombre, raza, edad, pesoKg, id, dueno, tipoGato, horasSueno, nivelIndependencia);
        listaGatos.add(gato);
    }

    public void actualizarGato(Gato gato, String nombre, String raza, int edad, double pesoKg,
                               String id, Dueno dueno, TipoGato tipoGato, int horasSueno, String nivelIndependencia) {
        gato.setNombre(nombre);
        gato.setRaza(raza);
        gato.setEdad(edad);
        gato.setPesoKg(pesoKg);
        gato.setId(id);
        gato.setDueno(dueno);
        gato.setTipoGato(tipoGato);
        gato.setHorasSueno(horasSueno);
        gato.setNivelIndependencia(nivelIndependencia);
    }

    public void eliminarGato(Gato gato) {
        listaGatos.remove(gato);
    }

    public Gato buscarGatoPorId(String id) {
        return listaGatos.stream()
                .filter(gato -> gato.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}