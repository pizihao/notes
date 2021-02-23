package com.example.mqserver.model;

/**
 * @program: JVMDome
 * @description:
 * @author: liuwenhao
 * @create: 2021-02-23 12:40
 **/
public class User {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 座右铭
     */
    private String motto;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public String toString() {

        return age + "岁" + gender + "人[" + name + "]的座右铭居然是: " + motto + "!!!";
    }

}
