package com.liu.stream.study;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author shidacaizi
 * @data 2020/3/12 16:44
 */
public class TestDome01 {
    public static void main(String[] args) {
        User u1 = new User(1,"u1",21);
        User u2 = new User(2,"u2",22);
        User u3 = new User(3,"u3",23);
        User u4 = new User(4,"u4",24);
        User u5 = new User(5,"u5",25);
        User u6 = new User(6,"u6",26);
        //集合就是存储
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5,u6);

        //计算交给Stream流
        list.stream()
                .filter(u -> {return u.getId()%2==0;})
                .filter(u -> {return u.getAge() > 23;})
                .map(u -> {return u.getName().toUpperCase();})
                .sorted((usr1,usr2) -> {return usr2.compareTo(usr1);})
                .limit(1)
                .forEach(System.out :: println);
    }
}

class User{
    private Integer Id;
    private String name;
    private Integer age;

    public User(Integer id, String name, Integer age) {
        Id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}