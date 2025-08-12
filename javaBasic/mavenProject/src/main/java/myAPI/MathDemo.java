package myAPI;

public class MathDemo {
    public static void main(String[] args) throws Exception{
        //创建学生对象
        Student s1 = new Student("李四",18);
        //获取代理的对象
        Stu ProxyStu = ProxyUtil.CreateProxy(s1);

        //调用学习的方法
        String result = ProxyStu.study("语文");
        System.out.println(result);
    }
}