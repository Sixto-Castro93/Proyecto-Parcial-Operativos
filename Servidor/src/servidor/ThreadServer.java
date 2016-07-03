/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author emele_000
 */
public class ThreadServer extends Thread {

    private Socket socket = null;
    
    private BaseNoSql base = null;

    public ThreadServer(Socket socket,BaseNoSql base) {
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
            out.println("Te estoy atendiendo en hora buena , dime tu petición");
            while ((inputLine = in.readLine()) != null) {
                 String[] comando=inputLine.split("\\s+");
                      String cmd = comando[0].toLowerCase();
                switch (cmd) {
                    case "get":
                         String clave = comando[1];
                         outputLine=(String) base.Base.get(clave);
                        //outputLine = "Bueno aun no me han implementado el comando 'get key' , espero lo hagan pronto disculpa";
                        out.println(outputLine);
                        break;
                    case "set":
                        outputLine = "Bueno aun no me han implementado el comando 'set key value' , espero lo hagan pronto disculpa";
                        out.println(outputLine);
                        break;
                    case "del":
                        outputLine = "Bueno aun no me han implementado el comando 'del key' , espero lo hagan pronto disculpa";
                        out.println(outputLine);
                        break;
                    case "list":
                        ArrayList lista;
                        lista = new ArrayList<String>(base.Base.keySet());
                        outputLine = "";
                        for(i=0; i< lista.size(); i++){
                            claveMap=(String)lista.get(i);
                            outputLine=outputLine + "\n"+ claveMap;
                            
                        }
                        //System.out.println(outputLine);
                        out.println("lista"+" "+lista);
                        //out.println(outputLine);
                        break;
                    case "exit":
                        outputLine = "Bye.";
                        out.println(outputLine);
                        break;

                    default:
                        outputLine = "Comando no válido, puede ver los comandos disponibles con el comando 'help'";
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
