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
import java.io.IOException;



//Clase principal que hará uso del servidor
public class MainServidor
{
    public static void main(String[] args) throws IOException
    {        
        Servidor serv = new Servidor(args[0]); //Se crea el servidor
        
        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
    }
}
