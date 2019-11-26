package Vistas;

import Clases.*;
import Conexion.Conectar;
import Conexion.Hash;
import Mensajes.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControllerMenuBusquedas1 extends Registros {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnBajaUsuario;
    @FXML
    private Button btnBajaGrupo;
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
    public TableView table;
    @FXML
    private TableView tableUser11;
    @FXML
    private TableView tableUser;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasenia;
    @FXML
    private PasswordField txtRepetirContrasenia;
    @FXML
    private ComboBox comboGrupo;
    @FXML
    private ComboBox comboGrupo1;
    @FXML
    private ComboBox comboUsuario;
    @FXML
    private ComboBox comboPermiso;

    @FXML
    private TextField txtPermiso;

    @FXML
    private Label Alumnos;
    @FXML
    private Label btnIniciarSesion;
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
    private Tab permiso;

    @FXML
    private Tab usuarios;

    @FXML
    private Tab usuarios2;

    @FXML
    private TextField txtGrupo;
    @FXML
    private Label permisos;
    @FXML
    private Button btnAltaGrupo;
    @FXML
    private Button btnAltaPermiso;
    @FXML
    private Button btnBajaPermiso;
    @FXML
    private Button btnModificarPermiso;
    @FXML
    private Button btnModificarGrupo;
    @FXML
    private Button btnVerPermiso;
    @FXML
    private Button btnVerGrupo;
    public String permiso1;


    @FXML
    private void initialize(){
        fecha.setValue(LocalDate.now());
        empresas.getTabs().remove(cargar);
        empresas.getTabs().remove(propuestas);
        empresas.getTabs().remove(candidatos);
        empresas.getTabs().remove(permiso);
        empresas.getTabs().remove(usuarios2);
        empresas.getTabs().remove(usuarios);
        table.getItems().clear();
        tableUser.getItems().clear();
        tableUser11.getItems().clear();
        listarAlumnos();
        cargarListaPermisos();
        cargarListaUsuarios();


        comboGrupo.setItems(g.TipoUsuario());
        comboUsuario.setItems(reg.TipoUsuarioG());
        comboPermiso.setItems(P.TipoPermiso());
        comboGrupo1.setItems(g.TipoUsuario());



    }




Grupos g= new Grupos();
Permisos P= new Permisos();
public boolean check1=true;
public boolean check2=false;

   public String nombre_grupo;
    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

   /* public void grupos(String nombre_grupo){
        traerPermisos(reg.getNombre_grupo());
        System.out.println("grupo"+reg.getNombre_grupo());
    }*/



    @FXML
    public  void traerPermisos(String grupo){
        Conectar con = new Conectar();
        PreparedStatement ps=null;
        ResultSet rs= null;
        Grupos g=new Grupos();
        try {
            Permisos p= new Permisos();
            String sql="SELECT gp.alta,gp.baja,gp.modificar,gp.listar FROM  grupos_permisos gp JOIN permisos p ON p.id_permiso=gp.id_permiso JOIN grupos g ON g.id_grupo=gp.id_grupo WHERE g.nombre= '"+grupo+"' ";
            ps = con.getConnection().prepareStatement(sql);
            rs =ps.executeQuery();

            while(rs.next()){
               int alta= rs.getInt(1);
               int baja=rs.getInt(2);
               int listar= rs.getInt(4);
               int modificar=rs.getInt(3);
                System.out.println("permiso "+grupo);
                if(alta==0){
                    System.out.println(alta);

                  //  btnAltaGrupo.setDisable(true);
                  // btnAltaPermiso.setDisable(true);


                }else{
                    System.out.println(alta);
                 // btnAltaGrupo.setDisable(false);
                  //btnAltaPermiso.setDisable(false);


                }

                if (baja==0){
                    System.out.println(baja);
                   // btnBajaGrupo.setDisable(true);
                   // btnBajaPermiso.setDisable(true);
                   // btnBajaUsuario.setDisable(true);
                }else{
                    System.out.println(baja);
                   // btnBajaGrupo.setDisable(false);
                   // btnBajaPermiso.setDisable(false);
                   // btnBajaUsuario.setDisable(false);

                }
                if(listar==0){
                    System.out.println(listar);
                   // tableUser.setDisable(true);
                   // tableUser11.setDisable(true);
                }else{
                    System.out.println(listar);
                    //tableUser.setDisable(false);
                    //tableUser11.setDisable(false);

                }

                if(modificar==0){
                    System.out.println(modificar);
                  // btnModificarGrupo.setDisable(true);
                }else{
                    System.out.println(modificar);
                   // btnModificarGrupo.setDisable(false);
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

        }

    }




    @FXML
    public void cargarListaUsuarios(){

        tableUser11.getColumns().clear();
        tableUser11.getItems().clear();

        TableColumn col6= new TableColumn("Id_registro");
        col6.setCellValueFactory(new PropertyValueFactory<Registros,Integer>("id_registros"));
        col6.setMinWidth(110);
        TableColumn col5= new TableColumn("Usuario");
        col5.setCellValueFactory(new PropertyValueFactory<Registros,String>("usuario"));
        col5.setMinWidth(110);
        TableColumn col7= new TableColumn("Grupo");
        col7.setCellValueFactory(new PropertyValueFactory<Registros,String>("nombre"));
        col7.setMinWidth(160);

        tableUser11.getColumns().addAll(col6,col5,col7);

        Registros re= new Registros();
        List<Registros> gp = re.ListarUsuarios();

        ObservableList<Registros> data = FXCollections.observableArrayList(gp);
        tableUser11.setItems(data);
        tableUser11.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableUser11.setEditable(true);


    }
    public void listarAlumnos() {
        Alumnos alum = new Alumnos();

        table.getColumns().clear();
        table.getItems().clear();
        Button cv = new Button();


        TableColumn col = new TableColumn("Nombre");
        col.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
        table.getColumns().add(col);

        TableColumn col1 = new TableColumn("Apellido");
        col1.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellido"));
        table.getColumns().add(col1);

        TableColumn col2 = new TableColumn("Dni");
        col2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("dni"));
        table.getColumns().add(col2);

        TableColumn col3 = new TableColumn("Mail");
        col3.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("mail"));
        table.getColumns().add(col3);

        TableColumn col5 = new TableColumn("Mensaje");
        col5.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("mensaje"));
        table.getColumns().add(col5);

        List<Alumnos> al = alum.ListarCurriculum();

        ObservableList<Alumnos> data = FXCollections.observableArrayList(al);
        table.setItems(data);


        TableColumn<Alumnos, Void> colBtn = new TableColumn("Curriculum");

        Callback<TableColumn<Alumnos, Void>, TableCell<Alumnos, Void>> cellFactory = new Callback<TableColumn<Alumnos, Void>, TableCell<Alumnos, Void>>() {
            @Override
            public TableCell<Alumnos, Void> call(final TableColumn<Alumnos, Void> param) {
                final TableCell<Alumnos, Void> cell = new TableCell<Alumnos, Void>() {

                    private final Button btn = new Button("cv");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Alumnos data = new Alumnos();
                            data.ejecutarArchivo();

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }

    @FXML
    public void cargarListaPermisos(){

        tableUser.getColumns().clear();
        tableUser.getItems().clear();

        TableColumn col7= new TableColumn("id Grupo");
        col7.setCellValueFactory(new PropertyValueFactory<GruposPermisos,String>("id_grupo"));
        col7.setMinWidth(100);
        TableColumn col6= new TableColumn("Grupo");
        col6.setCellValueFactory(new PropertyValueFactory<GruposPermisos,String>("nombre"));
        col6.setMinWidth(160);

        TableColumn col8= new TableColumn("id Permiso");
        col8.setCellValueFactory(new PropertyValueFactory<GruposPermisos,String>("id_permiso"));
        col8.setMinWidth(100);
        TableColumn col5= new TableColumn("Permiso");
        col5.setCellValueFactory(new PropertyValueFactory<GruposPermisos,String>("permiso"));
        col5.setMinWidth(170);


        TableColumn<GruposPermisos, Void> colBtn = new TableColumn("Alta");
        Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>> cellFactory = new Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>>() {
                          @Override public TableCell<GruposPermisos, Void> call(final TableColumn<GruposPermisos, Void> param) {
                              final TableCell<GruposPermisos, Void> cell = new TableCell<GruposPermisos, Void>() {

                                  private CheckBox btn = new CheckBox();

                                  {

                                      btn.setOnAction((ActionEvent event) -> {
                                          GruposPermisos gp= new GruposPermisos();
                                          Grupos g= new Grupos();
                                          Permisos p= new Permisos();


                                          if(btn.isSelected()==true ){
                                              List<GruposPermisos> gr = gp.listarPermisos();
                                              GruposPermisos opcion1=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int o= Integer.parseInt(Integer.toString(opcion1.getId_permiso()));
                                              gp.setId_permiso(o);
                                              GruposPermisos opcion2=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int v= Integer.parseInt(Integer.toString(opcion2.getId_grupo()));
                                              gp.setId_grupo(v);
                                              try {
                                                  Conectar con = new Conectar();
                                                  PreparedStatement ps1=null;
                                                  String sql="update grupos_permisos set alta = "+check1+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";
                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();
                                                  System.out.println("ingrese");

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                                              }



                                          }else{
                                              System.out.println("apagado");

                                              try {
                                                  Conectar con = new Conectar();
                                                  PreparedStatement ps1=null;
                                                  String sql="update grupos_permisos set alta = "+check2+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";

                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

                                              }


                                          }

                                      });
                                  }

                                  @Override
                                  public void updateItem(Void item, boolean empty) {
                                      super.updateItem(item, empty);
                                      if (empty) {
                                          setGraphic(null);
                                      } else {
                                          setGraphic(btn);
                                      }
                                  }
                              };
                              return cell;
                          }
                      };

        colBtn.setCellFactory(cellFactory);
        TableColumn<GruposPermisos, Void> colBtn2 = new TableColumn("Baja");
        Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>> cellFactory1 = new Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>>() {
            @Override public TableCell<GruposPermisos, Void> call(final TableColumn<GruposPermisos, Void> param) {
                final TableCell<GruposPermisos, Void> cell = new TableCell<GruposPermisos, Void>() {
                    private CheckBox btn1 = new CheckBox();

                                  {
                                      btn1.setOnAction((ActionEvent event) -> {
                                          GruposPermisos gp= new GruposPermisos();
                                          if(btn1.isSelected()==true){
                                              Conectar con= new Conectar();
                                              List<GruposPermisos> gr = gp.listarPermisos();
                                              GruposPermisos opcion1=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int o= Integer.parseInt(Integer.toString(opcion1.getId_permiso()));
                                              gp.setId_permiso(o);
                                              System.out.println(gp.getId_permiso());
                                              GruposPermisos opcion2=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int v= Integer.parseInt(Integer.toString(opcion2.getId_grupo()));
                                              gp.setId_grupo(v);

                                              try {
                                                  PreparedStatement ps1=null;
                                                  String sql="update grupos_permisos set baja = "+check1+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";
                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();
                                                  System.out.println("ingrese");

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                                              }



                                          }else{
                                              Conectar con = new Conectar();
                                              PreparedStatement ps1=null;
                                              String sql="update grupos_permisos set baja = "+check2+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";
                                              try {

                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

                                              }


                                          }

                                      });
                                  }

                                  @Override
                                  public void updateItem(Void item, boolean empty) {
                                      super.updateItem(item, empty);
                                      if (empty) {
                                          setGraphic(null);
                                      } else {
                                          setGraphic(btn1);
                                      }
                                  }
                              };
                              return cell;
                          }
                      };

        colBtn2.setCellFactory(cellFactory1);
                      TableColumn<GruposPermisos, Void> colBtn3 = new TableColumn("Modificar");
                      Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>> cellFactory2 = new Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>>() {
                          @Override public TableCell<GruposPermisos, Void> call(final TableColumn<GruposPermisos, Void> param) {
                              final TableCell<GruposPermisos, Void> cell = new TableCell<GruposPermisos, Void>() {

                                  private CheckBox btn2 = new CheckBox();
                                  {
                                      btn2.setOnAction((ActionEvent event) -> {
                                          GruposPermisos gp= new GruposPermisos();
                                          if(btn2.isSelected()==true ){

                                              Conectar con= new Conectar();
                                              List<GruposPermisos> gr = gp.listarPermisos();
                                              GruposPermisos opcion1=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int o= Integer.parseInt(Integer.toString(opcion1.getId_permiso()));
                                              gp.setId_permiso(o);
                                              System.out.println(gp.getId_permiso());
                                              GruposPermisos opcion2=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int v= Integer.parseInt(Integer.toString(opcion2.getId_grupo()));
                                              gp.setId_grupo(v);
                                              try {
                                                  PreparedStatement ps1=null;
                                                  String sql="update grupos_permisos set modificar = "+check1+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";
                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();
                                                  System.out.println("ingrese");

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                                              }



                                          }else{
                                              Conectar con = new Conectar();
                                              PreparedStatement ps1=null;
                                              String sql="update grupos_permisos set modificar = "+check2+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";
                                              try {

                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

                                              }


                                          }

                                      });
                                  }

                                  @Override
                                  public void updateItem(Void item, boolean empty) {
                                      super.updateItem(item, empty);
                                      if (empty) {
                                          setGraphic(null);
                                      } else {
                                          setGraphic(btn2);
                                      }
                                  }
                              };
                              return cell;
                          }
                      };
                      colBtn3.setCellFactory(cellFactory2);
                      TableColumn<GruposPermisos, Void> colBtn4 = new TableColumn("Listar");
                      Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>> cellFactory3 = new Callback<TableColumn<GruposPermisos, Void>, TableCell<GruposPermisos, Void>>() {
                          @Override public TableCell<GruposPermisos, Void> call(final TableColumn<GruposPermisos, Void> param) {
                              final TableCell<GruposPermisos, Void> cell = new TableCell<GruposPermisos, Void>() {
                                  private CheckBox btn3 = new CheckBox();
                                  {
                                      btn3.setOnAction((ActionEvent event) -> {
                                          GruposPermisos gp= new GruposPermisos();
                                          Grupos grupos= new Grupos();

                                          if( btn3.isSelected()==true){
                                              Conectar con= new Conectar();
                                              List<GruposPermisos> gr = gp.listarPermisos();
                                              GruposPermisos opcion1=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int o= Integer.parseInt(Integer.toString(opcion1.getId_permiso()));
                                              gp.setId_permiso(o);
                                              System.out.println(gp.getId_permiso());
                                              GruposPermisos opcion2=gr.get(tableUser.getSelectionModel().getSelectedIndex());
                                              int v= Integer.parseInt(Integer.toString(opcion2.getId_grupo()));
                                              gp.setId_grupo(v);
                                              try {
                                                  PreparedStatement ps1=null;
                                                  String sql="update grupos_permisos set listar = "+check1+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";
                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();
                                                  System.out.println("ingrese");

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                                              }



                                          }else{
                                              Conectar con = new Conectar();
                                              PreparedStatement ps1=null;
                                              String sql="update grupos_permisos set listar = "+check2+" where id_grupo="+gp.getId_grupo()+" AND id_permiso="+gp.getId_permiso()+"";
                                              try {

                                                  ps1 = con.getConnection().prepareStatement(sql);
                                                  ps1.execute();

                                              } catch (SQLException ex) {
                                                  Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

                                              }


                                          }

                                      });
                                  }

                                  @Override
                                  public void updateItem(Void item, boolean empty) {
                                      super.updateItem(item, empty);
                                      if (empty) {
                                          setGraphic(null);
                                      } else {
                                          setGraphic(btn3);
                                      }
                                  }
                              };
                              return cell;
                          }
                      };

                      colBtn4.setCellFactory(cellFactory3);
        tableUser.getColumns().addAll(col7,col6,col8,col5,colBtn,colBtn2,colBtn3,colBtn4);


                      GruposPermisos gp= new GruposPermisos();

        List<GruposPermisos> gr = gp.listarPermisos();

        ObservableList<GruposPermisos> data = FXCollections.observableArrayList(gr);
        tableUser.setItems(data);
        tableUser.setEditable(true);



    }

                   @FXML
                   public void cargarPermisos(MouseEvent mouseEvent){

                   }

                    @FXML
                    public void ventanaAlumnos(MouseEvent m) {
                        MostrarVistas l = new MostrarVistas();
                        l.mostrarVistaMenuAlumnos();
                    }

                     @FXML
                     public void iniciarSesion(MouseEvent m) {
                     MostrarVistas l = new MostrarVistas();
                     l.mostrarVistaRegistros();
                     }


                    private void limpiar() {
                        txtNombreEmpresa.setText("");
                        txtArea.setText("");
                        txtPuesto.setText("");
                        txtSector.setText("");
                        txtDetalle.setText("");
                    }


    private void limpiarRegistro() {
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtRepetirContrasenia.setText("");
    }
                    //metodo para la funcion del boton guardar pasamos todos los atributos como parametros para ejecutar el metodo insertarPropuesta
                    @FXML
                    private void guardarPropuesta(String nombreEmpresa, String puesto, String industria, String area, String sector, String detalle, Date Fecha) {
                        Empresas emp = new Empresas();
                        emp.setNombre_empresa(nombreEmpresa);
                        emp.setPuesto(puesto);
                        emp.setIndustria(industria);
                        emp.setArea(area);
                        emp.setSector(sector);
                        emp.setDetalle(detalle);
                        //emp.setFecha(Fecha);

                        emp.insertarPropuesta(emp);
                    }

                    @FXML
                    private void mostrarPropuesta(String nombreEmpresa, String puesto, String industria, String area, String sector, String detalle) {
                        Empresas emp = new Empresas();

                        nombreEmpresa=emp.getPuesto();
                        puesto=emp.getIndustria();
                        industria=emp.getArea();
                        sector=emp.getSector();
                        detalle=emp.getDetalle();
                        //emp.setFecha(Fecha);


                        emp.mostrarDatos(emp);
                    }


    Mensajes men = new Mensajes();

                    @FXML
                    public void guardar(MouseEvent event) {
                        String nom = txtNombreEmpresa.getText();
                        String pues = txtPuesto.getText();
                        String ind = txtIndustria.getText();
                        String ar = txtArea.getText();
                        String sec = txtSector.getText();
                        String det = txtDetalle.getText();
                        Date fec = (Date) fecha.getDayCellFactory();


                        if ((!txtNombreEmpresa.getText().equals("")) && (!txtPuesto.getText().equals("")) && (!txtIndustria.getText().equals("")) && (!txtArea.getText().equals("")) && (!txtSector.getText().equals("")) && (!txtDetalle.getText().equals(""))) {
                            guardarPropuesta(nom, pues, ind, ar, sec, det, fec);//si no hay campos vacios se guarda la informacion
                            men.mensajeOk("Registro exitoso");


                        } else {
                            men.MensajeError("Hay campos vacios");

                        }


                    }

                    Mensajes met = new Mensajes();
                    Registros reg = new Registros();



                    @FXML
                    public void agregarUsuarioGrupo(MouseEvent mouseEvent){
                        GruposUsuarios gr= new GruposUsuarios();
                        Grupos grupos= new Grupos();
                        Registros registros= new Registros();

                        List<Grupos> g= grupos.TipoUsuario();
                        List<Registros> r= registros.TipoUsuarioG();

                        Grupos opcion1=g.get(comboGrupo.getSelectionModel().getSelectedIndex());
                        int o= Integer.parseInt(Integer.toString(opcion1.getId_grupo()));
                        gr.setId_grupo(o);
                        Registros opcion2=r.get(comboUsuario.getSelectionModel().getSelectedIndex());
                        int v=Integer.parseInt(Integer.toString(opcion2.getId_registros()));
                        gr.setId_usuario(v);

                        if(gr.RegistrarGrupo(gr)){
                            met.mensajeOk("Registro exitoso");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Ventana de confirmacion");
                            alert.setHeaderText(null);
                            alert.initStyle(StageStyle.UTILITY);
                            alert.setContentText("agregar mas grupos al mismo usuario?");
                            Optional<ButtonType> result=alert.showAndWait();
                            if(result.get()==ButtonType.OK){
                                comboUsuario.setDisable(true);
                            }else if(result.get()==ButtonType.CANCEL){
                                comboUsuario.setDisable(false);
                            }
                            tableUser11.getItems().clear();
                            cargarListaUsuarios();

                        }else{
                            met.MensajeError("Registro invalido");
                        }


                    }

                  @FXML
                  public void agregarPermisoGrupo(MouseEvent mouseEvent){
                        GruposPermisos gp= new GruposPermisos();
                        Grupos grupos= new Grupos();
                        Permisos permisos= new Permisos();

                          List<Grupos> g= grupos.TipoUsuario();
                          List<Permisos> p= permisos.TipoPermiso();

                          Grupos opcion1=g.get(comboGrupo1.getSelectionModel().getSelectedIndex());
                          int o= Integer.parseInt(Integer.toString(opcion1.getId_grupo()));
                          gp.setId_grupo(o);
                          Permisos opcion2=p.get(comboPermiso.getSelectionModel().getSelectedIndex());
                          int v=Integer.parseInt(Integer.toString(opcion2.getId_permiso()));
                          gp.setId_permiso(v);

                          if(gp.RegistrarPermiso(gp)){
                              met.mensajeOk("Registro exitoso");
                              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                              alert.setTitle("Ventana de confirmacion");
                              alert.setHeaderText(null);
                              alert.initStyle(StageStyle.UTILITY);
                              alert.setContentText("agregar mas permisos al mismo grupo?");
                              Optional<ButtonType> result=alert.showAndWait();
                              if(result.get()==ButtonType.OK){
                                  comboGrupo1.setDisable(true);
                              }else if(result.get()==ButtonType.CANCEL){
                                  comboGrupo1.setDisable(false);
                              }

                              tableUser.getItems().clear();
                              cargarListaPermisos();

                            }else{
                            met.MensajeError("Registro invalido");
                             }
                            }

                    @FXML
                    public void registrar(MouseEvent mouseEvent) throws IOException {//registra nuevo usuario
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


                                        if (reg.Registrar(reg) ) {
                                            limpiarRegistro();
                                            txtUsuario.clear();
                                            met.mensajeOk("Registro exitoso");//se agrega nuevo permiso
                                            comboUsuario.getItems().clear();
                                            comboUsuario.setItems(reg.TipoUsuarioG());

                                            met.mensajeOk("Registro exitoso");//si el registro es exitoso se abre la ventana de login

                                        } else {
                                            met.MensajeError("Registro invalido");
                                        }
                                    } else {

                                        met.MensajeError("Ya hay un usuario con ese nombre");
                                        //en caso que retorne !=0 significa que ya hay un usuario con ese nombre
                                    }

                                } else {
                                    met.MensajeError("Cantidad de caracteres invalidos");
                                }
                            } else {
                                met.MensajeError("Las contraseñas no coinciden");
                            }


                        }
                    }
                 @FXML
                 private void nuevoGrupo(MouseEvent event){//agrega nuevo grupo
                        Registros reg= new Registros();
                        Grupos g= new Grupos();
                        reg.setNombre(txtGrupo.getText());
                     if(txtGrupo.getText().isEmpty()){
                         met.MensajeError("hay campos vacios");
                         return;
                     }
                 if (g.existeGrupo(txtGrupo.getText()) == 0){
                     if ( g.RegistrarGrupo(reg)) {
                         txtGrupo.clear();
                         met.mensajeOk("Registro exitoso");//se agrega nuevo grupo
                         comboGrupo1.getItems().clear();
                         Grupos per= new Grupos();
                         comboGrupo1.setItems(per.TipoUsuario());
                         comboGrupo.getItems().clear();
                         comboGrupo.setItems(per.TipoUsuario());


                     } else {
                         met.MensajeError("Registro invalido");
                     }
                     }else{ met.MensajeError("Ya hay un grupo con ese nombre");}

                 }

                 @FXML
                 private void guardarPermiso(MouseEvent mouseEvent){//agrega nuevo permiso
                       Permisos per= new Permisos();
                       per.setNombre_permiso(txtPermiso.getText());
                   if(txtPermiso.getText().isEmpty()){
                       met.MensajeError("hay campos vacios");
                       return;
                   }
                     if (per.existePermiso(txtPermiso.getText()) == 0){
                         if ( per.RegistrarPermiso(per)) {
                             txtPermiso.clear();
                             met.mensajeOk("Registro exitoso");//se agrega nuevo permiso
                             Permisos peri= new Permisos();
                             comboPermiso.getItems().clear();
                             comboPermiso.setItems(peri.TipoPermiso());


                         } else {
                             met.MensajeError("Registro invalido");
                         }
                     }else{ met.MensajeError("Ya hay un permiso con ese nombre");}



                 }

                  @FXML
                private void bajaUsuario(MouseEvent event) {
                      Registros registros= new Registros();
                      List<Registros> r= registros.TipoUsuarioG();
                      Registros opcion2=r.get(comboUsuario.getSelectionModel().getSelectedIndex());
                      int v=Integer.parseInt(Integer.toString(opcion2.getId_registros()));
                      registros.setId_registros(v);
                      try {
                          Conectar con = new Conectar();
                          PreparedStatement ps1=null;
                          String sql="delete from usuarios  WHERE id_registros="+registros.getId_registros()+"";
                          ps1 = con.getConnection().prepareStatement(sql);
                          ps1.execute();

                          System.out.println("eliminado");

                      } catch (SQLException ex) {
                          Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      try {
                          Conectar con = new Conectar();
                          PreparedStatement ps1=null;
                          String sql="delete from grupos_usuarios  WHERE id_usuario="+registros.getId_registros()+"";
                          ps1 = con.getConnection().prepareStatement(sql);
                          ps1.execute();

                          System.out.println("eliminado");

                      } catch (SQLException ex) {
                          Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      comboUsuario.getItems().clear();
                      Registros reg= new Registros();
                      comboUsuario.setItems(reg.TipoUsuarioG());
                      tableUser11.getItems().clear();
                      cargarListaUsuarios();

                  }
              @FXML
             private void bajaGrupo(MouseEvent event) {
                  Grupos grupos= new Grupos();
                  List<Grupos> g= grupos.TipoUsuario();
                  Grupos opcion2=g.get(comboGrupo.getSelectionModel().getSelectedIndex());
                  int v=Integer.parseInt(Integer.toString(opcion2.getId_grupo()));
                  grupos.setId_grupo(v);
                  try {
                      Conectar con = new Conectar();
                      PreparedStatement ps1=null;
                      String sql="delete from grupos  WHERE id_grupo="+grupos.getId_grupo()+"";
                      ps1 = con.getConnection().prepareStatement(sql);
                      ps1.execute();
                      System.out.println("eliminado");

                  } catch (SQLException ex) {
                      Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                      Conectar con = new Conectar();
                      PreparedStatement ps1=null;
                      String sql="delete from grupos_usuarios  WHERE id_grupo="+grupos.getId_grupo()+"";
                      ps1 = con.getConnection().prepareStatement(sql);
                      ps1.execute();
                      System.out.println("eliminado");

                  } catch (SQLException ex) {
                      Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  comboGrupo.getItems().clear();
                  Grupos gr= new Grupos();
                  comboGrupo.setItems(gr.TipoUsuario());
                  tableUser11.getItems().clear();
                  cargarListaUsuarios();



              }

                 @FXML
                 public void bajaPermiso(MouseEvent event){
                     Permisos permiso = new Permisos();
                     List<Permisos> p= permiso.TipoPermiso();
                     Permisos opcion2=p.get(comboPermiso.getSelectionModel().getSelectedIndex());
                     int v=Integer.parseInt(Integer.toString(opcion2.getId_permiso()));
                     permiso.setId_permiso(v);
                     try {
                         Conectar con = new Conectar();
                         PreparedStatement ps1=null;
                         String sql="delete from permisos  WHERE id_permiso="+permiso.getId_permiso()+"";
                         ps1 = con.getConnection().prepareStatement(sql);
                         ps1.execute();
                         System.out.println("eliminado");

                     } catch (SQLException ex) {
                         Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     try {
                         Conectar con = new Conectar();
                         PreparedStatement ps1=null;
                         String sql="delete from grupos_permisos  WHERE id_permiso="+permiso.getId_permiso()+"";
                         ps1 = con.getConnection().prepareStatement(sql);
                         ps1.execute();
                         System.out.println("eliminado");

                     } catch (SQLException ex) {
                         Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     comboPermiso.getItems().clear();
                     Permisos per= new Permisos();
                     comboPermiso.setItems(per.TipoPermiso());
                     tableUser.getItems().clear();
                     cargarListaPermisos();

                  }
                 @FXML
                  public void bajaGrupo1(MouseEvent event){
                     Grupos grupos= new Grupos();
                     List<Grupos> g= grupos.TipoUsuario();
                     Grupos opcion2=g.get(comboGrupo1.getSelectionModel().getSelectedIndex());
                     int v=Integer.parseInt(Integer.toString(opcion2.getId_grupo()));
                     grupos.setId_grupo(v);
                     try {
                         Conectar con = new Conectar();
                         PreparedStatement ps1=null;
                         String sql="delete from grupos  WHERE id_grupo="+grupos.getId_grupo()+"";
                         ps1 = con.getConnection().prepareStatement(sql);
                         ps1.execute();
                         System.out.println("eliminado");

                     } catch (SQLException ex) {
                         Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     try {
                         Conectar con = new Conectar();
                         PreparedStatement ps1=null;
                         String sql="delete from grupos_permisos WHERE id_grupo="+grupos.getId_grupo()+"";
                         ps1 = con.getConnection().prepareStatement(sql);
                         ps1.execute();
                         System.out.println("eliminado");

                     } catch (SQLException ex) {
                         Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     comboGrupo1.getItems().clear();
                     Grupos gr= new Grupos();
                     comboGrupo1.setItems(gr.TipoUsuario());
                     tableUser.getItems().clear();
                     cargarListaPermisos();

                   }
                   @FXML
                   public void verGrupo(MouseEvent event){
                       Grupos grupo = new Grupos();
                       String opcion3= String.valueOf( comboGrupo1.getSelectionModel().getSelectedItem());
                       txtGrupo.setText(opcion3);
                       grupo.setNombre(opcion3);


                   }

                   public String opcion2;
                   @FXML
                   public void verPermiso(MouseEvent event){
                       Permisos permiso = new Permisos();
                       String opcion3= String.valueOf( comboPermiso.getSelectionModel().getSelectedItem());
                       txtPermiso.setText(opcion3);
                       permiso.setNombre_permiso(opcion3);
                   }
                   @FXML
                   public void modificarPermiso(MouseEvent event){
                       Permisos permiso = new Permisos();
                       List<Permisos> p= permiso.TipoPermiso();
                       permiso.setNombre_permiso(txtPermiso.getText());
                       Permisos opcion2=p.get(comboPermiso.getSelectionModel().getSelectedIndex());
                       int v=Integer.parseInt(Integer.toString(opcion2.getId_permiso()));
                       permiso.setId_permiso(v);
                       if (permiso.existePermiso(permiso.getNombre_permiso()) == 0){


                       try {
                           Conectar con = new Conectar();
                           PreparedStatement ps1=null;

                           String sql="update permisos set nombre_permiso= '"+permiso.getNombre_permiso()+"' where id_permiso= "+permiso.getId_permiso();

                           ps1 = con.getConnection().prepareStatement(sql);
                           ps1.execute();
                           comboPermiso.getItems().clear();
                           Permisos per= new Permisos();
                           comboPermiso.setItems(per.TipoPermiso());
                           tableUser.getItems().clear();
                           cargarListaPermisos();
                           txtPermiso.clear();
                           met.mensajeOk("Actualizacion exitosa");

                       } catch (SQLException ex) {
                           Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

                       }}else {

                           met.MensajeError("ya hay un permiso con ese nombre");
                       }




                   }
    @FXML
    public void verUsuario(MouseEvent event){
        Registros registros = new Registros();
        String opcion3= String.valueOf( comboUsuario.getSelectionModel().getSelectedItem());
        txtUsuario.setText(opcion3);
        registros.setUsuario(opcion3);
    }
    @FXML
    public void modificarUsuario(MouseEvent event){
        Registros registros = new Registros();
        List<Registros> p= registros.TipoUsuarioG();
        registros.setUsuario(txtUsuario.getText());
        Registros opcion2=p.get(comboUsuario.getSelectionModel().getSelectedIndex());
        int v=Integer.parseInt(Integer.toString(opcion2.getId_registros()));
        registros.setId_registros(v);
        if (registros.existeUsuario(registros.getUsuario()) == 0){


            try {
                Conectar con = new Conectar();
                PreparedStatement ps1=null;

                String sql="update usuarios set usuario= '"+registros.getUsuario()+"' where id_registros= "+registros.getId_registros();

                ps1 = con.getConnection().prepareStatement(sql);
                ps1.execute();
                comboUsuario.getItems().clear();
                comboUsuario.setItems(registros.TipoUsuarioG());
                tableUser11.getItems().clear();
                cargarListaUsuarios();
                txtUsuario.clear();
                met.mensajeOk("Actualizacion exitosa");

            } catch (SQLException ex) {
                Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

            }}else {

            met.MensajeError("ya hay un usuario con ese nombre");
        }




    }


    @FXML
                   public void modificarGrupo(MouseEvent event){
                      Grupos g= new Grupos();
                      List<Grupos> gr=g.TipoUsuario();
                      String opcion3= String.valueOf( comboGrupo1.getSelectionModel().getSelectedItem());
                      g.setNombre(txtGrupo.getText());
                      Grupos opcion2=gr.get(comboGrupo1.getSelectionModel().getSelectedIndex());
                      int v=Integer.parseInt(Integer.toString(opcion2.getId_grupo()));
                      g.setId_grupo(v);
                      if (g.existeGrupo(g.getNombre()) == 0){
                      try {
                          Conectar con = new Conectar();
                          PreparedStatement ps1=null;

                          String sql="update grupos set nombre= '"+g.getNombre()+"' where id_grupo= "+g.getId_grupo();

                          ps1 = con.getConnection().prepareStatement(sql);
                          ps1.execute();
                          comboGrupo1.getItems().clear();
                          Grupos per= new Grupos();
                          comboGrupo1.setItems(per.TipoUsuario());
                          tableUser.getItems().clear();
                          cargarListaPermisos();
                          txtGrupo.clear();
                          comboGrupo.getItems().clear();
                          comboGrupo.setItems(per.TipoUsuario());
                          tableUser11.getItems().clear();
                          cargarListaUsuarios();
                          met.mensajeOk("Actualizacion exitosa");

                      } catch (SQLException ex) {
                          Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);

                      }}else {

                          met.MensajeError("ya existe un grupo con ese nombre");
                      }

                    }

                  @FXML
                    private void Cargar(MouseEvent event) {

                        empresas.getTabs().add(cargar);
                        empresas.getTabs().remove(Menu);
                        empresas.getTabs().remove(propuestas);
                        empresas.getTabs().remove(candidatos);
                        empresas.getTabs().remove(permiso);
                        empresas.getTabs().remove(usuarios);

                    }

                    @FXML
                    private void actualizarDatos(MouseEvent event) {
                        empresas.getTabs().add(cargar);
                        empresas.getTabs().remove(Menu);
                        empresas.getTabs().remove(propuestas);
                        empresas.getTabs().remove(candidatos);
                        empresas.getTabs().remove(permiso);
                        empresas.getTabs().remove(usuarios);

                        String nom = txtNombreEmpresa.getText();
                        String pues = txtPuesto.getText();
                        String ind = txtIndustria.getText();
                        String ar = txtArea.getText();
                        String sec = txtSector.getText();
                        String det = txtDetalle.getText();

                    }

                    @FXML
                    private void mostrarPropuesta(MouseEvent event) {
                        empresas.getTabs().add(propuestas);
                        empresas.getTabs().remove(Menu);
                        empresas.getTabs().remove(cargar);
                        empresas.getTabs().remove(candidatos);
                        empresas.getTabs().remove(permiso);
                        empresas.getTabs().remove(usuarios);
                        empresas.getTabs().remove(usuarios2);

                    }

                    @FXML
                    private void ListarCandidatos(MouseEvent event) {
                        empresas.getTabs().add(candidatos);
                        empresas.getTabs().remove(Menu);
                        empresas.getTabs().remove(propuestas);
                        empresas.getTabs().remove(cargar);
                        empresas.getTabs().remove(permiso);
                        empresas.getTabs().remove(usuarios);
                        empresas.getTabs().remove(usuarios2);
                        table.getItems().clear();
                        listarAlumnos();

                    }


                    @FXML
                    private void mostrarPermisos(MouseEvent event) {
                        empresas.getTabs().add(permiso);
                        empresas.getTabs().remove(Menu);
                        empresas.getTabs().remove(propuestas);
                        empresas.getTabs().remove(candidatos);
                        empresas.getTabs().remove(cargar);
                        empresas.getTabs().remove(usuarios);
                        empresas.getTabs().remove(usuarios2);

                    }

                    @FXML
                    private void mostrarUsuarios(MouseEvent event) {
                        empresas.getTabs().add(usuarios);
                        empresas.getTabs().remove(Menu);
                        empresas.getTabs().remove(propuestas);
                        empresas.getTabs().remove(candidatos);
                        empresas.getTabs().remove(cargar);
                        empresas.getTabs().remove(permiso);
                        empresas.getTabs().remove(usuarios2);

                    }

                    @FXML
                    private void volver(MouseEvent event) {
                        comboUsuario.setDisable(false);
                        comboGrupo.setDisable(false);
                        comboGrupo1.setDisable(false);
                        comboPermiso.setDisable(false);
                        empresas.getTabs().add(Menu);
                        empresas.getTabs().remove(cargar);
                        empresas.getTabs().remove(propuestas);
                        empresas.getTabs().remove(candidatos);
                        empresas.getTabs().remove(permiso);
                        empresas.getTabs().remove(usuarios);
                        empresas.getTabs().remove(usuarios2);


                    }

                    @FXML
                    private  void cerrarSesion(MouseEvent mouseEvent){
                        System.exit(0);
                    }

                    @FXML
                    private void user1(MouseEvent event) {
                    empresas.getTabs().add(usuarios2);
                    empresas.getTabs().remove(cargar);
                    empresas.getTabs().remove(propuestas);
                    empresas.getTabs().remove(candidatos);
                    empresas.getTabs().remove(permiso);
                    empresas.getTabs().remove(usuarios);
                    empresas.getTabs().remove(Menu);


    }
                }


