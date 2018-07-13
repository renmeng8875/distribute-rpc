package ares.remoting.test.bean;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.util.List;

@Message
public class TestBean  implements Serializable {
    public TestBean(){

    }

    public TestBean getSelf() {
        return self;
    }

    public void setSelf(TestBean self) {
        this.self = self;
    }

    private TestBean self;

    private byte byteNum;

    private short shortNum;

    private int intNum;

    private long longNum;

    private String varBin;

    private List<Person> beanList;


    public byte getByteNum() {
        return byteNum;
    }

    public void setByteNum(byte byteNum) {
        this.byteNum = byteNum;
    }

    public short getShortNum() {
        return shortNum;
    }

    public void setShortNum(short shortNum) {
        this.shortNum = shortNum;
    }

    public int getIntNum() {
        return intNum;
    }

    public void setIntNum(int intNum) {
        this.intNum = intNum;
    }

    public long getLongNum() {
        return longNum;
    }

    public void setLongNum(long longNum) {
        this.longNum = longNum;
    }

    public String getVarBin() {
        return varBin;
    }

    public void setVarBin(String varBin) {
        this.varBin = varBin;
    }

    public List<Person> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<Person> beanList) {
        this.beanList = beanList;
    }
}

