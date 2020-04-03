package com.wen.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author shidacaizi
 * @date 2020/4/1 19:52
 */
public class Student {
    private String nane;
    private Address address;
    private String[] books;
    private List<String> hobbys;
    private HashMap<String,String> card;
    private Set<String> ganes;
    private String Hife;
    private Properties info;

    public String getNane() {
        return nane;
    }

    public void setNane(String nane) {
        this.nane = nane;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobbys() {
        return hobbys;
    }

    public void setHobbys(List<String> hobbys) {
        this.hobbys = hobbys;
    }

    public HashMap<String, String> getCard() {
        return card;
    }

    public void setCard(HashMap<String, String> card) {
        this.card = card;
    }

    public Set<String> getGanes() {
        return ganes;
    }

    public void setGanes(Set<String> ganes) {
        this.ganes = ganes;
    }

    public String getHife() {
        return Hife;
    }

    public void setHife(String hife) {
        Hife = hife;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }
}
