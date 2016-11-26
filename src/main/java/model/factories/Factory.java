package model.factories;

import model.entities.Entity;
import model.subscribers.Subscriber;

import java.sql.Date;

/**
 * Created on 20.11.2016.
 */
public interface Factory {

	Entity createEntity();

	Entity createEntity(String name, int weight, Date departure);

	Subscriber createSubscriber();
	//Subscriber createSubscriber(params)
}
