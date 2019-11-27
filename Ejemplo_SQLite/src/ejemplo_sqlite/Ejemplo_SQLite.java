/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_sqlite;

import java.sql.DriverManager;
import java.sql.Connection;
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
    
    private Connection connect() {
        // SQLite connection string
        Connection conn=null;
        try {
            String url = "jdbc:sqlite:database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Se ha conectado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    
    /**
     * select all rows in the warehouses table
     */
    public  void selectAll(){
       String sql = "SELECT * FROM personas";
        
        try (Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("id_personas") +  "\t" + 
                                   rs.getString("DNI") + "\t" +
                                   rs.getString("Nombre") + " " +
                                   rs.getString("Apellido 1")+ " " +
                                   rs.getString("Apellido 2"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS alumnos (\n"
                + "    id_alumnos integer PRIMARY KEY,\n"
                + "    nombre text NOT NULL,\n"
                + "    edad real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) throws SQLException {
       
       
        Ejemplo_SQLite ejemplo= new Ejemplo_SQLite ();
        ejemplo.selectAll();
        ejemplo.createNewTable();
         
    }
    
}
