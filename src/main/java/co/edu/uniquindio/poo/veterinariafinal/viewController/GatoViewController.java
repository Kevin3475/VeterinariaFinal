// GatoViewController.java
package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.GatoController;
import co.edu.uniquindio.poo.veterinariafinal.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

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
    @FXML private TableColumn<Gato, String> colDueno;
    @FXML private TableColumn<Gato, TipoGato> colTipoGato;
    @FXML private TableColumn<Gato, Integer> colHorasSueno;
    @FXML private TableColumn<Gato, String> colNivelIndependencia;

    private GatoController gatoController;
    private Veterinaria veterinaria;

    @FXML
    public void initialize() {
        gatoController = new GatoController();
        comboTipoGato.setItems(FXCollections.observableArrayList(TipoGato.values()));
        configurarColumnas();
        tablaGatos.setItems(gatoController.getListaGatos());
    }

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        if (veterinaria != null) {
            ObservableList<Dueno> listaDuenos = veterinaria.getListDuenos();
            comboDueno.setItems(listaDuenos);

            // Configurar cómo mostrar los dueños
            comboDueno.setCellFactory(lv -> new ListCell<Dueno>() {
                @Override
                protected void updateItem(Dueno item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? "" : item.getNombre() + " - " + item.getId());
                }
            });

            comboDueno.setButtonCell(new ListCell<Dueno>() {
                @Override
                protected void updateItem(Dueno item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? "" : item.getNombre() + " - " + item.getId());
                }
            });
        }
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colRaza.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRaza()));
        colEdad.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getEdad()));
        colPeso.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getPesoKg()));
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colDueno.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getDueno() != null ? cell.getValue().getDueno().getNombre() : "Sin dueño"));
        colTipoGato.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getTipoGato()));
        colHorasSueno.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getHorasSueno()));
        colNivelIndependencia.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNivelIndependencia()));
    }

    @FXML
    private void registrarGato() {
        try {
            if (!validarCampos()) return;

            Dueno duenoSeleccionado = comboDueno.getValue();
            if (duenoSeleccionado == null) {
                mostrarAlerta("Error", "Debe seleccionar un dueño", Alert.AlertType.ERROR);
                return;
            }

            gatoController.registrarGato(
                    txtNombre.getText(),
                    txtRaza.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    Double.parseDouble(txtPesoKg.getText()),
                    txtId.getText(),
                    duenoSeleccionado,
                    comboTipoGato.getValue(),
                    Integer.parseInt(txtHorasSueno.getText()),
                    txtNivelIndependencia.getText()
            );

            mostrarAlerta("Éxito", "Gato registrado correctamente", Alert.AlertType.INFORMATION);
            limpiarCampos();
            tablaGatos.refresh();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Verifica los campos numéricos (edad, peso, horas de sueño).", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al registrar el gato.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void actualizarGato() {
        Gato seleccionado = tablaGatos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                if (!validarCampos()) return;

                Dueno duenoSeleccionado = comboDueno.getValue();
                if (duenoSeleccionado == null) {
                    mostrarAlerta("Error", "Debe seleccionar un dueño", Alert.AlertType.ERROR);
                    return;
                }

                gatoController.actualizarGato(
                        seleccionado,
                        txtNombre.getText(),
                        txtRaza.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        Double.parseDouble(txtPesoKg.getText()),
                        txtId.getText(),
                        duenoSeleccionado,
                        comboTipoGato.getValue(),
                        Integer.parseInt(txtHorasSueno.getText()),
                        txtNivelIndependencia.getText()
                );

                mostrarAlerta("Éxito", "Gato actualizado correctamente", Alert.AlertType.INFORMATION);
                tablaGatos.refresh();
                limpiarCampos();

            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Verifica los campos numéricos (edad, peso, horas de sueño).", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "Seleccione un gato para actualizar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void eliminarGato() {
        Gato seleccionado = tablaGatos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            gatoController.eliminarGato(seleccionado);
            mostrarAlerta("Éxito", "Gato eliminado correctamente", Alert.AlertType.INFORMATION);
            tablaGatos.refresh();
        } else {
            mostrarAlerta("Advertencia", "Seleccione un gato para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void seleccionarGato() {
        Gato seleccionado = tablaGatos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtNombre.setText(seleccionado.getNombre());
            txtRaza.setText(seleccionado.getRaza());
            txtEdad.setText(String.valueOf(seleccionado.getEdad()));
            txtPesoKg.setText(String.valueOf(seleccionado.getPesoKg()));
            txtId.setText(seleccionado.getId());
            txtHorasSueno.setText(String.valueOf(seleccionado.getHorasSueno()));
            txtNivelIndependencia.setText(seleccionado.getNivelIndependencia());
            comboTipoGato.setValue(seleccionado.getTipoGato());

            // Buscar y seleccionar el dueño en el ComboBox
            if (seleccionado.getDueno() != null && veterinaria != null) {
                for (Dueno dueno : veterinaria.getListDuenos()) {
                    if (dueno.getId().equals(seleccionado.getDueno().getId())) {
                        comboDueno.setValue(dueno);
                        break;
                    }
                }
            }
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

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtRaza.getText().isEmpty() || txtId.getText().isEmpty() ||
                txtHorasSueno.getText().isEmpty() || txtNivelIndependencia.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return false;
        }
        if (comboTipoGato.getValue() == null) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de gato", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}