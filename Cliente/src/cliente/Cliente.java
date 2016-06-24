package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente extends Conexion
{
    public Cliente() throws IOException{super("cliente");} //Se usa el constructor para cliente de Conexion

    public void startClient() //Método para iniciar el cliente
    {
        try
        {            
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            for (int i = 0; i < 2; i++)
            {
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Este es el mensaje número " + (i+1) + "\n");                
            }           

            

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        try{
            DataInputStream dis = new DataInputStream(cs.getInputStream());
            String msj = dis.readUTF();
            System.out.println(msj);
            
            cs.close();//Fin de la conexión
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            
        }
        
}
}