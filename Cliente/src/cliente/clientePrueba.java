/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xtratech
 */
public class clientePrueba {
    
    public static void main(String[] args) throws IOException
    {
    int i=0;
    while(i<20){
        new Thread() { 
            public void run() {
                clientePrueba cp = new clientePrueba();
                try {
                    cp.llamadas(args[0],args[1]);
                } catch (IOException ex) {
                    Logger.getLogger(clientePrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        i++;
    }
        
        
        
    }
    
    
    
    public void llamadas(String args1,String args2) throws IOException{
        int i=0;
        
            Cliente cli = new Cliente(); 
        
            System.out.println("Iniciando cliente\n");
            cli.startClient(args1,args2);
          
        
    }
    
    
    
    
}
