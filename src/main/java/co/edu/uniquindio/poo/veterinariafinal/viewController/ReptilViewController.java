package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.ReptilController;
import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.NivelPeligro;
import co.edu.uniquindio.poo.veterinariafinal.model.Reptil;
import co.edu.uniquindio.poo.veterinariafinal.model.TipoHabitat;
import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ReptilViewController implements Initializable {

    // Campos de formulario
    @FXML private TextField txtId, txtNombre, txtRaza, txtEdad, txtPeso, txtTemperatura;
    @FXML private ComboBox<TipoHabitat> comboHabitat;
    @FXML private ComboBox<NivelPeligro> comboPeligro;
    @FXML private ComboBox<Dueno> comboDueno;

    // Tabla y columnas
    @FXML private TableView<Reptil> tablaReptiles;
    @FXML private TableColumn<Reptil, String> colId, colNombre, colRaza, colEdad, colPeso,
            colTemperatura, colHabitat, colPeligro, colDueno;

    private Veterinaria veterinaria;
    private ReptilController controller;

    /** Método para inyectar la veterinaria compartida desde App o el controlador principal */
    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        this.controller = new ReptilController(veterinaria);

        // Vincular ComboBox de dueños
        comboDueno.setItems(veterinaria.getListDuenos());

        // Vincular tabla a lista de reptiles
        tablaReptiles.setItems(controller.getListaReptiles());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Llenar ComboBoxes con enumeraciones
        comboHabitat.getItems().addAll(TipoHabitat.values());
        comboPeligro.getItems().addAll(NivelPeligro.values());

        // Configurar visualización de dueños en ComboBox
        comboDueno.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Dueno item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre());
            }
        });
        comboDueno.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Dueno item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre());
            }
        });

        // Inicializar columnas de tabla
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colRaza.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRaza()));
        colEdad.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getEdad())));
        colPeso.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPesoKg())));
        colTemperatura.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTemperaturaOptima()));
        colHabitat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoHabitat().toString()));
        colPeligro.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNivelPeligro().toString()));
        colDueno.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getDueno() != null ? data.getValue().getDueno().getNombre() : ""
        ));
    }

    // =========================
    // MÉTODOS DE ACCIÓN
    // =========================

    @FXML
    private void agregarReptil(javafx.event.ActionEvent event) {
        try {
            Dueno duenoSeleccionado = comboDueno.getValue();
            if (duenoSeleccionado == null) {
                mostrarAlerta("Debe seleccionar un dueño.");
                return;
            }

            Reptil reptil = new Reptil(
                    txtNombre.getText(),
                    txtRaza.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    Double.parseDouble(txtPeso.getText()),
                    txtId.getText(),
                    duenoSeleccionado,
                    txtTemperatura.getText(),
                    comboHabitat.getValue(),
                    comboPeligro.getValue()
            );

            controller.agregarReptil(reptil);
            tablaReptiles.refresh();
            limpiarCampos(event);
        } catch (NumberFormatException e) {
            mostrarAlerta("Edad y Peso deben ser números válidos.");
        }
    }

    @FXML
    private void actualizarReptil(javafx.event.ActionEvent event) {
        Reptil seleccionado = tablaReptiles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                Dueno duenoSeleccionado = comboDueno.getValue();
                if (duenoSeleccionado == null) {
                    mostrarAlerta("Debe seleccionar un dueño.");
                    return;
                }

                Reptil actualizado = new Reptil(
                        txtNombre.getText(),
                        txtRaza.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        Double.parseDouble(txtPeso.getText()),
                        txtId.getText(),
                        duenoSeleccionado,
                        txtTemperatura.getText(),
                        comboHabitat.getValue(),
                        comboPeligro.getValue()
                );

                controller.actualizarReptil(seleccionado, actualizado);
                tablaReptiles.refresh();
                limpiarCampos(event);
            } catch (NumberFormatException e) {
                mostrarAlerta("Edad y Peso deben ser números válidos.");
            }
        } else {
            mostrarAlerta("Seleccione un reptil para actualizar.");
        }
    }

    @FXML
    private void eliminarReptil(javafx.event.ActionEvent event) {
        Reptil seleccionado = tablaReptiles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            controller.eliminarReptil(seleccionado);
            tablaReptiles.refresh();
        } else {
            mostrarAlerta("Seleccione un reptil para eliminar.");
        }
    }

    @FXML
    private void limpiarCampos(javafx.event.ActionEvent event) {
        txtId.clear();
        txtNombre.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtTemperatura.clear();
        comboHabitat.getSelectionModel().clearSelection();
        comboPeligro.getSelectionModel().clearSelection();
        comboDueno.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
