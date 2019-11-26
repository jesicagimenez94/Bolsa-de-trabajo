package Clases;

import Conexion.Conectar;
import Vistas.ControllerMenuBusquedas;
import Vistas.MostrarVistas;
import com.sun.deploy.util.FXLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Registros extends Conectar {
    int id_registros;
    public String nombre_grupo;
    String id_tipoUsuario;
    String nombre;
    String usuario;
    String contrasenia;
    String repetirContrasenia;

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public Registros(int id_registros, String id_tipoUsuario, String usuario, String contraseña, String repetirContraseña) {
        this.id_registros = id_registros;
        this.id_tipoUsuario = id_tipoUsuario;
        this.usuario = usuario;
        this.contrasenia = contraseña;
        this.repetirContrasenia = repetirContraseña;
    }

    public Registros(String usuario,int id_registros) {
        this.id_registros = id_registros;
        this.usuario = usuario;
    }


    public Registros(String nombre) {
        this.nombre = nombre;
    }

    public Registros() {
    }


    public Registros(int id_registros) {
        this.id_registros = id_registros;
    }

    public String getId_tipoUsuario() {
        return id_tipoUsuario;
    }

    public void setId_tipoUsuario(String id_tipoUsuario) {
        this.id_tipoUsuario = id_tipoUsuario;
    }

    public int getId_registros() {
        return id_registros;
    }

    public void setId_registros(int id_registros) {
        this.id_registros = id_registros;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contraseña) {
        this.contrasenia = contraseña;
    }

    public String getRepetirContrasenia() {
        return repetirContrasenia;
    }

    public void setRepetirContrasenia(String repetirContraseña) {
        this.repetirContrasenia = repetirContraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public boolean Registrar(Registros user){//metodo que guarda la informacion del registro

        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection con = getConnection();

        String sql="INSERT INTO usuarios (usuario,contrasenia,repetirContrasenia,id_tipoUsuario)VALUES(?,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getContrasenia());
            ps.setString(3, user.getRepetirContrasenia());
            ps.setString(4, user.getId_tipoUsuario());
            ps.execute();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


    }





    @Override
        public String toString(){
        return getUsuario();
}


    public int existeUsuario(String user1){ //metodo para saber  si ya hay registros con ese nombre de usuario

        PreparedStatement ps=null;
        ResultSet rs= null;
        Connection con = getConnection();

        String sql="SELECT count(id_registros)FROM usuarios WHERE usuario=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, user1);
            rs =ps.executeQuery();//para que me devuelva datos porque es una consulta

            if(rs.next()){
                return rs.getInt(1);// me devuelve cuantos registros hay con ese nombre de usuario
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }

    }


    public List<Registros> ListarUsuarios() {//metodo que devuelve la lista de los usuarios
        Conectar con = new Conectar();
        List<Registros> listaReg = new ArrayList<Registros>();
        String sql = "SELECT u.id_registros,u.usuario,gr.nombre FROM usuarios AS u JOIN grupos_usuarios AS g ON u.id_registros=g.id_usuario JOIN grupos AS gr ON gr.id_grupo= g.id_grupo ";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registros reg= new Registros();
                reg.setId_registros(rs.getInt("id_registros"));
                reg.setUsuario(rs.getString("usuario"));
                reg.setNombre(rs.getString("nombre"));


                listaReg.add(reg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca1");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaReg;
    }

    public  List<Registros> ListarGrupos() {//metodo que devuelve la lista de los usuarios
        Conectar con = new Conectar();
        List<Registros> listaGrup = new ArrayList<Registros>();
        String sql = "SELECT nombre FROM grupos";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registros reg= new Registros();
                reg.setNombre(rs.getString("nombre"));
                listaGrup.add(reg);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca1");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaGrup;
    }

    public  int MostrarIdRegistros(Registros user1) { //metodo para saber  si ya hay registros con ese nombre de usuario

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conectar con = new Conectar();

        String sql = "SELECT usuario FROM usuarios WHERE id_registros=?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setInt(1, user1.getId_registros());
            rs = ps.executeQuery();//para que me devuelva datos porque es una consulta

            if (rs.next()) {
                rs.getInt("id_registros");
            }

      return  getId_registros();
        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public  boolean BajaRegistros(Registros user1) { //metodo para saber  si ya hay registros con ese nombre de usuario

        PreparedStatement ps = null;
        Conectar con = new Conectar();

        String sql = "DELETE FROM usuarios WHERE usuario=?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, user1.getUsuario());
            ps.executeUpdate();



            return  true;
        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }


    @FXML
    public ObservableList<Registros> TipoUsuarioG() {//combo usuario
        Conectar con = new Conectar();
        ObservableList<Registros> listaGrupos = FXCollections.observableArrayList();

        try {
            String sql = "SELECT id_registros,usuario FROM usuarios";
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listaGrupos.addAll(new Registros(rs.getString("usuario"),rs.getInt("id_registros")));



            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaGrupos;

    }



}

