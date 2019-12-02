/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_db4o;

/**
 *
 * @author Noelia
 */
public class Persona {
    private String nombre;
    private int edad;
    
    public Persona(){
        nombre="";
        edad=0;
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setEdad(int edad){
        this.edad=edad;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getEdad(){
        return this.edad;
    }
    
    public String toString() {
        String datos= nombre + " " + edad;
        return datos;
    } 
}
