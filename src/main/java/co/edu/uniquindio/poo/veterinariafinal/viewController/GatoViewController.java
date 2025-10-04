package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.GatoController;
import co.edu.uniquindio.poo.veterinariafinal.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class GatoViewController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtRaza;
    @FXML private TextField txtEdad;
    @FXML private TextField txtPesoKg;
    @FXML private TextField txtId;
    @FXML private ComboBox<Dueno> comboDueno;
    @FXML private ComboBox<TipoGato> comboTipoGato;
    @FXML private TextField txtHorasSueno;
    @FXML private TextField txtNivelIndependencia;

    @FXML private TableView<Gato> tablaGatos;
    @FXML private TableColumn<Gato, String> colNombre;
    @FXML private TableColumn<Gato, String> colRaza;
    @FXML private TableColumn<Gato, Integer> colEdad;
    @FXML private TableColumn<Gato, Double> colPeso;
    @FXML private TableColumn<Gato, String> colId;
    @FXML private TableColumn<Gato, Dueno> colDueno;
    @FXML private TableColumn<Gato, TipoGato> colTipoGato;
    @FXML private TableColumn<Gato, Integer> colHorasSueno;
    @FXML private TableColumn<Gato, String> colNivelIndependencia;

    private final GatoController gatoController = new GatoController();

    @FXML
    public void initialize() {
        comboTipoGato.setItems(FXCollections.observableArrayList(TipoGato.values()));
        configurarColumnas();
        tablaGatos.setItems(gatoController.getListaGatos());
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colRaza.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRaza()));
        colEdad.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getEdad()));
        colPeso.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getPesoKg()));
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId()));
        colDueno.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getDueno()));
        colTipoGato.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getTipoGato()));
        colHorasSueno.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getHorasSueno()));
        colNivelIndependencia.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNivelIndependencia()));
    }

    @FXML
    private void registrarGato() {
        try {
            gatoController.registrarGato(
                    txtNombre.getText(),
                    txtRaza.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    Double.parseDouble(txtPesoKg.getText()),
                    txtId.getText(),
                    comboDueno.getValue(),
                    comboTipoGato.getValue(),
                    Integer.parseInt(txtHorasSueno.getText()),
                    txtNivelIndependencia.getText()
            );
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Verifica los campos numéricos (edad, peso, horas de sueño).", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al registrar el gato.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void actualizarGato() {
        Gato seleccionado = tablaGatos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            gatoController.actualizarGato(
                    seleccionado,
                    txtNombre.getText(),
                    txtRaza.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    Double.parseDouble(txtPesoKg.getText()),
                    txtId.getText(),
                    comboDueno.getValue(),
                    comboTipoGato.getValue(),
                    Integer.parseInt(txtHorasSueno.getText()),
                    txtNivelIndependencia.getText()
            );
            tablaGatos.refresh();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarGato() {
        Gato seleccionado = tablaGatos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            gatoController.eliminarGato(seleccionado);
        }
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPesoKg.clear();
        txtId.clear();
        comboDueno.setValue(null);
        comboTipoGato.setValue(null);
        txtHorasSueno.clear();
        txtNivelIndependencia.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
