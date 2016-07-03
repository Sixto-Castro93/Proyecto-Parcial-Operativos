package servidor;

import java.net.*;
import java.io.IOException;
import java.util.HashMap;


public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{

    public Servidor() throws IOException {
        super("servidor");
    } //Se usa el constructor para servidor de Conexion

    public void startServer()//Método para iniciar el servidor
    {
        try {
            System.out.println("Esperando..."); //Esperando conexión
            BaseNoSql Base=new BaseNoSql();
            Base.iniciarBase();
            while (true) {
                Socket incoming = ss.accept();

                Thread hilo = new ThreadServer(incoming,Base);
                hilo.start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
