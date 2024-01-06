package com.xiazhenyu.bean;

import java.util.List;
import lombok.Data;

/**
 * ClassName: User<br/>
 * Description: <br/>
 * date: 2019/6/28 10:38 AM<br/>
 *
 * @author chengluchao
 * @since JDK 1.8
 */
//@Data
public class User {

    private int age;
    private long id;
    private String name;
    private Card card;

    public User(int age, long id, String name, Card card) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.card = card;
    }

    public User(int age, long id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}