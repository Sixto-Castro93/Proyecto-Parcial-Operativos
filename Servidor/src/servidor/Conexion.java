/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author SixtoJavier
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion
{
    protected int PUERTO = 1234; //Puerto para la conexión
    protected String HOST = "localhost"; //Host para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos de salida
    
    public Conexion(String tipo) throws IOException //Constructor
    {
        if(tipo.equalsIgnoreCase("servidor"))
        {
              try {
                   ss = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            cs = new Socket(); //Socket para el cliente
            } catch (Exception e) {
                System.out.println("Este servidor ya esta disponible por el momento");
            }
        
        }
        else
        {
            try {
                  cs = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
            } catch (Exception e) {
                System.out.println("Este servidor no esta disponible por el momento, porfavor intenta mas tarde");
            }
        }
    }
}
    
