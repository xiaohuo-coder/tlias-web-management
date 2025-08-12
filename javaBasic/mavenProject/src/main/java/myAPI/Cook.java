package myAPI;

import java.util.concurrent.ArrayBlockingQueue;

public class Cook extends Thread {

    ArrayBlockingQueue<String> queue;

    public Cook(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //上一道菜吃一道，总共能吃10次
        while (true) {
            //如果有菜，等待。如果没菜，做菜，改桌子状态。
            try {
                queue.put("汉堡");
                System.out.println("厨师做好了一份汉堡");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
