package myAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class MyThread extends Thread {
    ArrayList<Integer> list;
    // 锁对象
    static Object lock = new Object();
    static int maxAll = 0;
    static String maxName = "";

    public MyThread(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        ArrayList<Integer> list1 = new ArrayList();
        int max = 0;

        while (true) {
            synchronized (lock) {
                //判断set是否为null
                if (list.size() == 0) {
                    // 已被抽完
                    int count = 0;
                    int sum = 0;
                    for (Integer i : list1) {
                        sum += i;
                        count++;
                        if (i > max) {
                            max = i;
                        }
                    }
                    Collections.shuffle(list1);
                    if (max > maxAll) {
                        maxAll = max;
                        maxName = Thread.currentThread().getName();
                    }
                    System.out.println(Thread.currentThread().getName() + "产生了" + count + "个奖项，分别为" + list1 + "，最高奖项为" + max + "元，共计" + sum + "元");
                    break;
                }
                // 随机抽取一个奖
                int randomIndex = (int) (Math.random() * list.size());
                Object[] array = list.toArray();
                Object randomElement = array[randomIndex];
                list1.add((Integer) randomElement);
                list.remove(randomElement);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

