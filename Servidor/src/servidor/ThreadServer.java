/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadServer extends Thread {

    private Socket socket = null;
    public static PrintWriter salida;
    private BaseNoSql base = null;

    public ThreadServer(Socket socket, BaseNoSql base) {
        super("ThreadServer");
        this.socket = socket;
        this.base = base;
    }

    public void leerArchivo2(BaseNoSql base, String nombreArchivo, PrintWriter out) {

        Iterator it = base.Base.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            if (mentry.getKey().toString().startsWith(nombreArchivo) == true) {
                System.out.print("key: " + mentry.getKey() + " & Value: ");
                System.out.println(mentry.getValue());
                out.println("Linea " + mentry.getValue().toString());
            }

        }
        out.println("Fin archivo");

    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente en linea");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            salida = out;
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String inputLine, outputLine;
            int i = 0;
            String claveMap;

            out.println("Te estoy atendiendo en hora buena, dime tu petición. Si desea consulte los comandos disponibles con 'help'");
            while ((inputLine = in.readLine()) != null) {
                inputLine = inputLine.substring(1, inputLine.length() - 1);
                //   System.out.println(inputLine);
                String[] comando = inputLine.split(", ");
                //   System.out.println(Arrays.toString(comando));
                String cmd = comando[0].toLowerCase();
                switch (cmd) {
                    case "get":
                        String clave = comando[1];
                        outputLine = base.getvalor(clave);
                        if (outputLine.endsWith(".txt")) {
                            leerArchivo2(base, clave, out);
                        } else {
                            System.out.println(outputLine);
                            out.println(outputLine);
                        }

                        //leerArchivo2(base, clave, out);
                        /*if(outputLine.endsWith(".txt")){
                         base.guardarArchivo(clave, outputLine.toString());
                         }else{*/
                        //out.println(outputLine);
                        //}
                        break;
                    case "put":
                        if (comando[2].endsWith(".txt")) {
                            outputLine = base.putvalor2(comando[1], comando[2]);
                            out.println(outputLine);
                        } else {
                            outputLine = base.putvalor(comando[1], comando[2]);
                            out.println(outputLine);
                        }
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
                        outputLine = "ERROR: Comando no valido, puede ver los comandos disponibles con el comando 'help'";
                        out.println(outputLine);
                        break;
                }
            }
            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
