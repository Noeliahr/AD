/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosserializables;

import java.io.Serializable;

/**
 *
 * @author Charlowit
 */
public class Empleado implements Serializable {
    
    private int id;
    private String apellidos;
    private int dep;
    private double salario;

    Empleado(){
        this.id = 0;
        this.apellidos = "";
        this.dep = 0;
        this.salario = 0;
    }
    public Empleado(int id, String apellidos, int dep, double salario) {
        this.id = id;
        this.apellidos = apellidos;
        this.dep = dep;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
   
}
