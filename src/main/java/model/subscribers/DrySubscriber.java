package model.subscribers;

import model.entities.DryEntity;

/**
 * Created on 20.11.2016.
 */
public class DrySubscriber extends AbstractSubscriber {

	public DrySubscriber() {
		unloadType = DryEntity.UNLOAD_TYPE;
	}

	@Override
	public String getName() {
		return "Dry crane " + super.getName();
	}
}
