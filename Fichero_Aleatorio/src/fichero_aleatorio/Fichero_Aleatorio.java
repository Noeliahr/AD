/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichero_aleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Noelia
 */
public class Fichero_Aleatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        File fichero = new File("Aleatorio.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        
        
        Empleado e1= new Empleado(111, "Hernandez", 555, 1338.9);
        Empleado e2= new Empleado(22,"Burgos",66,2005.33);
        Empleado e3= new Empleado(55, "Gomez",5,1000.6);
        Empleado e4= new Empleado(8,"Rodriguez",96,900.99);
        
        ArrayList <Empleado> empleados= new ArrayList<>();
        empleados.add(e1);
        empleados.add(e2);
        empleados.add(e3);
        empleados.add(e4);
        
        StringBuffer buffer = null;
        for (int i=0; i<empleados.size(); i++){
            try {
                file.writeInt(empleados.get(i).getId());
                buffer = new StringBuffer(empleados.get(i).getApellidos());      
                buffer.setLength(10); 
                file.writeChars(buffer.toString());
                file.writeInt(empleados.get(i).getDep());      
                file.writeDouble(empleados.get(i).getSalario());
            } catch (IOException ex) {
                System.out.println(ex);;
            }
        }
        
        file.close();
        
        
        File fichero1 = new File("Aleatorio.dat");
        RandomAccessFile file1 = new RandomAccessFile(fichero, "r");
        char apellido[] = new char[10];
        ArrayList <Empleado> empleados1= new ArrayList<>();
        
        for (int posicion=0; posicion<file1.length(); posicion+=36){
            file1.seek(posicion);
            Empleado aux= new Empleado();
            aux.setId(file1.readInt());
            String surname="";
            for (int i = 0; i < apellido.length; i++) {         
                apellido[i] = file1.readChar();
                surname+=Character.toString(apellido[i]);
            }
            aux.setApellidos(surname);
            aux.setDep(file1.readInt());
            aux.setSalario(file1.readDouble());
            
            empleados1.add(aux);
        }
        for (int i=0; i<empleados1.size(); i++){
            if(empleados1.get(i).getId() >0){
                 System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n",empleados1.get(i).getId(),   
                    empleados1.get(i).getApellidos(), empleados1.get(i).getDep(), empleados1.get(i).getSalario());
            }
        }
    }
    
}
