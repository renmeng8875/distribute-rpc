package ares.remoting.test.bean;


import org.msgpack.annotation.Message;

import java.io.Serializable;

@Message
public class InnerBean implements Serializable{
    public InnerBean(){

    }
    private String name;

    private int age;

    private long num;

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

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}