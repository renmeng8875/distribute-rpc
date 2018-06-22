package ares.remoting.test;

import ares.remoting.framework.serialization.common.SerializeType;
import ares.remoting.framework.serialization.engine.SerializerEngine;
import ares.remoting.framework.serialization.serializer.ISerializer;
import ares.remoting.test.bean.InnerBean;
import ares.remoting.test.bean.TestBean;

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
        Map<SerializeType, ISerializer> serializerMap = SerializerEngine.serializerMap;
        for(SerializeType serializeType:serializerMap.keySet()){
            ISerializer serializer = serializerMap.get(serializeType);
            byte[] bytes = serializer.serialize(buildTestParam());
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

        InnerBean innerBean = new InnerBean();
        innerBean.setAge(18);
        innerBean.setName("babyTree");
        innerBean.setNum(30000);



        List<InnerBean> innerBeanList = new ArrayList<InnerBean>();
        innerBeanList.add(innerBean);


        inputBean.setBeanList(innerBeanList);
        return inputBean;
    }


}
