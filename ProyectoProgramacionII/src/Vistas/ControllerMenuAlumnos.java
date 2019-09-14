package Vistas;


import Clases.Empresas;
import Mensajes.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Clases.Alumnos;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.awt.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ControllerMenuAlumnos extends Component {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnSeleccionarArchivo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtCurriculum;
    @FXML
    private TextField txtMail;
    @FXML
    private TextArea txtMensaje;

    @FXML
    private Label MenuCargar;
    @FXML
    private Label MenuActualizar;
    @FXML
    private Label MenuCurriculum;
    @FXML
    private Label Propuestas;
    @FXML
    private Label CerrarSesion;
    @FXML
    private TabPane Alumnos;
    @FXML
    private Tab PestaniaCargar;
    @FXML
    private  Tab PestaniaMiCv;
    @FXML
    private  Tab PestaniaMenu;
    @FXML
    private  Tab PestaniaPropuestas;
    @FXML
    private TableView<Empresas> table;


    @FXML
    public void initialize() {
     listarBusquedas();

     Alumnos.getTabs().remove(PestaniaCargar);
     Alumnos.getTabs().remove(PestaniaMiCv);
     Alumnos.getTabs().remove(PestaniaPropuestas);

     /*PestaniaMenu.setDisable(false);
     PestaniaPropuestas.setDisable(true);
     PestaniaMiCv.setDisable(true);
     PestaniaCargar.setDisable(true);*/

    }



    Alumnos al = new Alumnos();
    String ruta_archivo="";

    public void limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtCurriculum.setText("");
        txtMail.setText("");
        txtMensaje.setText("");

    }


    public boolean verificaEmail(String correo) {//metodo que verifica si se ingreso un caracter valido para mail
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        }else{
            return false;
        }
    }



    public void listarBusquedas()
    {
        Empresas emp= new Empresas();

        table.getColumns().clear();
        table.getItems().clear();

        TableColumn col= new TableColumn("Empresa");
        col.setCellValueFactory(new PropertyValueFactory<Empresas, String>("Nombre_empresa") );
        table.getColumns().add(col);

        TableColumn col1= new TableColumn("Puesto");
        col1.setCellValueFactory(new PropertyValueFactory<Empresas, String>("Puesto") );
        table.getColumns().add(col1);

        TableColumn col2= new TableColumn("Area");
        col2.setCellValueFactory(new PropertyValueFactory<Empresas, String>("Area") );
        table.getColumns().add(col2);

        TableColumn col3= new TableColumn("Sector");
        col3.setCellValueFactory(new PropertyValueFactory<Empresas, String>("Sector") );
        table.getColumns().add(col3);

        TableColumn col4= new TableColumn("Industria");
        col4.setCellValueFactory(new PropertyValueFactory<Empresas, String>("Industria") );
        table.getColumns().add(col4);

        TableColumn col5= new TableColumn("Detalle");
        col5.setCellValueFactory(new PropertyValueFactory<Empresas, String>("Detalle") );
        table.getColumns().add(col5);

        List<Empresas> Le = emp.ListarPropuestas();

        ObservableList<Empresas> data= FXCollections.observableArrayList(Le);
        table.setItems(data);

    }




    //metodo para la funcion del boton guardar pasamos todos los atributos como parametros para ejecutar el metodo insertarCV
    public  void guardarCV(String nombre, String Apellido,String dni,String mail,String cur ,File ruta,String mensaje){
        Alumnos alu=new Alumnos();
        alu.setNombre(nombre);
        alu.setApellido(Apellido);
        alu.setDni(dni);
        alu.setMail(mail);
        alu.setMensaje(mensaje);
        alu.setNom_archivo(cur);



        try{  //convierte el archivo pdf a bytes
            byte[] pdf= new byte[(int) ruta.length()];
            InputStream input= new FileInputStream(ruta);
            input.read(pdf);
            alu.setArchivo_cv(pdf);

        }catch(IOException e){
            alu.setArchivo_cv(null);
        }
        alu.insertarCV(alu);
    }


    public void seleccionarArchivo(){   //busca la ruta del archivo en el dispositivo
        JFileChooser j= new JFileChooser();
        FileNameExtensionFilter fi= new FileNameExtensionFilter("pdf","pdf");
        j.setFileFilter(fi);
        int se = j.showOpenDialog(this);
        if(se==0){
            this.txtCurriculum.setText(""+j.getSelectedFile().getName());
            ruta_archivo=j.getSelectedFile().getAbsolutePath();
        }else{}
    }


    @FXML
    public void guardar(MouseEvent event) {
        String nom = txtNombre.getText();
        String apel = txtApellido.getText();
        String l = txtMail.getText();
        String mene = txtMensaje.getText();
        String dn = txtDni.getText();
        String curm = txtCurriculum.getText();

        File ruta = new File(ruta_archivo);

        Mensajes met = new Mensajes();
        MostrarVistas mv= new MostrarVistas();

        if ((!txtNombre.getText().equals("")) && (!txtApellido.getText().equals("")) && (!txtMail.getText().equals("")) && (!txtMensaje.getText().equals("")) && (!txtDni.getText().equals("")) && (!txtCurriculum.getText().equals(""))) {
            if (verificaEmail(txtMail.getText())) {
                guardarCV(nom, apel, dn, l, curm, ruta, mene);
                limpiar();
                met.mensajeOk("Registro exitoso");
                MenuCargar.setVisible(false);


            } else {
                met.MensajeError("Mail invalido");
            }
        } else {
            met.MensajeError("Error al guardar");

        }
    }



    @FXML
    public void seleccionar(MouseEvent event){
        txtCurriculum.setText("seleccionar...");
        seleccionarArchivo();


    }

    @FXML
    public void cargar(MouseEvent event){//evento cargar
        Alumnos.getTabs().add(PestaniaCargar);
        Alumnos.getTabs().remove(PestaniaMenu);
        Alumnos.getTabs().remove(PestaniaMiCv);
        Alumnos.getTabs().remove(PestaniaPropuestas);

    }

    @FXML
    public void Actualizar(MouseEvent event){//evento actualizar
        Alumnos.getTabs().add(PestaniaCargar);
        Alumnos.getTabs().remove(PestaniaMenu);
        Alumnos.getTabs().remove(PestaniaMiCv);
        Alumnos.getTabs().remove(PestaniaPropuestas);
    }

    @FXML
    public void VistaCurriculum(MouseEvent event){ //evento vista curriculum
        Alumnos.getTabs().add(PestaniaMiCv);
        Alumnos.getTabs().remove(PestaniaCargar);
        Alumnos.getTabs().remove(PestaniaMenu);
        Alumnos.getTabs().remove(PestaniaPropuestas);

    }

    @FXML
    public void VerPropuestas(MouseEvent event){//evento boton propuestas
        Alumnos.getTabs().add(PestaniaPropuestas);
        Alumnos.getTabs().remove(PestaniaCargar);
        Alumnos.getTabs().remove(PestaniaMiCv);
        Alumnos.getTabs().remove(PestaniaMenu);
    }
    @FXML
    public void Cerrar(MouseEvent event){//evento boton cerrar sesion
         PestaniaPropuestas.setClosable(true);
         PestaniaMiCv.getOnClosed();
         PestaniaCargar.getOnClosed();

    }

    @FXML
    public void volver(MouseEvent event){//evento boton volver
        Alumnos.getTabs().add(PestaniaMenu);
        Alumnos.getTabs().remove(PestaniaCargar);
        Alumnos.getTabs().remove(PestaniaMiCv);
        Alumnos.getTabs().remove(PestaniaPropuestas);


    }
}
