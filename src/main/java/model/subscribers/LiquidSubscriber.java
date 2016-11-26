package model.subscribers;

import model.entities.LiquidEntity;

/**
 * Created on 20.11.2016.
 */
public class LiquidSubscriber extends AbstractSubscriber {

	public LiquidSubscriber() {
		unloadType = LiquidEntity.UNLOAD_TYPE;
	}

	@Override
	public String getName() {
		return "Liquid crane " + super.getName();
	}
}
