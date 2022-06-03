import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main {

    static int car = 1;
    static final CyclicBarrier BARRIER = new CyclicBarrier(car);
    static volatile boolean stop = false;

    static synchronized void stopped(boolean a) {
        stop = a;
    }

    public static void main(String[] args) throws InterruptedException {

        int cyrcle = 0;

        try (Scanner scanner = new Scanner(System.in);) {

            System.out.print("Enter the number of circles: ");
            cyrcle = scanner.nextInt();

            System.out.print("Enter the number of cars: ");
            car = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect value!");
        }

        MyThread.trackLengthAll = cyrcle;
        for (int i = 0; i < car; i++) {
            MyThread myThread = new MyThread();
            myThread.start();
        }
    }
}
