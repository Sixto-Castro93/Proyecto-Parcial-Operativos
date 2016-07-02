/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;

/**
 *
 * @author emele_000
 */

public class ThreadServer extends Thread {

    private Socket socket = null;

    public ThreadServer(Socket socket) {
        super("ThreadServer");
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String inputLine, outputLine;
            int i = 0;
            out.println("Te estoy atendiendo en horabuena , dime tu petición");
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("exit")) {
                    outputLine = "Bye.";
                } else {
                    outputLine = "Bueno aun no me han implementado respuestas logicas , espero lo hagan pronto disculpa";
                }
                out.println(outputLine);
                if (outputLine.equals("exit")) {
                    break;
                }
            }
            out.close();
            in.close();
            socket.close();
         
        } catch (IOException e) {
        }
    }
}
