import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	
    Lock tempLock = new ReentrantLock();
   
    private final int id;

    public Chopstick(int id) {
      this.id = id;
    }

    public boolean pickUp(Philosopher who, String where) throws InterruptedException {
      if (tempLock.tryLock(10, TimeUnit.MILLISECONDS)) {
        System.out.println(who + " picked up " + where + " " + this);
        return true;
      }
      return false;
    }

    public void putDown(Philosopher who, String name) {
    	tempLock.unlock();
      System.out.println(who + " put down " + name + " " + this);
    }

    @Override
    public String toString() {
      return "Chopstick-" + id;
    }
}
