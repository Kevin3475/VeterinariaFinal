// PerroViewController.java
package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.PerroController;
import co.edu.uniquindio.poo.veterinariafinal.model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class PerroViewController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtRaza;
    @FXML private TextField txtEdad;
    @FXML private TextField txtPeso;
    @FXML private TextField txtId;
    @FXML private ComboBox<Dueno> comboDueno;
    @FXML private ComboBox<TamanoPerro> comboTamano;
    @FXML private TextField txtAdiestramiento;
    @FXML private TextField txtPaseo;
    @FXML private Label lblMensaje;

    @FXML private TableView<Perro> tablaPerros;
    @FXML private TableColumn<Perro, String> colNombre;
    @FXML private TableColumn<Perro, String> colRaza;
    @FXML private TableColumn<Perro, Integer> colEdad;
    @FXML private TableColumn<Perro, Double> colPeso;
    @FXML private TableColumn<Perro, String> colId;
    @FXML private TableColumn<Perro, String> colDueno;
    @FXML private TableColumn<Perro, TamanoPerro> colTamano;
    @FXML private TableColumn<Perro, String> colAdiestramiento;
    @FXML private TableColumn<Perro, String> colPaseo;

    private PerroController perroController;
    private Veterinaria veterinaria;

    @FXML
    public void initialize() {
        perroController = new PerroController();
        comboTamano.getItems().addAll(TamanoPerro.values());
        configurarTabla();
    }

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        // Cargar lista de dueños registrados en el ComboBox
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

    private void configurarTabla() {
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colRaza.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRaza()));
        colEdad.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getEdad()));
        colPeso.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getPesoKg()));
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colDueno.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getDueno() != null ? cell.getValue().getDueno().getNombre() : "Sin dueño"));
        colTamano.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getTamanoPerro()));
        colAdiestramiento.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNivelAdiestramiento()));
        colPaseo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNecesidadPaseo()));

        tablaPerros.setItems(perroController.obtenerListaPerros());
    }

    @FXML
    private void guardarPerro() {
        try {
            if (!validarCampos()) return;

            Dueno duenoSeleccionado = comboDueno.getValue();
            if (duenoSeleccionado == null) {
                mostrarMensaje("⚠️ Debes seleccionar un dueño.", true);
                return;
            }

            String resultado = perroController.guardarPerro(
                    txtNombre.getText(), txtRaza.getText(),
                    Integer.parseInt(txtEdad.getText()), Double.parseDouble(txtPeso.getText()),
                    txtId.getText(), duenoSeleccionado.getNombre(),
                    comboTamano.getValue(), txtAdiestramiento.getText(), txtPaseo.getText()
            );

            mostrarMensaje(resultado, !resultado.startsWith("✅"));
            if (resultado.startsWith("✅")) {
                limpiarCampos();
                tablaPerros.refresh();
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("⚠️ Edad y peso deben ser numéricos.", true);
        } catch (Exception e) {
            mostrarMensaje("❌ Error inesperado.", true);
            e.printStackTrace();
        }
    }

    @FXML
    private void actualizarPerro() {
        Perro perroSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        if (perroSeleccionado != null) {
            try {
                if (!validarCampos()) return;

                Dueno duenoSeleccionado = comboDueno.getValue();
                if (duenoSeleccionado == null) {
                    mostrarMensaje("⚠️ Debes seleccionar un dueño.", true);
                    return;
                }

                perroController.actualizarPerro(
                        perroSeleccionado, txtNombre.getText(), txtRaza.getText(),
                        Integer.parseInt(txtEdad.getText()), Double.parseDouble(txtPeso.getText()),
                        txtId.getText(), duenoSeleccionado, comboTamano.getValue(),
                        txtAdiestramiento.getText(), txtPaseo.getText()
                );

                mostrarMensaje("✅ Perro actualizado correctamente.", false);
                tablaPerros.refresh();
                limpiarCampos();

            } catch (NumberFormatException e) {
                mostrarMensaje("⚠️ Edad y peso deben ser numéricos.", true);
            }
        } else {
            mostrarMensaje("⚠️ Seleccione un perro para actualizar.", true);
        }
    }

    @FXML
    private void eliminarPerro() {
        Perro perroSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        if (perroSeleccionado != null) {
            perroController.eliminarPerro(perroSeleccionado);
            mostrarMensaje("✅ Perro eliminado correctamente.", false);
            tablaPerros.refresh();
        } else {
            mostrarMensaje("⚠️ Seleccione un perro para eliminar.", true);
        }
    }

    @FXML
    private void seleccionarPerro() {
        Perro perroSeleccionado = tablaPerros.getSelectionModel().getSelectedItem();
        if (perroSeleccionado != null) {
            txtNombre.setText(perroSeleccionado.getNombre());
            txtRaza.setText(perroSeleccionado.getRaza());
            txtEdad.setText(String.valueOf(perroSeleccionado.getEdad()));
            txtPeso.setText(String.valueOf(perroSeleccionado.getPesoKg()));
            txtId.setText(perroSeleccionado.getId());
            txtAdiestramiento.setText(perroSeleccionado.getNivelAdiestramiento());
            txtPaseo.setText(perroSeleccionado.getNecesidadPaseo());
            comboTamano.setValue(perroSeleccionado.getTamanoPerro());
            // Buscar y seleccionar el dueño en el ComboBox
            if (perroSeleccionado.getDueno() != null && veterinaria != null) {
                for (Dueno dueno : veterinaria.getListDuenos()) {
                    if (dueno.getId().equals(perroSeleccionado.getDueno().getId())) {
                        comboDueno.setValue(dueno);
                        break;
                    }
                }
            }
        }
    }

    @FXML
    private void cancelar() {
        limpiarCampos();
        lblMensaje.setText("");
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtRaza.getText().isEmpty() || txtId.getText().isEmpty() ||
                txtAdiestramiento.getText().isEmpty() || txtPaseo.getText().isEmpty()) {
            mostrarMensaje("⚠️ Todos los campos son obligatorios.", true);
            return false;
        }
        if (comboTamano.getValue() == null) {
            mostrarMensaje("⚠️ Debes seleccionar un tamaño.", true);
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtId.clear();
        txtAdiestramiento.clear();
        txtPaseo.clear();
        comboTamano.setValue(null);
        comboDueno.setValue(null);
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        lblMensaje.setStyle(esError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }
}