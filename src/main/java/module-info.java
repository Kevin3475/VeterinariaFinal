module co.edu.uniquindio.poo.veterinariafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens co.edu.uniquindio.poo.veterinariafinal to javafx.fxml;
    opens co.edu.uniquindio.poo.veterinariafinal.model to javafx.base;
    opens co.edu.uniquindio.poo.veterinariafinal.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.veterinariafinal.controller to javafx.fxml;

    exports co.edu.uniquindio.poo.veterinariafinal;
    exports co.edu.uniquindio.poo.veterinariafinal.model;
    exports co.edu.uniquindio.poo.veterinariafinal.viewController;
    exports co.edu.uniquindio.poo.veterinariafinal.controller;
}