 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_sqlite;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Noelia
 */
public class Ejemplo_SQLite {

    /**
     * @param args the command line arguments
     */
    
    private static Connection connect() {
        // SQLite connection string
        Connection conn=null;
        try {
            String url = "jdbc:sqlite:database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            //System.out.println("Se ha conectado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    
    /**
     * select all rows in the warehouses table
     */
    public static  void selectPersonas(){
       String sql = "SELECT * FROM personas";
        
        try (Connection conn =connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("id_personas") +  "\t" + 
                                   rs.getString("DNI") + "\t" +
                                   rs.getString("Nombre") + " " +
                                   rs.getString("Apellido 1")+ " " +
                                   rs.getString("Apellido 2"));
            }
            System.out.println("");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        // SQLite connection string
        
        // SQL statement for creating a new table
        
        String sql = "CREATE TABLE IF NOT EXISTS alumnos (\n"
                + "    id_alumnos integer PRIMARY KEY,\n"
                + "    nombre text NOT NULL,\n"
                + "    edad integer \n"
                + ");";
        
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("\tTabla creada");
        } catch (SQLException e) {
            System.out.println("No se ha podido crear la tabla");
        }
    }
    
    public static void insertAlumnos(int id,String name, int edad) {
        String sql = "INSERT INTO alumnos(id_alumnos,nombre,edad) VALUES(?,?,?)";
 
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, edad);
            pstmt.executeUpdate();
            System.out.println("\tDatos insertados");
        } catch (SQLException e) {
            System.out.println("No se ha podido insertar datos a la tabla");
        }
    }
    public static void selectAlumnos(){
       String sql = "SELECT * FROM alumnos";
        
        try (Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("id_alumnos") +  "\t" + 
                                   rs.getString("nombre") + "\t" +
                                   rs.getString("edad"));
            }
            System.out.println("");
        } catch (SQLException e) {
            System.out.println("No se puede mostrar datos de la tabla, puede ser que no exista");
        }
    }
    
    public static void borrarTabla(String tabla){
        String sql="DROP TABLE IF EXISTS "+ tabla + ";";
         try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("\tTabla borrada");
        } catch (SQLException e) {
            System.out.println("No se ha podido crear la tabla");
        }
    }
    
    public static void borrarAlumno(int id) {
        String sql = "DELETE FROM alumnos WHERE id_alumnos = ?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("\tTupla borrada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) throws SQLException {
        Ejemplo_SQLite ejemplo= new Ejemplo_SQLite ();
         System.out.println("Tabla de Personas");
        selectPersonas();
        System.out.println("Tabla de Alumnos");
        selectAlumnos();
        borrarTabla("alumnos");
         System.out.println("Tabla de Alumnos");
        selectAlumnos();
        
        createNewTable();
        insertAlumnos(1,"Noelia", 20);
        insertAlumnos(2,"Carlos", 29);
        insertAlumnos(3,"Patri", 20);
        insertAlumnos(4,"Alex", 24);
        insertAlumnos(5,"Marta", 23);
         System.out.println("Tabla de Alumnos");
        selectAlumnos();
       
        borrarAlumno(1);
        System.out.println("Tabla de Alumnos");
        selectAlumnos();
         
    }
    
}
