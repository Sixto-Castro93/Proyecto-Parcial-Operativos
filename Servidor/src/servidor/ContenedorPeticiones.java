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
        contenedovacio = Boolean.FALSE;
        if (contenido.size() != 0) {
            contenedovacio = Boolean.TRUE;
            tem=contenido.poll();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        tem.getInputStream()));
                        PrintWriter out = new PrintWriter(tem.getOutputStream(), true);

        System.out.println("Contenedor saco de la  cola ");
            return contenido.poll();
        }
        return null;
    }

    public synchronized void put(Socket value) throws IOException {

        contenido.add(value);
        contenedovacio = Boolean.FALSE;
                    PrintWriter out = new PrintWriter(value.getOutputStream(), true);
        out.println("Atendido y en cola");
        System.out.println("productor puso en cola ");
        notify();
    }
}
