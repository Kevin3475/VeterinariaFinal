package co.edu.uniquindio.poo.veterinariafinal.controller;

import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.Reptil;
import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReptilController {

    private final ObservableList<Reptil> listaReptiles = FXCollections.observableArrayList();
    private final Veterinaria veterinaria;

    public ReptilController(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
    }

    public ObservableList<Reptil> getListaReptiles() {
        return listaReptiles;
    }

    public ObservableList<Dueno> getListaDuenos() {
        return veterinaria.getListDuenos(); // <--- usar directamente la lista de la veterinaria
    }

    public void agregarReptil(Reptil reptil) {
        listaReptiles.add(reptil);
        reptil.getDueno().getListMascotas().add(reptil);
    }

    public void actualizarReptil(Reptil original, Reptil actualizado) {
        int index = listaReptiles.indexOf(original);
        if (index >= 0) {
            listaReptiles.set(index, actualizado);
            original.getDueno().getListMascotas().remove(original);
            actualizado.getDueno().getListMascotas().add(actualizado);
        }
    }

    public void eliminarReptil(Reptil reptil) {
        listaReptiles.remove(reptil);
        reptil.getDueno().getListMascotas().remove(reptil);
    }
}

