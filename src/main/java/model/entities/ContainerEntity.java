package model.entities;

import net.slashie.libjcsi.ConsoleSystemInterface;

import java.sql.Date;

/**
 * Created on 20.11.2016.
 */
public class ContainerEntity extends AbstractEntity {
    public static final int UNLOAD_TYPE = 1;
    public ContainerEntity(String name, int weight, Date departure) {
        super(name, weight, departure);
        model = "<-#->";//  <-getName()->
        color = ConsoleSystemInterface.RED;
    }

    public int getType() {
        return UNLOAD_TYPE;
    }
}
