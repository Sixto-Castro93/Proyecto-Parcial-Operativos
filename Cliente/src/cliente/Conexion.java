package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion
{
    public static int PUERTO ; //Puerto para la conexión
    public static String HOST = ""; //Host para la conexiónprotected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos de salida
    
    public Conexion(String tipo,String h,String p) throws IOException //Constructor
    {
        HOST=h;
        PUERTO=Integer.parseInt(p);
        if(tipo.equalsIgnoreCase("servidor"))
        {
            ss = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            cs = new Socket(); //Socket para el cliente
        }
        else
        {
            try {
                  cs = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
            } catch (Exception e) {
                System.out.println("Este servidor no esta disponible por el momento, por favor intenta mas tarde"+h+p);
            }
          
        }
    }
}