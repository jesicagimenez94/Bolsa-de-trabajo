package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Alumnos {
    int id_alumnos;
    String nombre,apellido,mail,mensaje;
    String dni;
    byte[] Archivo_cv;
    String nom_archivo;


    public Alumnos() {

    }

    public Alumnos(int id_alumnos, String nombre, String apellido, String mail, String mensaje, String dni, byte[] Archivo_cv, String nom_archivo) {
        this.id_alumnos = id_alumnos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.mensaje = mensaje;
        this.dni = dni;
        this.Archivo_cv = Archivo_cv;
        this.nom_archivo = nom_archivo;
    }





    public String getNom_archivo() {
        return nom_archivo;
    }

    public void setNom_archivo(String nom_archivo) {
        this.nom_archivo = nom_archivo;
    }



    public int getId_alumnos() {
        return id_alumnos;
    }

    public void setId_alumnos(int id_alumnos) {
        this.id_alumnos = id_alumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public byte[] getArchivo_cv() {
        return Archivo_cv;
    }

    public void setArchivo_cv(byte[] archivo_cv) {
        Archivo_cv = archivo_cv;
    }




    public void insertarCV(Alumnos alumnos){  //metodo donde insertamos los datos del alumno a la base de datos


        Conectar con= new Conectar();
        String sql="INSERT INTO alumnos(nombre,apellido,mail,dni,nom_archivo,archivo_cv,mensaje)VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps=null;

        try{
            ps=con.getConnection().prepareStatement(sql);
            ps.setString(1,alumnos.getNombre());
            ps.setString(2,alumnos.getApellido());
            ps.setString(3,alumnos.getMail());
            ps.setString(4,alumnos.getDni());
            ps.setString(5, alumnos.getNom_archivo());
            ps.setBytes(6,alumnos.getArchivo_cv());
            ps.setString(7,alumnos.getMensaje());
            ps.executeUpdate();

        }catch (SQLException e){

            System.out.println(e.getMessage());
        }finally {
            try{
                ps.close();
                con.desconectar();
            }catch (SQLException e){}
        }

    }

    public List<Alumnos> ListarCurriculum() {//metodo que devuelve la lista de los curriculums
        Conectar con = new Conectar();
        List<Alumnos> listaAlum = new ArrayList<Alumnos>();
        String sql = "SELECT nombre,apellido,mail,dni,archivo_cv,mensaje FROM alumnos";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Alumnos alu= new Alumnos();
                alu.setNombre(rs.getString("nombre"));
                alu.setApellido(rs.getString("apellido"));
                alu.setMail(rs.getString("mail"));
                alu.setDni(rs.getString("dni"));
                        alu.setArchivo_cv(rs.getBytes("archivo_cv"));
                        alu.setMensaje(rs.getString("mensaje"));

                        listaAlum.add(alu);
                        }
                        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("aca1");
                        } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("aca2");
                        }
                        return listaAlum;
                        }
                        }
