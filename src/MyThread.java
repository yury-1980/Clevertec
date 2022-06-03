import java.util.concurrent.BrokenBarrierException;

public class MyThread extends Thread {

    public static int trackLengthAll;
    private int trackLength = trackLengthAll;

    @Override
    public void run() {
        try {
            Main.BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        while (trackLength > 0) {
            if (Main.stop) {
                System.out.println("До конца " + getName() + " осталось - " + trackLength);
                return;
            }
            System.out.println(getName() + " осталось - " + trackLength);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            trackLength--;
            if (trackLength == 0) {
                Main.stopped(true);
                System.out.print("Я первый!!! - " + getName() + " " + trackLength + "  ");
            }
        }
        System.out.println("финишировал " + getName() + " осталось - " + trackLength);
    }
}
