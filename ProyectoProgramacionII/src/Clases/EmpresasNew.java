package Clases;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresasNew {
    private final SimpleStringProperty puesto;
    private final SimpleIntegerProperty id_empresas;

    private final  SimpleStringProperty nombre_empresa;
    private final SimpleStringProperty area;
    private final SimpleStringProperty sector;
    private final SimpleStringProperty detalle;
    private final SimpleStringProperty industria;


    public static final String DEFAULT_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DEFAULT_URL = "jdbc:mysql://localhost/bolsadetrabajo";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "";


    public EmpresasNew()
    {
        this.nombre_empresa= new SimpleStringProperty("");
        this.sector= new SimpleStringProperty("");
        this.area= new SimpleStringProperty("");
        this.detalle= new SimpleStringProperty("");
        this.industria= new SimpleStringProperty("");
        this.id_empresas = new SimpleIntegerProperty(0);
        this.puesto= new SimpleStringProperty("");
    }


    public String getPuesto() {
        return puesto.get();
    }

    public SimpleStringProperty puestoProperty() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto.set(puesto);
    }

    public int getId_empresas() {
        return id_empresas.get();
    }

    public SimpleIntegerProperty id_empresasProperty() {
        return id_empresas;
    }

    public void setId_empresas(int id_empresas) {
        this.id_empresas.set(id_empresas);
    }

    public String getNombre_empresa() {
        return nombre_empresa.get();
    }

    public SimpleStringProperty nombre_empresaProperty() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa.set(nombre_empresa);
    }

    public String getArea() {
        return area.get();
    }

    public SimpleStringProperty areaProperty() {
        return area;
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public String getSector() {
        return sector.get();
    }

    public SimpleStringProperty sectorProperty() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector.set(sector);
    }

    public String getDetalle() {
        return detalle.get();
    }

    public SimpleStringProperty detalleProperty() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle.set(detalle);
    }

    public String getIndustria() {
        return industria.get();
    }

    public SimpleStringProperty industriaProperty() {
        return industria;
    }

    public void setIndustria(String industria) {
        this.industria.set(industria);
    }

    public List<EmpresasNew> ListarEmpresas()  {
        Connection Con = null   ;
        List<EmpresasNew> lEmp = new ArrayList<EmpresasNew>();
        try {
            Class.forName(DEFAULT_DRIVER_CLASS);
            Con = DriverManager.getConnection(DEFAULT_URL,DEFAULT_USERNAME, DEFAULT_PASSWORD);
            PreparedStatement ps1=Con.prepareStatement("select * from empresas");
            ResultSet rs1=ps1.executeQuery();
            while (rs1.next())
            {
                EmpresasNew emp = new EmpresasNew();
                emp.setNombre_empresa(rs1.getString("nombre_empresa"));
                emp.setPuesto(rs1.getString("puesto"));
                emp.setArea(rs1.getString("area"));
                emp.setSector(rs1.getString("sector"));
                emp.setIndustria(rs1.getString("industria"));
                emp.setDetalle(rs1.getString("detalle"));
                lEmp.add (emp);
            }
            return lEmp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try { Con.close(); } catch (Exception e) { /* ignored */ }
        }
    }
}
