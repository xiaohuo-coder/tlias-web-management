package extendsDemo;

public class Cook extends Employee{
    public Cook() {
    }

    public Cook(int id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public void work() {
        System.out.println("厨师" + getName() + "正在炒菜");
    }
}
