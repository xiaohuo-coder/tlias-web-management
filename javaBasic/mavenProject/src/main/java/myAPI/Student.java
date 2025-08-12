package myAPI;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable,Stu {

    String name;
    int age;

    public Student() {
    }

    public Student(String str) {
        String[] split = str.split(",");
        this.name = split[0];
        this.age = Integer.parseInt(split[1]);
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 获取
     * @return name
     */
    private String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student{name = " + name + ", age = " + age + "}";
    }

    @Override
    public String study(String lesson) {
        System.out.println("学生" + this.name + "正在学习" + lesson);
        return "学得很认真";
    }

    @Override
    public void game(){
        System.out.println("学生"+this.name+"正在打游戏");
    }
}
