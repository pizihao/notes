package helloworld.liu;

import helloworld.pojo.Teacher;

/**
 * @author shidacaizi
 * @data 2020/3/10 14:04
 */
public class ReflecTest02 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
//        try {
//            Student student = Student.class.newInstance();
//            student.setAge(2);
//            System.out.println(student.toString());
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

//        try {
//            User user = User.class.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

//        Teacher teacher = Teacher.class.newInstance();
//
//        Class aClass = teacher.getClass();
//        Class aClass1 = Teacher.class;
//        Class aClass2 = Class.forName("com.pojo.Teacher");
//
//        System.out.println(aClass.hashCode());
//        System.out.println(aClass1.hashCode());
//        System.out.println(aClass2.hashCode());

        Teacher teacher = Teacher.class.newInstance();
        Teacher teacher1 = new Teacher();

        System.out.println(teacher.getClass().hashCode());
        System.out.println(teacher1.getClass().hashCode());
    }
}


class Student {
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

    public Student() {
    }

    public Student(String name, int id, int age) {
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

