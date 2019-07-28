
package Mensajes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;


public class Mensajes {
    
    public void mensajeOk(String mensaje){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("OK");
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
    }

    public void MensajeError(String mensaje){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    
}
