package model.subscribers;

import model.entities.ContainerEntity;

/**
 * Created on 20.11.2016.
 */
public class ContainerSubscriber extends AbstractSubscriber {

	public ContainerSubscriber() {
		unloadType = ContainerEntity.UNLOAD_TYPE;
	}

	@Override
	public String getName() {
		return "Container crane " + super.getName();
	}
}
