package model.subscribers;

import model.entities.Entity;

import java.util.List;

/**
 * Created on 20.11.2016.
 */
public interface Subscriber {
	void onNewDay(List<Entity> entityList);
}
