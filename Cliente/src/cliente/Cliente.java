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
                        case "set":
                            System.out.println("Petición valida");
                            out.println(fromUser);
                            validez=false;
                            break;
                        case "exit":
                            System.out.println("Petición valida");
                            out.println(fromUser);
                            validez=false;
                            break;
                        default:
                            System.out.println("Esta peticion no es valida, porfavor ingresela de nuevo");
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
