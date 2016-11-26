package model.entities;

import net.slashie.libjcsi.ConsoleSystemInterface;

import java.sql.Date;

/**
 * Created on 20.11.2016.
 */
public class LiquidEntity extends AbstractEntity {
    public static final int UNLOAD_TYPE = 3;
    public LiquidEntity(String name, int weight, Date departure) {
        super(name, weight, departure);

        model = "<OOO>";
        color = ConsoleSystemInterface.DARK_BLUE;
    }

    public int getType() {
        return UNLOAD_TYPE;
    }
}
