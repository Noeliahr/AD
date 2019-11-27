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
 * @author Noelia
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
        
        empleados.add(new Empleado(1,"Galvez",1,1200.50));
        empleados.add(new Empleado(2,"Payares",2,5400.50));
        empleados.add(new Empleado(3,"Nieto",3,1300.50));
        empleados.add(new Empleado(4,"Alarcon",3,1340.50));
        empleados.add(new Empleado(5,"Hernandez",2,1760.50));
        empleados.add(new Empleado(6,"Cabellos",1,2220.50));
            
        for (Empleado empleado : empleados){ //recorro los arrays    	  
               dataOS.writeObject(empleado); //escribo la persona en el fichero
        }     
        dataOS.close();  //cerrar stream de salida   
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
        try {
                while (true) { // lectura del fichero
                        Empleado empleado = (Empleado) dataIS.readObject(); // leer una Persona
                        System.out.printf("Id: %d , Nombre: %s, Departamento: %d, Salario: %.2f  \n",
                                        empleado.getId(), empleado.getApellidos(), empleado.getDep(), empleado.getSalario());

                }
        } catch (EOFException eo) {
                System.out.println("FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
    }
}
    

