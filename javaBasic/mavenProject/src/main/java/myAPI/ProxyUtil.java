package myAPI;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

    /**
     * 给学生对象创建代理
     * 形参：被代理的学生类
     * 返回值：创建的代理
     */
    public static Stu CreateProxy(Student student) {
        /**
         * 参数一：指定类加载器，去加载生成的代理类
         * 参数二：指定接口，也就是指定哪些方法需要被代理，数组形式，可以多个
         * 参数三InvocationHandler：用来指定生成的代理对象要干什么事情，是一个接口，要new对象
         * InvocationHandler中的invoke方法不是在创建代理对象时执行的，
         * 而是在代理对象调用方法时才会被 JVM 自动触发。
         */
        Stu stu = (Stu) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(), new Class[]{Stu.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * 参数一：代理的这个对象，即stu本身
                 * 参数二：Stu接口，意味着代理对象可以调用study、game方法
                 * 参数三：方法的实参
                 */
                if (method.getName().equals("study")) {
                    System.out.println("开始代理学习，做准备工作");
                }else if (method.getName().equals("game")) {
                    System.out.println("开始代理游戏，做准备工作");
                }
                //利用反射运行方法，如果有返回值则return返回值，没有的话也已经把方法本体运行了
                return method.invoke(student, args);
            }
        });
        return stu;
    }
}
