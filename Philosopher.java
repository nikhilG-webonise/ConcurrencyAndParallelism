import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Philosopher implements Runnable {
    private final int id;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;
   
    volatile boolean isTummyFull = false;
    
    private Random randomGenerator = new Random();
    
    public Philosopher(int id, Chopstick leftChopStick, Chopstick rightChopStick) {
      this.id = id;
      this.leftChopstick = leftChopStick;
      this.rightChopstick = rightChopStick;
    }

    @Override
    public void run() {

      try {
        while (!isTummyFull) {
          think();
          if (leftChopstick.pickUp(this, "left")) {
            if (rightChopstick.pickUp(this, "right")) {
              // Eating
              eat();
              // Finished.
              rightChopstick.putDown(this, "right");
            }
            // Finished.
            leftChopstick.putDown(this, "left");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private void think() throws InterruptedException {
      System.out.println(this + " is thinking");
      Thread.sleep(randomGenerator.nextInt(1000));
    }

    private void eat() throws InterruptedException {
      System.out.println(this + " is eating");
      Thread.sleep(randomGenerator.nextInt(1000));
    }

    @Override
    public String toString() {
      return "Philosopher-" + id;
    }
}