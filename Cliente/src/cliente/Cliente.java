package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

public class Cliente extends Conexion {

    String[] comandos = {"get", "set", "del", "list", "exit"};

    public Cliente() throws IOException {
        super("cliente");
    } //Se usa el constructor para cliente de Conexion

    PrintWriter out = null;
    BufferedReader in = null;
    boolean verificaConexion = false;

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
                if (fromServer.startsWith("lista")) {
                    String[] lista;
                    String last, outputLine;
                    String arreglo;
                    arreglo = fromServer.substring(6);
                    arreglo = arreglo.replace("[", " ");
                    arreglo = arreglo.replace("]", " ");
                    lista = arreglo.split(",");
                    //last = in.readLine().split("[")[arreglo-1];
                    outputLine = "";
                    for (int i = 0; i < lista.length; i++) {
                        outputLine = outputLine + "\n" + lista[i];

                    }
                    //System.out.println(arreglo);
                    //System.out.println(outputLine);
                    fromServer = outputLine;

                }

                System.out.println("Server: " + fromServer);

                boolean validez = true;
                do {
                    try {
                       new Socket(Conexion.HOST, Conexion.PUERTO).close();
                       verificaConexion = true;
                    } catch(Exception e) {
                        verificaConexion = false;
                    }
                    if(verificaConexion==false){
                        cs.close();
                        System.exit(0);
                        break;
                    
                    }
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
                        String[] comando =validarComandos(fromUser);
                        if (comando!=null) {
                            validez=true;
                            if (!fromUser.toLowerCase().equals("exit") && !fromUser.toLowerCase().equals("help")) {
                                System.out.println(Arrays.toString(comando));
                                out.println(Arrays.toString(comando));
                                
                            }
                            if (fromUser.toLowerCase().equals("help")) {
                                validez = false;
                            }
                        }else
                            validez = false;
                    }
                } while (!validez);
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

    private String[] validarComandos(String s) {
        String[]comando=s.split("\\s");
        String cmd = comando[0].toLowerCase();
        switch (cmd) {
            case "get":
                if (comando.length == 2) {
                    return comando;
                } else {
                    System.out.println("Error: El comando get recibe un parámetro de entrada");
                    return null;
                }
            case "set":
                if (comando.length >= 3) {
                    comando = extraerDeComando(s);
                    if (comando != null) {
                        return comando;
                    } else {
                        System.out.println("Error: El comando set no cumple con la estructura: set <key> \"<value>\"");
                        return null;
                    }
                } else {
                    System.out.println("Error: El comando set recibe dos parámetros de entrada");
                    return null;
                }

            case "put":
                if (comando.length >= 3) {
                    comando = extraerDeComando(s);
                    if (comando != null) {
                        return comando;
                    } else {
                        System.out.println("Error: El comando put no cumple con la estructura: put <key> \"<value>\"");
                        return null;
                    }
                } else {
                    System.out.println("Error: El comando put recibe dos parámetros de entrada");
                    return null;
                }
            case "del":
                if (comando.length == 2) {
                    return comando;
                } else {
                    System.out.println("Error: El comando del recibe solo un parámetro de entrada");
                    return null;
                }
            case "list":
                if (comando.length == 1) {
                    return comando;
                } else {
                    System.out.println("Error: El comando list no recibe parámetros de entrada");
                    return null;
                }
            case "exit":
                if (comando.length == 1) {
                    return comando;
                } else {
                    System.out.println("Error: El comando exit no recibe parámetros de entrada");
                    return null;
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
                    return comando;
                } else {
                    System.out.println("Error: El comando help no recibe parámetros de entrada");
                    return null;
                }
            default:
                System.out.println("Error: El comando " + comando[0] + " no existe. Consulta los comandos disponibles con el comando 'help'");
                return null;
        }
    }

    private static String[] extraerDeComando(String s) {
        ArrayList<String> cmdFinal = new ArrayList<>();
        String[] comando = s.split("\\s+");
        String value = new String();
        if (comando.length >= 3) {
            cmdFinal.add(comando[0]);
            cmdFinal.add(comando[1]);
            int cont = 0;
            int y = 0;
            while (cont < 2) {
                char c = s.charAt(y);
                if (c == ' ') {
                    cont++;
                }
                while (c == ' ') {
                    y++;
                    c = s.charAt(y);
                }
                if (c != ' ') {
                    y++;
                }
            }
            if (s.charAt(y) != '\"' && s.charAt(s.length() - 1) != '\"') {
                return null;
            } else {
                for (; y < s.length() - 1; y++) {
                    value += s.charAt(y);
                }
            }
        } else {
            return null;
        }
        String[] cmd = new String[]{cmdFinal.get(0), cmdFinal.get(1), value};
        return cmd;
    }
}
