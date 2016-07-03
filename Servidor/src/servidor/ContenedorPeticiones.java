/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author emele_000
 */
public class ContenedorPeticiones {

    public LinkedList<Socket> contenido;
    private boolean contenedovacio = Boolean.TRUE;

    public ContenedorPeticiones() {
        this.contenido = new LinkedList<>();
    }

    public synchronized Socket get() throws IOException {
        Socket tem;
        while (contenedovacio) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Contenedor: Error en get -> " + e.getMessage());
            }
        }
        if (contenido.size() != 0) {
           notify();
            System.out.println("Contenedor saco de la  cola ");
            return contenido.poll();
        }
        else {
            contenedovacio = Boolean.TRUE;
        return null;}
    }

    public synchronized void put(Socket value) throws IOException {

        contenido.add(value);
        contenedovacio = Boolean.FALSE;
        System.out.println("productor puso en cola ");
        notifyAll();
    }
}
