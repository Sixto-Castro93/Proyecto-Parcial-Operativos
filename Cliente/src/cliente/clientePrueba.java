/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;

/**
 *
 * @author Xtratech
 */
public class clientePrueba {
    
    public static void main(String[] args) throws IOException
    {  
        clientePrueba cp = new clientePrueba();
        cp.llamadas();
    }
    
    
    
    public void llamadas() throws IOException{
        int i=0;
        while (i<100) {
            Cliente cli = new Cliente(); 
        
            System.out.println("Iniciando cliente\n");
            cli.startClient();
        }  
        
    }
    
    
    
}
