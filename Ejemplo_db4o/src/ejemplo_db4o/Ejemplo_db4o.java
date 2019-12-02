/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_db4o;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 *
 * @author Noelia
 */
public class Ejemplo_db4o {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ObjectContainer db = Db4o.openFile("personas.db");
        Persona examplePlayer = new Persona(null,0);
        ObjectSet result=db.queryByExample(examplePlayer);
        db.delete(examplePlayer);
        
        Persona p1=new Persona("Noelia",20);
        Persona p2=new Persona("Pepe",20);
        Persona p3=new Persona("Maria",39);
        Persona p4=new Persona("Antonio",70);
        
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);
        ObjectSet result1=db.queryByExample(examplePlayer);
        System.out.println(result1.size());
        while(result1.hasNext()) {
            System.out.println(result1.next());
        }
        db.close();
       
    }
    
}
