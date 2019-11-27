/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerxml;

import java.io.*;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Noelia
 */
public class LeerXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
              throws FileNotFoundException, IOException, SAXException{
		
	XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
	GestionContenido gestor= new GestionContenido();  
	procesadorXML.setContentHandler(gestor);
 	InputSource fileXML = new InputSource("Coches.xml");	    
        procesadorXML.parse(fileXML);        	      
    }
    
}
class GestionContenido extends DefaultHandler {	 
            private ArrayList <Coche> coches;
            private String elemento;
            private Coche aux;
	    public GestionContenido() {
	        super();
                coches=new ArrayList<>();
	    }	    
	    public void startDocument() {
	        //System.out.println("Comienzo del Documento XML");
	    }	    
	    public void endDocument() {
                for (int i=0; i<coches.size(); i++){
                    System.out.println("Coche "+i+": Marca: "+ coches.get(i).getMarca()+" Matricula: "+coches.get(i).getMatricula());
                }
	        //System.out.println("Final del Documento XML");
	    }	 	    
	    public void startElement(String uri, String nombre,
	              String nombreC, Attributes atts) {
                elemento=nombre;
                if (elemento=="coche"){
                    aux=new Coche();
                }
	        //System.out.printf("\t Principio Elemento: %s %n",nombre);	 	        
	    } 	
	    public void endElement(String uri, String nombre, 
                          String nombreC) {
                if (nombre=="coche"){
                    coches.add(aux);
                }
	       // System.out.printf("\tFin Elemento: %s %n", nombre);
	    }	
	    public void characters(char[] ch, int inicio, int longitud) 
                                            throws SAXException {
		   String car=new String(ch, inicio, longitud);
               //quitar saltos de línea	
		   car = car.replaceAll("[\t\n]","");
                   
                   if (elemento=="Matricula"){
                       aux.setMatricula(car);
                   }else if(elemento=="Marca"){
                       aux.setMarca(car);
                   }
		   //System.out.printf ("\t Caracteres: %s %n", car);		
	    }	

}
