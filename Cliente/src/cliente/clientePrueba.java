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
     static  int i = 0;
    public static void main(String[] args) throws IOException {
       
        ArrayList<String> comandos = new ArrayList<>();
        comandos.add("put, 0988199825, poiupoiu");
        comandos.add("del, 0988199825");
        comandos.add("get, 0988199825");
        comandos.add("put, 0988199825, poiupoiu");
        comandos.add("put, 0988199826, poiupoiu");
      
        
//        comandos.add("list");
//        comandos.add("list");
//        comandos.add("list");
//        comandos.add("list");
//        comandos.add("list");

       for(i=0;i<100;i++) { 
           
            ThreadPrueba tp = new ThreadPrueba(0, comandos, args[0],args[1]);
            tp.start();
        }

    }
    
    
    
    public void llamadas(String str,String args1,String args2) throws IOException {
        
        Cliente cli = new Cliente();

        System.out.println("Iniciando cliente\n"+"["+ str + "]");
        cli.startClient("["+ str + "]",args1,args2);
//        cli.startClient("get 0988199820");
//        cli.startClient("[list]");

    }

    
    
    
}
