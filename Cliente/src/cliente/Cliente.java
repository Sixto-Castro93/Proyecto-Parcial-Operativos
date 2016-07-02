package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;

public class Cliente extends Conexion {

    public Cliente() throws IOException {
        super("cliente");
    } //Se usa el constructor para cliente de Conexion

    PrintWriter out = null;
    BufferedReader in = null;

    public void startClient() //Método para iniciar el cliente
    {
        try {
            out = new PrintWriter(cs.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            //Flujo de datos hacia el servidor

            //Se enviarán dos mensajes
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }
                boolean validez=true;
                while (validez){
                System.out.print("Client: ");
                fromUser = stdIn.readLine();
                if (fromUser != null) {

                    switch (fromUser) {
                        case "get key":
                            System.out.println("Petición válida");
                            out.println(fromUser);
                            validez=false;
                            break;
                        case "set key value":
                            System.out.println("Petición válida");
                            out.println(fromUser);
                            validez=false;
                            break;
                        case "del key":
                            System.out.println("Petición válida");
                            out.println(fromUser);
                            validez=false;
                            break;
                        case "list":
                            System.out.println("Petición válida");
                            out.println(fromUser);
                            validez=false;
                            break;
                        case "exit":
                            System.out.println("Petición válida");
                            out.println(fromUser);
                            validez=false;
                            break;
                        case "help":
                            System.out.println("Lista de comandos disponibles: \n"
                                    + "get key: Operación get. Retorna el valor asociado a dicha clave.\n" 
                                    + "set key value: Almacena (en memoria) la clave, con el valor asociado. " +
                                    "El valor puede contener cualquier caracter, incluso caracteres especiales,tabs, y espacios en blanco.\n" 
                                    + "del key: Elimina la clave, con su valor asociado.\n" 
                                    + "list: Retorna la lista de todas las claves almacenadas. "+
                                    "NO retorna los valores asociados a dichas claves.\n"
                                    + "exit: Termina la conexión con el servidor y posteriormente, termina ejecución del programa cliente.\n"
                                    );
                            out.println(fromUser);
                            validez=false;
                            break;
                        default:
                            System.out.println("Esta petición no es válida, por favor ingrese un comando válido o consulte los comandos disponibles con el comando 'help'");
                            break;

                    }

                }
                }
            }
            out.close();
            in.close();
            cs.close();//Fin de la conexión

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
