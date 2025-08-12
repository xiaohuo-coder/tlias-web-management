package myAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

public class MyCall implements Callable<Integer> {
    ArrayList<Integer> list;
    // 锁对象
    static Object lock = new Object();

    public MyCall() {}

    public MyCall(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        ArrayList<Integer> boxList = new ArrayList();
        int max = 0;
        while (true) {
            synchronized (lock) {
                //判断set是否为null
                if (list.size() == 0) {
                    // 已被抽完
                    int count = 0;
                    int sum = 0;
                    for (Integer i : boxList) {
                        sum += i;
                        count++;
                        if (i > max) {
                            max = i;
                        }
                    }
                    Collections.shuffle(boxList);
                    System.out.println(Thread.currentThread().getName() + "产生了" + count + "个奖项，分别为" + boxList + "，最高奖项为" + max + "元，共计" + sum + "元");
                    break;
                }
                // 随机抽取一个奖
                int randomIndex = (int) (Math.random() * list.size());
                Object[] array = list.toArray();
                Object randomElement = array[randomIndex];
                boxList.add((Integer) randomElement);
                list.remove(randomElement);
            }
            Thread.sleep(1000);
        }
        return max;
    }
}
