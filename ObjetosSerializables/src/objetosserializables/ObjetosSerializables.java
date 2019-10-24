/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosserializables;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Charlowit
 */
public class ObjetosSerializables {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        File fichero = new File("FichEmpleado.dat");//declara el fichero
        FileOutputStream fileout = new FileOutputStream(fichero,true);  //crea el flujo de salida
         //conecta el flujo de bytes al flujo de datos
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);  

        ArrayList<Empleado> empleados = new ArrayList();
        
        empleados.add(new Empleado(111, "Hernandez", 555, 1338.9));
        empleados.add(new Empleado(22,"Burgos",66,2005.33));
        empleados.add(new Empleado(55, "Gomez",5,1000.6));
        empleados.add(new Empleado(8,"Rodriguez",96,900.99));
            
        for (Empleado empleado : empleados){    	  
               dataOS.writeObject(empleado); 
        }     
        dataOS.close();  
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
        try {
                while (true) { 
                        Empleado empleado = (Empleado) dataIS.readObject(); 
                        System.out.printf("Id: %d , Nombre: %s, Departamento: %d, Salario: %.2f  \n",
                                        empleado.getId(), empleado.getApellidos(), empleado.getDep(), empleado.getSalario());

                }
        } catch (EOFException eo) {
                //System.out.println("FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); 
    }
}
    

