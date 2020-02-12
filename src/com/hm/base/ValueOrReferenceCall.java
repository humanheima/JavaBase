package com.hm.base;

/**
 * Crete by dumingwei on 2020-02-11
 * Desc: 测试Java是值传递还是引用传递
 * 参考链接：https://blog.csdn.net/javazejian/article/details/51192130
 * <p>
 * 结论：Java中是函数调用中的参数都是值传递
 */
public class ValueOrReferenceCall {


    private static int x = 10;

    private static User user = null;
    private static User stu = null;

    public static void main(String[] args) {

        /*System.out.println("调用前x的值：" + x);
        updateValue(x);
        System.out.println("调用后x的值：" + x);*/


        /*user = new User("zhangsan", 26);
        System.out.println("调用前user的值：" + user.toString());
        updateUser(user);
        System.out.println("调用后user的值：" + user.toString());*/


        user = new User("user", 26);
        stu = new User("stu", 18);
        System.out.println("调用前user的值：" + user.toString());
        System.out.println("调用前stu的值：" + stu.toString());
        swap(user, stu);
        System.out.println("调用后user的值：" + user.toString());
        System.out.println("调用后stu的值：" + stu.toString());


    }


    /**
     * 当传递给方法的参数是基本数据类型时，方法可以修改参数，但是不会修改原数据
     * 在这个例子中，可以修改方法的参数`value`，但是x不受影响
     *
     * @param value 基本数据类型
     */
    public static void updateValue(int value) {
        value = 3 * value;
        //System.out.println(value);
    }

    /**
     * 当传递给方法参数是引用数据类型时，方法修改可以修改参数所指向对象的值。
     *
     * @param student 引用数据类型
     */
    public static void updateUser(User student) {
        student.setName("Lisi");
        student.setAge(18);
    }

    /**
     * 交换两个对象
     * <p>
     * 在这个例子中
     * swap方法的参数x和y被初始化为两个对象引用的拷贝，
     * 这个方法交换的是这两个拷贝的值而已，最终，所做的事都是白费力气罢了。
     * 在方法结束后x，y将被丢弃，而原来的变量user和stu仍然引用这个方法调用之前所引用的对象。
     *
     * @param x
     * @param y
     */
    public static void swap(User x, User y) {
        User temp = x;
        x = y;
        y = temp;

        System.out.println("in swap method：" + x.toString());
        System.out.println("in swap method：" + y.toString());
    }


    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
