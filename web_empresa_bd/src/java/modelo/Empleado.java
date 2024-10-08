package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase que representa un empleado, heredando de la clase Persona.
 * Autor: dave7
 */
public class Empleado extends Persona {
    private String codigo;
    private int id_puesto;
    private Conexion cn;

    public Empleado() {
        // Constructor vacío
    }

    public Empleado(String codigo, int id_puesto, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id_puesto = id_puesto;
        this.cn = new Conexion(); // Inicializar la conexión aquí
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    @Override
    public int agregar() {
        int retorno = 0;
        String query = "INSERT INTO empleados(codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puesto) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            cn.abrir_conexion();
            try (PreparedStatement parametro = cn.conexionBD.prepareStatement(query)) {
                parametro.setString(1, getCodigo());
                parametro.setString(2, getNombres());
                parametro.setString(3, getApellidos());
                parametro.setString(4, getDireccion());
                parametro.setString(5, getTelefono());
                parametro.setString(6, getFecha_nacimiento());
                parametro.setInt(7, getId_puesto());

                retorno = parametro.executeUpdate();
            } 
        } catch (SQLException ex) {
            System.out.println("Error al agregar empleado: " + ex.getMessage());
            retorno = 0;
        } finally {
            cn.cerrar_conexion();
        }

        return retorno;
    }
}
