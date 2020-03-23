package com.liu;

/**
 * @author shidacaizi
 * @data 2020/3/10 13:48
 */
public class ReflecTest01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c1 = Class.forName("com.liu.ReflecTest01");

        System.out.println();

//        System.out.println(c1.getTypeParameters());
//        System.out.println("----------------------------");
//        System.out.println(c1.getTypeName());
//        System.out.println("----------------------------");
//        System.out.println(c1.getSuperclass());
//        System.out.println("----------------------------");
//        System.out.println(c1.getSigners());
//        System.out.println("----------------------------");
//        System.out.println(c1.getSimpleName());
//        System.out.println("----------------------------");
//        System.out.println(c1.getProtectionDomain());
//        System.out.println("----------------------------");
//        System.out.println(c1.getPackage());
//        System.out.println("----------------------------");
//        System.out.println(c1.getName());
//        System.out.println("----------------------------");
//        System.out.println(c1.getModifiers());
//        System.out.println("----------------------------");
//        System.out.println(c1.getMethods());
//        System.out.println("----------------------------");
//        System.out.println(c1.getInterfaces());
    }
}

class User {
    private String name;
    private int id;
    private  int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
