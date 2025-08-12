package myAPI;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Desk {
    public static int count = 10;
    public static Object lock = new Object();
    public static int flag = 0;
}
