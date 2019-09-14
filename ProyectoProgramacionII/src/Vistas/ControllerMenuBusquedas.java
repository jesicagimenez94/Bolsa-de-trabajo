package Vistas;

import Clases.Alumnos;
import Clases.Registros;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Mensajes.Mensajes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import Clases.Empresas;

import javax.imageio.spi.RegisterableService;


public class ControllerMenuBusquedas {

    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtNombreEmpresa;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtIndustria;
    @FXML
    private TextField txtPuesto;
    @FXML
    private TextField txtSector;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextArea txtDetalle;
    @FXML
    private TableView table;
    @FXML
    private TableView tableUser;

    @FXML
    private Label Alumnos;
    @FXML
    private TabPane empresas;
    @FXML
    private Tab Menu;
    @FXML
    private Tab cargar;
    @FXML
    private Tab propuestas;
    @FXML
    private Tab candidatos;
    @FXML
    private Tab user;
    @FXML
    private Tab permiso;


    @FXML
    private void initialize(){
        fecha.setValue(LocalDate.now());
        listarAlumnos();
        listarUsuarios();
        empresas.getTabs().remove(cargar);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(user);
        empresas.getTabs().remove(permiso);

    }


    public void listarAlumnos()
    {
        Alumnos alum = new Alumnos();

        table.getColumns().clear();
        table.getItems().clear();

        TableColumn col= new TableColumn("Nombre");
        col.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre") );
        table.getColumns().add(col);

        TableColumn col1= new TableColumn("Apellido");
        col1.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellido") );
        table.getColumns().add(col1);

        TableColumn col2= new TableColumn("Dni");
        col2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("dni") );
        table.getColumns().add(col2);

        TableColumn col3= new TableColumn("Mail");
        col3.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("mail") );
        table.getColumns().add(col3);


        TableColumn col4= new TableColumn("Curriculum");
        col4.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("archivo_cv") );
        table.getColumns().add(col4);

        TableColumn col5= new TableColumn("Mensaje");
        col5.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("mensaje") );
        table.getColumns().add(col5);

        List<Alumnos> al = alum.ListarCurriculum();

        ObservableList<Alumnos> data= FXCollections.observableArrayList(al);
        table.setItems(data);

    }


    public void listarUsuarios(){ //metodo que muestra los usuarios
        Registros reg =new Registros();
        tableUser.getColumns().clear();
        tableUser.getItems().clear();
        TableColumn colUser= new TableColumn("Usuario");
        colUser.setCellValueFactory(new PropertyValueFactory<Registros, String>("usuario") );
        tableUser.getColumns().add(colUser);

        List<Registros> regi = reg.ListarUsuarios();

        ObservableList<Registros> data= FXCollections.observableArrayList(regi);
        tableUser.setItems(data);


    }
    @FXML
    public void ventanaAlumnos(MouseEvent m){
        MostrarVistas l = new MostrarVistas();
        l.mostrarVistaMenuAlumnos();


    }


    private void limpiar(){
        txtNombreEmpresa.setText("");
        txtArea.setText("");
        txtPuesto.setText("");
        txtSector.setText("");
        txtDetalle.setText("");
    }

    //metodo para la funcion del boton guardar pasamos todos los atributos como parametros para ejecutar el metodo insertarPropuesta
    @FXML
    private  void guardarPropuesta( String nombreEmpresa, String puesto,String industria,String area,String sector ,String detalle, Date Fecha){
        Empresas emp=new Empresas();
        emp.setNombre_empresa(nombreEmpresa);
        emp.setPuesto(puesto);
        emp.setIndustria(industria);
        emp.setArea(area);
        emp.setSector(sector);
        emp.setDetalle(detalle);
        //emp.setFecha(Fecha);

        emp.insertarPropuesta(emp);
    }
    Mensajes men= new Mensajes();

    @FXML
    public void guardar(MouseEvent event){
        String nom=txtNombreEmpresa.getText();
        String pues= txtPuesto.getText();
        String ind= txtIndustria.getText();
        String ar= txtArea.getText();
        String sec= txtSector.getText();
        String det= txtDetalle.getText();
        Date fec= (Date) fecha.getDayCellFactory();



        if((!txtNombreEmpresa.getText().equals(""))&&(!txtPuesto.getText().equals(""))&&(!txtIndustria.getText().equals(""))&&(!txtArea.getText().equals(""))&&(!txtSector.getText().equals(""))&&(!txtDetalle.getText().equals(""))){
            guardarPropuesta(nom,pues,ind,ar,sec,det,fec);//si no hay campos vacios se guarda la informacion
            men.mensajeOk("Registro exitoso");


        }else{
            men.MensajeError("Hay campos vacios");

        }


    }

    @FXML
    private  void Cargar(MouseEvent event){

        empresas.getTabs().add(cargar);
        empresas.getTabs().remove(Menu);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(user);
        empresas.getTabs().remove(permiso);

    }

    @FXML
    private  void actualizarDatos(MouseEvent event){
        empresas.getTabs().add(cargar);
        empresas.getTabs().remove(Menu);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(user);
        empresas.getTabs().remove(permiso);
    }

    @FXML
    private  void mostrarPropuesta(MouseEvent event){
        empresas.getTabs().add(propuestas);
        empresas.getTabs().remove(Menu);
        empresas.getTabs().remove(cargar);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(user);
        empresas.getTabs().remove(permiso);

    }

    @FXML
    private  void ListarCandidatos(MouseEvent event){
        empresas.getTabs().add(candidatos);
        empresas.getTabs().remove(Menu);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(cargar);
        empresas.getTabs().remove(user);
        empresas.getTabs().remove(permiso);

    }

    @FXML
    private  void mostrarUser(MouseEvent event){
        empresas.getTabs().add(user);
        empresas.getTabs().remove(Menu);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(cargar);
        empresas.getTabs().remove(permiso);

    }

    @FXML
    private  void mostrarPermisos(MouseEvent event){
        empresas.getTabs().add(permiso);
        empresas.getTabs().remove(Menu);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(user);
        empresas.getTabs().remove(cargar);

    }

    @FXML
    private  void volver(MouseEvent event){
        empresas.getTabs().add(Menu);
        empresas.getTabs().remove(cargar);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(user);
        empresas.getTabs().remove(permiso);

    }



}


