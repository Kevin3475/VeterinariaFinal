package co.edu.uniquindio.poo.veterinariafinal.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuViewController {

    public void abrirVentanaDueno(ActionEvent event) {
        abrirVentana("dueno.fxml", "Gestión de Dueños");
    }

    public void abrirVentanaPerro(ActionEvent event) {
        abrirVentana("perro.fxml", "Gestión de Perros");
    }

    public void abrirVentanaGato(ActionEvent event) {
        abrirVentana("gato.fxml", "Gestión de Gatos");
    }

    public void abrirVentanaAve(ActionEvent event) {
        abrirVentana("ave.fxml", "Gestión de Aves");
    }

    public void abrirVentanaReptil(ActionEvent event) {
        abrirVentana("reptil.fxml", "Gestión de Reptiles");
    }

    /**
     * Método genérico para abrir ventanas desde los botones del menú
     */
    public void abrirVentana(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/poo/veterinariafinal/" + fxml));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
