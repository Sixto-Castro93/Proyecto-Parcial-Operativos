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
                System.out.println(inputLine);
                inputLine = inputLine.substring(1, inputLine.length()-1);
                 String[] comando=inputLine.split(", ");
                 System.out.println(Arrays.toString(comando));
                      String cmd = comando[0].toLowerCase();
                switch (cmd) {
                    case "get":
                        String clave = comando[1];
                        outputLine=get(clave);
                        //outputLine = "Bueno aun no me han implementado el comando 'get key' , espero lo hagan pronto disculpa";
                        out.println(outputLine);
                        break;
                    case "put":
                        outputLine = put(comando[1],comando[2]);
                        out.println(outputLine);
                        break;
                    case "set":
                        outputLine = set(comando[1],comando[2]);
                        out.println(outputLine);
                        break;
                    case "del":
                        outputLine = del(comando[1]);
                        out.println(outputLine);
                        break;
                    case "list":
                        ArrayList lista;
                        lista = list();
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
    private String get(String key){
        Object value = base.Base.get(key);
        if(value==null)
            return key+" no existe";
        else 
            return (String)value;
    }
    private ArrayList list(){
        ArrayList lista;
        lista = new ArrayList<String>(base.Base.keySet());
        return lista;
    }
    private String del(String key){
        Object value = base.Base.get(key);
        if(value==null)
            return key+" no existe";
        else{
            base.Base.remove(key);
            return key+" se eliminó de la lista";
        }    
    }
    private String set(String key,String newValue){
        Object value = base.Base.get(key);
        if(value==null)
            return key+" no existe";
        else{
            base.Base.put(key,newValue);
            return "Se cambió el valor de "+key;
        }  
    }
    private String put(String key,String value){
        base.Base.put(key,value);
        return "Se agregó el nuevo objeto: "+key+", a la lista";
    }
}
