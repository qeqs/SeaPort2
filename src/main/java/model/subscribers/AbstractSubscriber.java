package model.subscribers;

import model.EventProvider;
import model.Statistics;
import model.Weather;
import model.entities.Entity;

import java.util.List;

/**
 * Created on 22.11.2016.
 */
public abstract class AbstractSubscriber implements Subscriber {
	private static int ID = 0;
	private int id;
	protected Entity workingEntity;
	protected int currentProgress = 0;
	protected int weight;
	private int unloaded;
	protected int unloadType;
	protected int unloadsPerShip = 0;

	public void onNewDay(List<Entity> entityList) {
		unloadsPerShip++;

		if (weight == 0) {
			if (workingEntity != null) entityList.add(workingEntity);
			workingEntity = null;
			Statistics.setUnLoadTime(unloadsPerShip);
			unloadsPerShip = 0;
			return;
		}
		if (EventProvider.getCurrentWeather().equals(Weather.storm) ||// в плохую погоду нельзя разгружать
				unloaded++ < unloadType) {return;}//наполняем цистерны


		unloaded = 0;
		weight--;
		currentProgress = (int) (10 * (Double.valueOf(workingEntity.getValue() - weight) / Double.valueOf(workingEntity.getValue())));
	}

	public int getProgress() {
		return currentProgress;
	}

	public void set(Entity entity) {
		if (unloadType != entity.getType()) return;
		workingEntity = entity;
		weight = workingEntity.getValue();
	}

	public boolean isReady() {
		return workingEntity == null;
	}
	public String getName() {
		return "" + id;
	}

	public AbstractSubscriber() {
		ID++;
		id = ID;
	}

	@Override
	public String toString() {
		String entityInfo = "";
		if (workingEntity != null) {
			entityInfo = ": "+workingEntity.getName() + "[" + workingEntity.getDate().toString() + ", " + "w: " + workingEntity.getValue()+"]";
			for (int i = 0; i < getProgress(); i++) {
				entityInfo += "#";
			}
		}
		return getName() + entityInfo + "\n";
	}
}
