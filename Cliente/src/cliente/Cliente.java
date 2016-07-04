package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.util.Calendar;

public class Cliente extends Conexion {

    String[] comandos = {"get", "set", "del", "list", "exit"};

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
            String fromUser = "";
            while ((fromServer = in.readLine()) != null) {
                if (fromUser.toLowerCase().equals("list")) {
                    fromServer = fromServer.replace(" ;", "\n");
                    System.out.println("Server: " + fromServer);
                } else {
                    System.out.println("Server: " + fromServer);
                }

                boolean validez = true;
                do {
                    System.out.print("Client: ");
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
                        String[] comando = fromUser.split(" ");
                        validez = validarComandos(comando);
                        if (validez) {
                            if (!fromUser.toLowerCase().equals("exit") && !fromUser.toLowerCase().equals("help"))
                            out.println(fromUser);
                        }
                    }
                } while ((!"help".equals(fromUser)) && (!"exit".equals(fromUser) && !validez));
                if (fromUser.toLowerCase().equals("exit")) {
                    break;
                }
            }
            out.close();
            in.close();
            cs.close();//Fin de la conexión
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private Boolean validarComandos(String[] comando) {
        String cmd = comando[0].toLowerCase();
        switch (cmd) {
            case "get":
                if (comando.length == 2) {
                    return true;
                } else {
                    System.out.println("Error: El comando get recibe un parámetro de entrada");
                    return false;
                }
            case "set":
                if (comando.length == 3) {
                    return true;
                } else {
                    System.out.println("Error: El comando set recibe dos parámetros de entrada");
                    return false;
                }
            case "del":
                if (comando.length == 2) {
                    return true;
                } else {
                    System.out.println("Error: El comando del recibe solo un parámetro de entrada");
                    return false;
                }
            case "list":
                if (comando.length == 1) {
                    return true;
                } else {
                    System.out.println("Error: El comando list no recibe parámetros de entrada");
                    return false;
                }
            case "exit":
                if (comando.length == 1) {
                    return true;
                } else {
                    System.out.println("Error: El comando exit no recibe parámetros de entrada");
                    return false;
                }
            case "help":
                if (comando.length == 1) {
                    System.out.println("Lista de comandos disponibles: \n"
                            + "get key: Operación get. Retorna el valor asociado a dicha clave.\n"
                            + "put key value: Almacena (en memoria) la clave, con el valor asociado. "
                            + "set key value: Edita (en memoria) el valor, con el valor asociado. "
                            + "El valor puede contener cualquier caracter, incluso caracteres especiales,tabs, y espacios en blanco.\n"
                            + "del key: Elimina la clave, con su valor asociado.\n"
                            + "list: Retorna la lista de todas las claves almacenadas. "
                            + "NO retorna los valores asociados a dichas claves.\n"
                            + "exit: Termina la conexión con el servidor y posteriormente, termina ejecución del programa cliente.\n"
                    );
                    return true;
                } else {
                    System.out.println("Error: El comando help no recibe parámetros de entrada");
                    return false;
                }
            default:
                System.out.println("Error: El comando " + comando[0] + " no existe");
                return false;
        }
    }
}
