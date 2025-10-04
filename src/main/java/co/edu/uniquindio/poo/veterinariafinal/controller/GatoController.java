package co.edu.uniquindio.poo.veterinariafinal.controller;

import co.edu.uniquindio.poo.veterinariafinal.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GatoController {

    private final ObservableList<Gato> listaGatos = FXCollections.observableArrayList();

    public ObservableList<Gato> getListaGatos() {
        return listaGatos;
    }

    /**
     * Registra un nuevo gato en la lista.
     */
    public void registrarGato(String nombre, String raza, int edad, double pesoKg, String id,
                              Dueno dueno, TipoGato tipoGato, int horasSueno, String nivelIndependencia) {

        Gato gato = new Gato(nombre, raza, edad, pesoKg, id, dueno, tipoGato, horasSueno, nivelIndependencia);
        listaGatos.add(gato);
    }

    /**
     * Actualiza la información de un gato seleccionado.
     */
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

    /**
     * Elimina un gato de la lista.
     */
    public void eliminarGato(Gato gato) {
        listaGatos.remove(gato);
    }
}
