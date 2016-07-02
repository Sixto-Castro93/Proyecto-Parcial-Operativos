/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.HashMap;

/**
 *
 * @author emele_000
 */
public class BaseNoSql {

    HashMap Base = new HashMap();

    public BaseNoSql() {
    }

    public HashMap getBase() {
        return Base;
    }

    public void setBase(HashMap Base) {
        this.Base = Base;
    }

    public void iniciarBase() {
        Base.put(1, "1 elemento");
        Base.put(2, "2 elemento");
        Base.put(3, "3 elemento");
        Base.put(4, "4 elemento");
    }
}
