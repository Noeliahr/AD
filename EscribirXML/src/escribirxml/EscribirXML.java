/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escribirxml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Noelia
 */
public class EscribirXML {
   
   public static void main(String args[]) throws IOException{
    ArrayList <Coche>  coches = new ArrayList<>();
    coches.add(new Coche("SEAT", "7418AAA"));
    coches.add(new Coche("FORD", "7417BAA"));
    coches.add(new Coche("FIAT", "1236BBB"));
    coches.add(new Coche("AUDI", "1111BBB"));
   
     
   DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
  
   try{
     DocumentBuilder builder = factory.newDocumentBuilder();
     DOMImplementation implementation = builder.getDOMImplementation();
     Document document = (Document) implementation.createDocument(null, "Coches", null);
     document.setXmlVersion("1.0"); 
   
        for(int i=0; i<coches.size(); i++){   

            Element raiz = document.createElement("coche"); //nodo empleado
            document.getDocumentElement().appendChild(raiz);                        
            CrearElemento("Marca",coches.get(i).getMarca(), raiz, document); 
            CrearElemento("Matricula",coches.get(i).getMatricula(), raiz, document); 
        }//fin del for que recorre el fichero
		
     Source source = new DOMSource((Node) document);
     Result result = new StreamResult(new java.io.File("Coches.xml"));        
     Transformer transformer = TransformerFactory.newInstance().newTransformer();
     transformer.transform(source, result);
    
    }catch(Exception e){ System.err.println("Error: "+e); }
   
 }//fin de main
 
 //Inserción de los datos del empleado
 static void  CrearElemento(String datoEmple, String valor, Element raiz, Document document){
    Element elem = document.createElement(datoEmple); 
    Text text = document.createTextNode(valor); //damos valor
    raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
    elem.appendChild(text); //pegamos el valor		 	
 } 
}
