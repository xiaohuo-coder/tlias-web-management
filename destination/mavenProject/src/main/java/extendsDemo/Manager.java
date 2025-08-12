package extendsDemo;

public class Manager extends Employee{
    private double bonus;

    public Manager() {
    }

    public Manager(int id, String name, double salary, double bonus) {
        super(id, name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void work(){
        System.out.println("经理" + getName() + "正在工作");
    }
}
