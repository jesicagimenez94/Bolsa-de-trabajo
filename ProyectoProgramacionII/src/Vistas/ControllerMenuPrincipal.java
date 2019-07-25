package Vistas;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControllerMenuPrincipal {

    MostrarVistas mos= new MostrarVistas();

    @FXML
    public void Registrarse(MouseEvent event){
        mos.mostrarVistaRegistros();

    }


}
