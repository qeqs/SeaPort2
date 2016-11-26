package model.factories;

import model.EventProvider;
import model.entities.LiquidEntity;
import model.subscribers.LiquidSubscriber;

import java.sql.Date;
import java.util.Random;

/**
 * Created on 20.11.2016.
 */
public class LiquidFactory implements Factory {
	int i = 0;
	Random random;
	private static LiquidFactory instance;

	public static LiquidFactory getInstance() {
		if (instance == null) instance = new LiquidFactory();
		return instance;
	}

	private LiquidFactory() {

	}
	public LiquidEntity createEntity() {
		random = new Random();
		return new LiquidEntity("Liquid" + String.valueOf(i++), random.nextInt(6) + 1, EventProvider.getDate());
	}

	public LiquidEntity createEntity(String name, int weight, Date departure) {
		return new LiquidEntity(name, weight, departure);
	}

	public LiquidSubscriber createSubscriber() {
		return new LiquidSubscriber();
	}
}
