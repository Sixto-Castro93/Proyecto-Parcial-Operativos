/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreadServer extends Thread {

    private Socket socket = null;

    private BaseNoSql base = null;

    public ThreadServer(Socket socket, BaseNoSql base) {
        super("ThreadServer");
        this.socket = socket;
        this.base = base;
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
            String claveMap;
            out.println("Te estoy atendiendo en hora buena, dime tu petición. Si desea consulte los comandos disponibles con 'help'");
            while ((inputLine = in.readLine()) != null) {
                inputLine=inputLine.substring(1,inputLine.length()-1);
                System.out.println(inputLine);
                String[] comando = inputLine.split(", ");
                System.out.println(Arrays.toString(comando));
                String cmd = comando[0].toLowerCase();
                switch (cmd) {
                    case "get":
                        String clave = comando[1];
                        outputLine = base.getvalor(clave);
                        out.println(outputLine);
                        break;
                    case "put":
                        outputLine = base.putvalor(comando[1], comando[2]);
                        out.println(outputLine);
                        break;
                    case "set":

                        outputLine = base.setvalor(comando[1], comando[2]);

                        out.println(outputLine);
                        break;
                    case "del":
                        outputLine = base.delvalor(comando[1]);
                        out.println(outputLine);
                        break;
                    case "list":
                        ArrayList lista;
                        lista = base.listvalor();
                        //System.out.println(outputLine);
                        out.println("lista" + " " + lista);
                        //out.println(outputLine);
                        break;
                    default:
                        outputLine = "ERROR: Comando no válido, puede ver los comandos disponibles con el comando 'help'";
                        out.println(outputLine);
                        break;

                    /*if (inputLine.equals("exit")) {
                     outputLine = "Bye.";
                     } else {
                     outputLine = "Bueno aun no me han implementado respuestas logicas , espero lo hagan pronto disculpa";
                     }
                     out.println(outputLine);
                     if (outputLine.equals("exit")) {
                     break;
                     }*/
                }
            }
            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
        }
    }
}
