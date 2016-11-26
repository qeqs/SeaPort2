package model.entities;

import java.sql.Date;

/**
 * Created on 21.11.2016.
 */
abstract class AbstractEntity implements Entity {
    private final String name;
    private final int weight;
    private final Date departure;
    protected String model = "?";
    protected int color = 0;

    public AbstractEntity(String name, int weight, Date departure) {

        this.name = name;
        this.weight = weight;
        this.departure = departure;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return weight;
    }

    public Date getDate() {
        return departure;
    }

    public int getColor() {
        return color;
    }

    public  String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return getName();
    }
}
