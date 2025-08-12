package myAPI;

public class MyRun implements Runnable {

    int count = 0;

    @Override
    public void run() {
        while (true) {
            if (method()) break;
        }
    }

    private synchronized boolean method() {
        if (count == 5) {
            return true;
        }else {
            count++;
            System.out.println(Thread.currentThread().getName() +"正在卖出"+ count);
        }
        return false;
    }
}
