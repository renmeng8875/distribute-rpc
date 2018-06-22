package ares.remoting.framework.serialization.serializer.impl;

import ares.remoting.framework.serialization.serializer.ISerializer;
import com.babytree.commons.protopack.base.Pack;
import com.babytree.commons.protopack.base.Unpack;
import com.babytree.commons.protopack.marshal.ObjectMarshal;

/**
 * @author renmeng
 * @Description: 自定义编解码器
 */
public class ProtoPackSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        Pack pack = new Pack();
        byte[] b = null;
        try {
            ObjectMarshal marshal = new ObjectMarshal(obj);
            marshal.marshal(pack);
            b = pack.getBuffer().array();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;

    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        Unpack unpack = new Unpack(data);
        T obj = null;
        try {
             obj = clazz.newInstance();
            ObjectMarshal outputMarshal = new ObjectMarshal(obj);
            outputMarshal.unmarshal(unpack);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
