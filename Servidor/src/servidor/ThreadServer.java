/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.util.Iterator;

/**
 *
 * @author emele_000
 */
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
            out.println("Te estoy atendiendo en hora buena , dime tu petición");
            while ((inputLine = in.readLine()) != null) {
                String[] comando = inputLine.split("\\s");
                String cmd = comando[0].toLowerCase();
                switch (cmd) {
                    case "get":
                        String clave = comando[1];
                        outputLine = (String) base.Base.get(clave);
                        if (outputLine != null) {
                           outputLine="Key:" + outputLine;
                        } else {
                            outputLine = "Key=";
                        }
                        out.println(outputLine);
                        break;
                    case "set":
                        Object tem = base.Base.put(comando[1], comando[2]);
                        if (tem != null) {
                            outputLine = "EROR: Ha ocurido un error al insertar su registro";
                        } else {

                            outputLine = "Ok";
                        }
                        out.println(outputLine);
                        break;
                    case "del":
                        Object tem2 = base.Base.remove(comando[1]);
                        if (tem2 == null) {
                            outputLine = "ERROR: Probablemente este key no exista ,consultelo con el comando list";
                        } else {

                            outputLine = "Registro eliminado con clave: "+comando[1];
                        }
                        out.println(outputLine);
                        break;
                    case "list":
                        outputLine="Lista de claves: ;";
                        Iterator it = base.Base.keySet().iterator();
                        while (it.hasNext()) {
                            String key = (String)it.next();
                            outputLine=outputLine+"        Clave: " + key +" ;";
                        }
                        out.println(outputLine);
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
