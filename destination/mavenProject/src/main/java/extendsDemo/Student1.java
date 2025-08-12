package extendsDemo;

public class Student1 extends Person {

    public Student1() {
        //super();
        System.out.println("子类的无参构造");
    }
    public Student1(String name, int age) {
        super(name, age);
        System.out.println("子类的有参构造");
    }

    public String toString() {
        return "Student{}";
    }
}
