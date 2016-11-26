import model.Balancer;
import model.EventProvider;
import model.Statistics;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import org.junit.Test;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on 23.11.2016.
 */
public class testBalancer {
	Balancer balancer;
	ConsoleSystemInterface csi = new WSwingConsoleInterface("console", true);

	@Test
	public void test() throws IOException {
		//csi.setAutoRefresh(true);
		balancer = new Balancer();
		Timer timer = new Timer();
		for (int i = 0; i < 1; i++)
			timer.schedule(task, i * 1000, i * 1000 + 1000);
		csi.waitKey(0);

	}

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			balancer.process();
			Statistics stat = balancer.getStat();
			csi.cls();
			csi.print(0, 0, EventProvider.getCurrentDate().toString());
			csi.print(0, 1, EventProvider.getCurrentWeather().toString() + " on " + EventProvider.getTotalDays() + " day");
			csi.print(0, 2, "Statistics: penalty(" + stat.getPenalty() + "), avarageUnload(" + stat.getAvarageUnLoadTime() + "), goodDays(" + stat.getPercentGoodWeatherDays() + "%)");
			for (int i = 0; i < balancer.inQueue.size(); i++) {
				csi.print(0, 3 + i, "inQueue: " + balancer.inQueue.get(i).toString());
			}
			csi.print(0, 4 + balancer.inQueue.size(), "outQueue: " + balancer.outQueue.toString());
			for (int i = 0; i < 3; i++) {
				csi.print(0, 5 + balancer.inQueue.size() + i, balancer.subscriberList.get(i).toString());
			}
			csi.refresh();
		}
	};

}
