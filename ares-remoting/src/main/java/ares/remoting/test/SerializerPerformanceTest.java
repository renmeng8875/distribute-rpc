package ares.remoting.test;

import ares.remoting.framework.serialization.common.SerializeType;
import ares.remoting.framework.serialization.engine.SerializerEngine;
import ares.remoting.framework.serialization.serializer.ISerializer;
import ares.remoting.test.bean.Person;
import ares.remoting.test.bean.TestBean;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author renmeng
 * @Description: 测试比较每种序列化框架的编码后的体积
 * @date 2018-06-22
 */
public class SerializerPerformanceTest {

    public static void main(String[] args){
        TestBean inputBean = buildTestParam();
        Map<SerializeType, ISerializer> serializerMap = SerializerEngine.serializerMap;
        for(SerializeType serializeType:serializerMap.keySet()){
            ISerializer serializer = serializerMap.get(serializeType);
            byte[] bytes = serializer.serialize(inputBean);
            System.err.println(serializeType+":"+bytes.length);
        }


    }

    public static TestBean buildTestParam(){
        TestBean inputBean = new TestBean();
        inputBean.setByteNum((byte)1);
        inputBean.setShortNum((short)2);
        inputBean.setIntNum(3);
        inputBean.setLongNum(4);
        inputBean.setVarBin("It is fun!good luck!中英文混杂");

        //inputBean.setSelf(inputBean);


        List<Person> innerBeanList = new ArrayList<Person>();
        Person innerBean = new Person();
        innerBean.setAge(100);
        innerBean.setName(RandomStringUtils.randomAlphabetic(10));
        innerBean.setNum(RandomUtils.nextInt(100,100));
        innerBeanList.add(innerBean);
        inputBean.setBeanList(innerBeanList);
        return inputBean;
    }




}
