package Clases;

import Conexion.Conectar;
import Vistas.MostrarVistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;





public class Registros extends Conectar {
    int id_registros;
    int id_tipoUsuario;
    String usuario;
    String contrasenia;
    String repetirContrasenia;

    public Registros(int id_registros, int id_tipoUsuario, String usuario, String contraseña, String repetirContraseña) {
        this.id_registros = id_registros;
        this.id_tipoUsuario = id_tipoUsuario;
        this.usuario = usuario;
        this.contrasenia = contraseña;
        this.repetirContrasenia = repetirContraseña;
    }



    public Registros() {
    }

    public int getId_tipoUsuario() {
        return id_tipoUsuario;
    }

    public void setId_tipoUsuario(int id_tipoUsuario) {
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



    public boolean Registrar(Registros user){//metodo que guarda la informacion del registro

        PreparedStatement ps=null;
        Connection con = getConnection();

        String sql="INSERT INTO usuarios (usuario,contrasenia,repetirContrasenia,id_tipoUsuario)VALUES(?,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getContrasenia());
            ps.setString(3, user.getRepetirContrasenia());
            ps.setInt(4, user.getId_tipoUsuario());
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }




    public boolean login(Registros user){ //metodo para iniciar sesion

        PreparedStatement ps=null;
        ResultSet rs= null;
        Connection con = getConnection();

        String sql="SELECT id_registros,usuario,contrasenia,id_tipousuario FROM usuarios WHERE usuario=?";//primero vemos si el usuario existe
        try {

            MostrarVistas m= new MostrarVistas();
            ps=con.prepareStatement(sql);
            ps.setString(1, user.getUsuario());
            rs =ps.executeQuery();//para que me devuelva datos porque es una consulta

            if(rs.next()){
                if(user.getContrasenia().equals(rs.getString(3))){//en caso que las contraseñas coincidan
                    user.setId_registros(1);
                    if( user.getUsuario().equalsIgnoreCase("admin123")){
                        m.mostrarVistaEmpresas();}
                     else{
                        m.mostrarVistaMenuAlumnos(); }
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
        String sql = "SELECT usuario FROM usuarios";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registros reg= new Registros();
                reg.setUsuario(rs.getString("usuario"));

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



}

