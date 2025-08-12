package extendsDemo;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {
        Manager m = new Manager(1, "张三", 20000, 10000);
        System.out.println(m.getId() +" ,"+ m.getName()+" ,"
                + m.getSalary()+" ,"+ m.getBonus());
        m.work();
        m.eat();

        Cook c = new Cook();
        c.setId(2);
        c.setName("李四");
        c.setSalary(18000);
        System.out.println(c.getId() +","+ c.getName()+"," + c.getSalary());

        c.work();
        c.eat();
    }
}
