package model;

import model.entities.Entity;
import model.subscribers.Subscriber;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventProvider {
	private static Weather weather = Weather.clearSky;
	private static Date currentDate = new Date(System.currentTimeMillis());
	private static List<Subscriber> subscriberList = new ArrayList<Subscriber>();
	private static int totalDays = 0;

	public static void addSub(Subscriber sub) {
		subscriberList.add(sub);
	}

	private static void fireSub(List<Entity> entityList) {
		for (Subscriber sub : subscriberList) {

			sub.onNewDay(entityList);
		}
	}

	public static Date getDate() {
		Random random = new Random();
		return new Date(currentDate.getTime() + random.nextInt(1000 * 24 * 3600 * 7) + 1000 * 24 * 3600 * 7*2);
	}

	private static Weather getWeather() {
		Random random = new Random();
		return (random.nextInt() % 2) == 0 ? Weather.clearSky : Weather.storm;
	}

	public static Weather getCurrentWeather() {
		return weather;
	}

	public static Date getCurrentDate() {
		return currentDate;
	}

	public static void onNewDay(List<Entity> entityList) {
		totalDays++;
		weather = getWeather();
		currentDate.setTime(currentDate.getTime() + 1000 * 24 * 3600);
		fireSub(entityList);
	}

	public static int getTotalDays() {
		return totalDays;
	}
}
