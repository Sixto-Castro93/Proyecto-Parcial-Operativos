/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xtratech
 */
public class clientePrueba {
    
    public static void main(String[] args) throws IOException {
        int i = 0;
        ArrayList<String> comandos = new ArrayList<>();
//        comandos.add("put 0988199825 \"poiupoiu\"");
//        comandos.add("del 0988199825");
//        comandos.add("put 0988199825 \"poiupoiu\"");
//        comandos.add("put 0988199826 \"poiupoiu\"");
//        comandos.add("get 0988199825");
        
//        comandos.add("list");
//        comandos.add("list");
//        comandos.add("list");
//        comandos.add("list");
//        comandos.add("list");

        while(i<1) {
           
            new Thread() {
                public void run() {
                    clientePrueba cp = new clientePrueba();
                    try {
//                        cp.llamadas(comandos.get(0));
                        cp.llamadas("put 0988199825 \"poiupoiu\"",args[0],args[1]);
//                        cp.llamadas("list");
//                        cp.llamadas();
                    } catch (IOException ex) {
                        Logger.getLogger(clientePrueba.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
            i++;
        }

    }
    
    
    
    public void llamadas(String str,String args1,String args2) throws IOException {
        
        Cliente cli = new Cliente();

        System.out.println("Iniciando cliente\n");
        cli.startClient("["+ str + "]",args1,args2);
//        cli.startClient("get 0988199820");
//        cli.startClient("[list]");

    }

    
    
    
}
