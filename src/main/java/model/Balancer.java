package model;


import model.entities.Entity;
import model.factories.ContainerFactory;
import model.factories.DryFactory;
import model.factories.LiquidFactory;
import model.subscribers.AbstractSubscriber;
import model.subscribers.Subscriber;

import java.util.*;

/**
 * Created on 20.11.2016.
 */
public class Balancer {

	public LinkedList<Entity> inQueue = new LinkedList<Entity>();
	public LinkedList<Entity> outQueue = new LinkedList<Entity>();
		public List<Subscriber> subscriberList = new ArrayList<Subscriber>();
	private Statistics stat = new Statistics();

	public void addSub(Subscriber sub) {
		subscriberList.add(sub);
	}

	private void fireSub(List<Entity> entityList) {
		for (Subscriber sub : subscriberList) {

			sub.onNewDay(entityList);
		}
	}

	private void setSub(List<Entity> entityList) {
		for (Subscriber sub : subscriberList) {

			if (((AbstractSubscriber) sub).isReady()) for (int i = 0; i < inQueue.size(); i++) {

				((AbstractSubscriber) sub).set(inQueue.get(i));
				if (!((AbstractSubscriber) sub).isReady()) inQueue.remove(i);
			}
		}
	}

	public void process() {
		createNewShips();
		EventProvider.onNewDay(inQueue);

		balance();
		setSub(inQueue);

		fireSub(outQueue);
	}

	private void balance() {
		inQueue.sort(new Comparator<Entity>() {
			public int compare(Entity o1, Entity o2) {
				return priority(o1) - priority(o2);
			}
		});

	}

	private int priority(Entity entity) {
		return (entity.getValue() + entity.getType()) / (int) (entity.getDate().getTime() - EventProvider.getCurrentDate().getTime());
	}
	private void createNewShips() {
		Random random = new Random();
		switch (random.nextInt(9)) {
			case 1:
				inQueue.add(ContainerFactory.getInstance().createEntity());
				break;
			case 2:
				inQueue.add(DryFactory.getInstance().createEntity());
				break;
			case 3:
				inQueue.add(LiquidFactory.getInstance().createEntity());
				break;
			default:
				break;
		}
	}

	public Balancer() {

		subscriberList.add(ContainerFactory.getInstance().createSubscriber());
		subscriberList.add(DryFactory.getInstance().createSubscriber());
		subscriberList.add(LiquidFactory.getInstance().createSubscriber());

		EventProvider.addSub(stat);
	}

	public Statistics getStat() {
		return stat;
	}
}
