/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaenfichero;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author Noelia
 */
public class BuscaenFichero {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if(args.length <2){
            System.out.println("Indica por favor el nombre de fichero y la cadena a buscar");
            return;
        }
        String nomFich= args[0];
        String cadena="";
        boolean encontrado=false;
        for (int i=1; i<args.length; i++){
             cadena+=args[i];
        }
        
        try(BufferedReader fbr= new BufferedReader(new FileReader(nomFich))){
            int i=0;
            String linea=fbr.readLine();
            while(linea!= null){
                //System.out.println("cadena"+cadena+".");
                //System.out.println("linea"+linea+".");
                if (linea.indexOf(cadena)>=0){
                    System.out.println("La cadena "+ cadena +" esta en la linea: "+ i +" y la columna: "+linea.indexOf(cadena));
                    encontrado=true;
                }
                linea=fbr.readLine();
                i++;
            }
            if (encontrado==false) {
                    System.out.println("La cadena no esta en el fichero");
                }
        }catch(FileNotFoundException e){
            System.out.println("No existe fichero " + nomFich);
        }catch(IOException e){
            System.out.println("Error de E/S: "+e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
