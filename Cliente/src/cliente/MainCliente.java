package cliente;

import java.io.IOException;



//Clase principal que hará uso del cliente
public class MainCliente
{
    public static void main(String[] args) throws IOException
    {  
        Cliente cli = new Cliente(); 
        
        System.out.println("Iniciando cliente\n");
        cli.startClient(args[0],args[1]); //Se inicia el cliente
    }
}