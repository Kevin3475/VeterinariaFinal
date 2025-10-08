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
        return veterinaria != null ? veterinaria.getListDuenos() : FXCollections.observableArrayList();
    }

    public void agregarReptil(Reptil reptil) {
        // Validaciones
        if (reptil.getNombre() == null || reptil.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (reptil.getEdad() <= 0) {
            throw new IllegalArgumentException("La edad debe ser positiva");
        }
        if (reptil.getPesoKg() <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }
        if (reptil.getDueno() == null) {
            throw new IllegalArgumentException("El dueño no puede ser nulo");
        }

        listaReptiles.add(reptil);
        if (veterinaria != null) {
            reptil.getDueno().getListMascotas().add(reptil);
        }
    }

    public void actualizarReptil(Reptil original, Reptil actualizado) {
        int index = listaReptiles.indexOf(original);
        if (index >= 0) {
            listaReptiles.set(index, actualizado);
            if (veterinaria != null) {
                original.getDueno().getListMascotas().remove(original);
                actualizado.getDueno().getListMascotas().add(actualizado);
            }
        }
    }

    public void eliminarReptil(Reptil reptil) {
        listaReptiles.remove(reptil);
        if (veterinaria != null) {
            reptil.getDueno().getListMascotas().remove(reptil);
        }
    }

    public Reptil buscarReptilPorId(String id) {
        return listaReptiles.stream()
                .filter(reptil -> reptil.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}