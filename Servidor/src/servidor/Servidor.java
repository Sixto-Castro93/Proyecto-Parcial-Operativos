package servidor;

import java.net.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
  private static ContenedorPeticiones contenedor;
    private static BaseNoSql Base=new BaseNoSql();
    public Servidor() throws IOException {
        super("servidor");
    } //Se usa el constructor para servidor de Conexion

    public void startServer()//Método para iniciar el servidor
    {
        try {
            System.out.println("Esperando..."); //Esperando conexión
          Base=new BaseNoSql();
            Base.iniciarBase();
            contenedor = new ContenedorPeticiones();
             ExecutorService producers = Executors.newFixedThreadPool(5);
          for (int i = 0; i < 5; i++) {
               producers.execute(new  ThreadProducer (ss,contenedor,i));
          }
      
             ExecutorService consumers = Executors.newFixedThreadPool(5);
          for (int i = 0; i < 5; i++) {
               consumers.execute(new  ThreadConsumidor(Base,contenedor,i));
          }
          consumers.shutdown();
          producers.shutdown();
//            while (true) {
//                Socket incoming = ss.accept();
//
//                Thread hilo = new ThreadServer(incoming);
//                hilo.start();
//            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
