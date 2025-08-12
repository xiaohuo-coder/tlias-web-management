package extendsDemo;

public class Student {
    String name;
    int age;
    String school;

    public Student() {
        this(null,0,"传智大学");
    }

    public Student(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public void show() {
        int a = 10;
        int b = 20;
        int c = 30;
        System.out.println(name + "," + age);
    }
}
