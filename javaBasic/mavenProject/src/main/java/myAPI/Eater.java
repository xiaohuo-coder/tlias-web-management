package myAPI;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Eater extends Thread {
    ArrayBlockingQueue<String> queue;

    public Eater(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                String food = queue.take();
                System.out.println("顾客拿到了一份"+food);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
