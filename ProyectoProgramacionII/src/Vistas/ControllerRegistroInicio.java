package Vistas;


import Clases.Registros;
import Conexion.Hash;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Mensajes.Mensajes;

import java.io.IOException;

public class ControllerRegistroInicio  {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasenia;
    @FXML
    private PasswordField txtRepetirContrasenia;
    @FXML
    private ComboBox tipoUsuario;
    @FXML
    private Button btnregistrar;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnInicio;



    @FXML
    public void inicioSesion(javafx.scene.input.MouseEvent mouseEvent) {
        Registros reg= new Registros();
        Mensajes met= new Mensajes();

        if((!txtUser.getText().equals(""))&&(!txtPass.getText().equals(""))){ //en caso que usuario y contraseña sean distinto a campo vacio

            String nuevoPas= Hash.sha1(txtPass.getText());
            reg.setUsuario(txtUser.getText());
            reg.setContrasenia(nuevoPas);
            if ((txtPass.getText().length()>=6 )&&(txtPass.getText().length()<=8 )){ //cantidad min de caracteres 6 y maxima 8
                if(reg.login(reg)){//si devuelve true el metodo login inicia sesion


                }else{
                    met.MensajeError("Datos incorrectos");
                }

            }else{
                met.MensajeError("cantidad de caracteres invalidos");
            }
        }else{
            met.MensajeError("Hay campos vacios");
        }
    }

    public void initialize() {

        tipoUsuario.setValue("---");
        tipoUsuario.setItems(ListaTipo);

    }




    ObservableList<String> ListaTipo = FXCollections.observableArrayList("Alumno", "Empresa");

    Mensajes met= new Mensajes();
    Registros reg = new Registros();

    public void limpiar(){
        txtRepetirContrasenia.setText("");
        txtContrasenia.setText("");
        txtUsuario.setText("");
        tipoUsuario.setSelectionModel(null);

    }



    @FXML
    public void registrar(MouseEvent mouseEvent) throws IOException {
        if (txtUsuario.getText().equals("") || txtContrasenia.getText().equals("") || txtRepetirContrasenia.getText().equals("")||tipoUsuario.getSelectionModel().getSelectedItem().equals("---")) {//comprueba si hay campos vacios
            met.MensajeError("Hay campos vacios");
        } else {//si no hay campos vacios pasa a la siguiente validacion

            if (txtContrasenia.getText().equals(txtRepetirContrasenia.getText())) {//valida si la contraseña ingresada es la misma en los dos campos en caso que sea igual sigue al siguiente paso
                if (((txtContrasenia.getText().length() >= 6) && (txtContrasenia.getText().length() <= 8)) && ((txtRepetirContrasenia.getText().length() >= 6) && (txtRepetirContrasenia.getText().length() <= 8))) {
                    //verifica que la contraseña tenga la cantidad minima de caracteres : cantidad min de caracteres 6 y maxima 8
                    if (reg.existeUsuario(txtUsuario.getText()) == 0) {//en caso que el metodo retorne 0 significa que no hay usuarios con ese nombre y se puede seguir al siguiente paso.

                        String nuevoPass = Hash.sha1(txtContrasenia.getText());// cifrado de contraseña
                        reg.setUsuario(txtUsuario.getText());
                        reg.setContrasenia(nuevoPass);

                        String opcion = (String) tipoUsuario.getSelectionModel().getSelectedItem();//si tipo de usuario es igual alumno retorna 1 en otro caso 2
                        if (opcion.equalsIgnoreCase("Alumno")) {
                            reg.setId_tipoUsuario(1);
                        } else {
                            reg.setId_tipoUsuario(2);
                        }

                        if (reg.Registrar(reg)){
                            limpiar();
                            met.mensajeOk("Registro exitoso");//si el registro es exitoso se abre la ventana de login
                            try{

                                FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("RegistroInicio.fxml"));//trae la ventana de login
                                Parent root1= (Parent) fxmlLoader.load();
                                Stage stage= new Stage();
                                stage.setScene(new Scene(root1));
                                stage.show();

                            }catch (Exception e){e.printStackTrace();
                            }


                        } else {
                            met.MensajeError("Registro invalido");
                        }
                    } else {

                        met.MensajeError("Ya hay un usuario con ese nombre");//en caso que retorne !=0 significa que ya hay un usuario con ese nombre
                    }

                } else {
                    met.MensajeError("Cantidad de caracteres invalidos");
                }
            } else {
                met.MensajeError("Las contraseñas no coinciden");
            }


        }
    }

}
