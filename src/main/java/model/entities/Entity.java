package model.entities;

import java.sql.Date;


/**
 * Created on 20.11.2016.
 */
public interface Entity {
    String getName();

    int getValue();//вес груза

    Date getDate();//дата отбытия

    int getColor();

    String getModel();

    int getType();
}
