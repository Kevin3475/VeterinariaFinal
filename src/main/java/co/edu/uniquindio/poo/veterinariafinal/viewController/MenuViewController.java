// MenuViewController.java CORREGIDO
package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MenuViewController {

    private Veterinaria veterinaria;

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
    }

    @FXML
    public void abrirVentanaDueno(ActionEvent event) {
        abrirVentanaConVeterinaria("dueno.fxml", "Gestión de Dueños");
    }

    @FXML
    public void abrirVentanaPerro(ActionEvent event) {
        abrirVentanaConVeterinaria("perro.fxml", "Gestión de Perros");
    }

    @FXML
    public void abrirVentanaGato(ActionEvent event) {
        abrirVentanaConVeterinaria("gato.fxml", "Gestión de Gatos");
    }

    @FXML
    public void abrirVentanaAve(ActionEvent event) {
        abrirVentanaConVeterinaria("ave.fxml", "Gestión de Aves");
    }

    @FXML
    public void abrirVentanaReptil(ActionEvent event) {
        abrirVentanaConVeterinaria("reptil.fxml", "Gestión de Reptiles");
    }

    /**
     * Método genérico para abrir ventanas inyectando la veterinaria - CORREGIDO
     */
    private void abrirVentanaConVeterinaria(String fxml, String titulo) {
        try {
            // 🔧 CORRECCIÓN: Usar getResource con la ruta correcta
            FXMLLoader loader = new FXMLLoader();

            // Intentar diferentes rutas hasta encontrar el archivo
            String[] posiblesRutas = {
                    "/co/edu/uniquindio/poo/veterinariafinal/" + fxml,
                    "/" + fxml,
                    fxml
            };

            Parent root = null;
            Exception ultimoError = null;

            for (String ruta : posiblesRutas) {
                try {
                    System.out.println("🔍 Buscando archivo en: " + ruta);
                    loader.setLocation(getClass().getResource(ruta));
                    root = loader.load();
                    System.out.println("✅ Archivo encontrado: " + ruta);
                    break; // Si se carga exitosamente, salir del bucle
                } catch (Exception e) {
                    ultimoError = e;
                    System.out.println("❌ No se encontró en: " + ruta);
                }
            }

            if (root == null) {
                throw new RuntimeException("No se pudo encontrar el archivo: " + fxml, ultimoError);
            }

            // Inyectar veterinaria según el tipo de controlador
            Object controller = loader.getController();

            if (controller instanceof DuenoViewController) {
                ((DuenoViewController) controller).setVeterinaria(veterinaria);
            } else if (controller instanceof PerroViewController) {
                ((PerroViewController) controller).setVeterinaria(veterinaria);
            } else if (controller instanceof GatoViewController) {
                ((GatoViewController) controller).setVeterinaria(veterinaria);
            } else if (controller instanceof AveViewController) {
                ((AveViewController) controller).setVeterinaria(veterinaria);
            } else if (controller instanceof ReptilViewController) {
                ((ReptilViewController) controller).setVeterinaria(veterinaria);
            }

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana: " + fxml + "\n\nError: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}