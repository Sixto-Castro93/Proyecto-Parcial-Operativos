/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emele_000
 */
public class ThreadConsumidor extends Thread{
        private final int numero;
    private Socket socket = null;
    private final ContenedorPeticiones contenedor;

    public ThreadConsumidor(ContenedorPeticiones contenedor, Integer numero) {
        super("ThreadConsumidor");
        this.numero = numero;
        this.contenedor = contenedor;
    }

    @Override
    public void run() {
        while (true) {

            try {
                socket=contenedor.get();
            } catch (IOException ex) {
                Logger.getLogger(ThreadConsumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
//                 try {
//                socket.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ThreadConsumidor.class.getName()).log(Level.SEVERE, null, ex);
//            }
          
        }
    }
}
