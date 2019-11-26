package Vistas;


import Clases.Registros;
import Conexion.Conectar;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Mensajes.Mensajes;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public String grupo;

    public boolean login(Clases.Registros user){ //metodo para iniciar sesion
        Conectar con = new Conectar();

        try {
            PreparedStatement ps=null;
            ResultSet rs= null;

            String sql="SELECT u.id_registros,u.usuario,u.contrasenia,u.id_tipousuario,g.nombre FROM usuarios u JOIN grupos_usuarios gu ON u.id_registros=gu.id_usuario JOIN grupos g ON g.id_grupo=gu.id_grupo WHERE  u.usuario=?";


            MostrarVistas m= new MostrarVistas();
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1,user.getUsuario());
            rs =ps.executeQuery();//para que me devuelva datos porque es una consulta

            if(rs.next()){
                ControllerMenuBusquedas c= new ControllerMenuBusquedas();
                c.setNombre_grupo(rs.getString(5));
                grupo= rs.getString("nombre");

                System.out.println(c.getNombre_grupo());
                if(user.getContrasenia().equals(rs.getString(3))&& user.getUsuario().equals(rs.getString(2))){//en caso que las contraseñas coincidan
                    user.setId_registros(1);



                }

                //devuelve true solo cuando contraseña existe

                return true;

            }

            else{

                //si contraseña no existe devuelve falso
                return false;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }


    @FXML
    public void inicioSesion(MouseEvent mouseEvent) {
        Registros reg= new Registros();
        Mensajes met= new Mensajes();
        MostrarVistas m = new MostrarVistas();

        if((!txtUser.getText().equals(""))&&(!txtPass.getText().equals(""))){ //en caso que usuario y contraseña sean distinto a campo vacio

            String nuevoPas= Hash.sha1(txtPass.getText());
            reg.setUsuario(txtUser.getText());
            reg.setContrasenia(nuevoPas);
            if ((txtPass.getText().length()>=6 )&&(txtPass.getText().length()<=8 )){ //cantidad min de caracteres 6 y maxima 8
              if(login(reg) ){//si devuelve true el metodo login inicia sesion
                  try{
                      FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("MenuBusquedas1.fxml"));//trae la ventana de emresas
                      Parent root= (Parent) fxmlLoader.load();
                      Stage stage= new Stage();
                      stage.setScene(new Scene(root));
                      Image icon = new Image(getClass().getResourceAsStream("BW-Automotive-bolsa-icono.png"));
                      stage.getIcons().add(icon);
                      stage.setTitle("Empresas");
                      ControllerMenuBusquedas c= new ControllerMenuBusquedas();
                      System.out.println( "valor grupo"+grupo);
                      c.traerPermisos(grupo);
                      stage.show();

                  }catch (Exception e)
                  {e.printStackTrace();
                      System.out.println(e);
                  }
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


    }




    ObservableList<String> ListaTipo = FXCollections.observableArrayList("Alumno", "Empresa");

    Mensajes met= new Mensajes();
    Registros reg = new Registros();

    public void limpiar(){
        txtRepetirContrasenia.setText("");
        txtContrasenia.setText("");
        txtUsuario.setText("");

    }



    @FXML
    public void registrar(MouseEvent mouseEvent) throws IOException {
        if (txtUsuario.getText().equals("") || txtContrasenia.getText().equals("") || txtRepetirContrasenia.getText().equals("")) {//comprueba si hay campos vacios
            met.MensajeError("Hay campos vacios");
        } else {//si no hay campos vacios pasa a la siguiente validacion

            if (txtContrasenia.getText().equals(txtRepetirContrasenia.getText())) {//valida si la contraseña ingresada es la misma en los dos campos en caso que sea igual sigue al siguiente paso
                if (((txtContrasenia.getText().length() >= 6) && (txtContrasenia.getText().length() <= 8)) && ((txtRepetirContrasenia.getText().length() >= 6) && (txtRepetirContrasenia.getText().length() <= 8))) {
                    //verifica que la contraseña tenga la cantidad minima de caracteres : cantidad min de caracteres 6 y maxima 8
                    if (reg.existeUsuario(txtUsuario.getText()) == 0) {//en caso que el metodo retorne 0 significa que no hay usuarios con ese nombre y se puede seguir al siguiente paso.



                        String nuevoPass = Hash.sha1(txtContrasenia.getText());// cifrado de contraseña
                        reg.setUsuario(txtUsuario.getText());
                        reg.setContrasenia(nuevoPass);
                        reg.setNombre("Alumnos");


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
