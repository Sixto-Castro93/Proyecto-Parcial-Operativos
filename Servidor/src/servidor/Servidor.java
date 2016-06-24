package servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
    public Servidor() throws IOException{super("servidor");} //Se usa el constructor para servidor de Conexion

    public void startServer()//Método para iniciar el servidor
    {
        try
        {
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());
            salidaCliente.writeUTF("Petición recibida y aceptada");
            
            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            
            while((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
            {
                System.out.println(mensajeServidor);
            }
            
            System.out.println("Fin de la conexión");
            
            ss.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}