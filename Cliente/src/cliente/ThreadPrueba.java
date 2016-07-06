/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import static cliente.clientePrueba.i;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emele_000
 */
public class ThreadPrueba extends Thread {

    private final int i;
    private ArrayList<String> comandos = new ArrayList<>();
    String arg1, arg2;

    public ThreadPrueba( int i, ArrayList<String> comandos, String arg1, String arg2) {
        super("ThreadPrueba");
        this.i = i;
        this.comandos = comandos;
        this.arg1=arg1;
        this.arg2=arg2;        
                
    }

    @Override
    public void run() {
                    
                    clientePrueba cp = new clientePrueba();
                    try {
                        cp.llamadas(comandos.get(i),arg1,arg2);
//                        cp.llamadas("put, 0988199825, poiupoiu",args[0],args[1]);
                    //  cp.llamadas("list",args[0],args[1]);
//                        cp.llamadas();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(clientePrueba.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
}
