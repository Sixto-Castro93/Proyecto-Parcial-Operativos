/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author emele_000
 */
public class BaseNoSql {

    HashMap Base = new HashMap();
    boolean lectura = false;
    boolean escritura = false;

    public BaseNoSql() {
    }

    public HashMap getBase() {
        return Base;
    }

    public void setBase(HashMap Base) {
        this.Base = Base;
    }

    public void iniciarBase() {
        /*Base.put("1", "1 elemento");
         Base.put("2", "2 elemento");
         Base.put("3", "3 elemento");
         Base.put("4", "4 elemento");*/
        Base.put("0991216662", "Nombre: Sixto Castro Redroban  Edad: 22 anios  Nacionalidad: Ecuatoriano");
        Base.put("0987228938", "Nombre: Marlon Espinoza Pacheco  Edad: 22 anios  Nacionalidad: Ecuatoriano");
        Base.put("0988199820", "Nombre: Kevin Zambrano Cortez  Edad: 22 anios  Nacionalidad: Ecuatoriano");
        Base.put("0981423688", "Nombre: Jordy Vasquez Cepeda  Edad: 22 anios  Nacionalidad: Ecuatoriano");
    }

    public void iniciarBase2() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(".\\src\\servidor\\prueba.txt"));

            String line;
            String[] values;

            while (((line = in.readLine()) != null)) {
                values = line.split("\\t");
                String clave = values[0];
                String valor = values[1];
                //System.out.println(clave + " "+ valor);
                Base.put(clave, valor);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getvalor(String key) throws InterruptedException {
        while (escritura) {
            wait();
        }
        lectura = true;
        Object value = this.Base.get(key);
        lectura = false;
        notify();
        if (value == null) {
            return "Key:";
        } else {
            return "Key:" + (String) value;
        }
    }

    public synchronized ArrayList listvalor() throws InterruptedException {
        ArrayList lista;
        while (escritura) {
            wait();
        }
        lectura = true;
        lista = new ArrayList<String>(this.Base.keySet());
        lectura = false;
        return lista;
    }

    public synchronized String delvalor(String key) throws InterruptedException {
        while (escritura || lectura) {
            wait();
        }
        lectura = true;
        Object value = this.Base.get(key);
        lectura = false;
        if (value == null) {
            return "ERROR: Este key no existe ,consultelo con el comando list";
        } else {
            escritura = true;
            this.Base.remove(key);
            escritura = false;
            return "Registro eliminado con clave: " + key;
        }
    }

    public synchronized String setvalor(String key, String newValue) throws InterruptedException {

        while (lectura) {
            wait();
        }
        escritura = true;
        Object value = this.Base.get(key);
        if (value == null) {
             escritura = false;
              notify();
            return "ERROR:" + key + " no existe";
        } else {
            this.Base.put(key, newValue);
            escritura = false;
            notify();
            return "Se cambio el valor de " + key;
        }
    }

    public synchronized String putvalor(String key, String value) throws InterruptedException {
        while (lectura) {
            wait();
        }
        escritura = true;
        this.Base.put(key, value);
        escritura = false;
        notify();
        return "Se agrego el nuevo objeto: " + key + ", a la lista";
    }
}
