import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Solution {
	 public static void main(String args[]) throws InterruptedException {
		 
		 final int NO_OF_PHILOSOPHER = 5;
		 final int SIMULATION_MILLIS = 1000 * 10;
		 ExecutorService executorService = null;

		    Philosopher[] philosophers = null;
		    try {
		      philosophers = new Philosopher[NO_OF_PHILOSOPHER];

		      //As many forks as Philosophers
		      Chopstick[] chopSticks = new Chopstick[NO_OF_PHILOSOPHER];
		      for (int i = 0; i < NO_OF_PHILOSOPHER; i++) {
		        chopSticks[i] = new Chopstick(i);
		      }

		      executorService = Executors.newFixedThreadPool(NO_OF_PHILOSOPHER);

		      for (int i = 0; i < NO_OF_PHILOSOPHER; i++) {
		        philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i + 1) % NO_OF_PHILOSOPHER]);
		        executorService.execute(philosophers[i]);
		      }
		      Thread.sleep(SIMULATION_MILLIS);
		      // Stop all philosophers.
		      for (Philosopher philosopher : philosophers) {
		        philosopher.isTummyFull = true;
		      }

		    } finally {
		      executorService.shutdown();
		      while (!executorService.isTerminated()) {
		        Thread.sleep(1000);
		      }
		  }
	 }
}
