package ares.remoting.framework.serialization.engine;


import ares.remoting.framework.serialization.common.SerializeType;
import ares.remoting.framework.serialization.serializer.ISerializer;
import ares.remoting.framework.serialization.serializer.impl.*;
import avro.shaded.com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author renmeng created on 17/1/23.
 * @version $Id$
 */
public class SerializerEngine {

    public static final Map<SerializeType, ISerializer> serializerMap = Maps.newConcurrentMap();

    static {
        serializerMap.put(SerializeType.DefaultJavaSerializer, new DefaultJavaSerializer());
        serializerMap.put(SerializeType.HessianSerializer, new HessianSerializer());
        serializerMap.put(SerializeType.JSONSerializer, new JSONSerializer());
        serializerMap.put(SerializeType.XmlSerializer, new XmlSerializer());
        serializerMap.put(SerializeType.ProtoStuffSerializer, new ProtoStuffSerializer());
        serializerMap.put(SerializeType.MarshallingSerializer, new MarshallingSerializer());

        serializerMap.put(SerializeType.ProtoPackSerializer, new ProtoPackSerializer());
        serializerMap.put(SerializeType.KryoSerializer, new KryoSerializer());
        serializerMap.put(SerializeType.MessagePackSerializer, new MessagePackSerializer());



        //以下三类不能使用普通的java bean,需要事先双方定好协议文件然后用程序生成代码
        //serializerMap.put(SerializeType.AvroSerializer, new AvroSerializer());
        //serializerMap.put(SerializeType.ThriftSerializer, new ThriftSerializer());
        //serializerMap.put(SerializeType.ProtocolBufferSerializer, new ProtocolBufferSerializer());
    }


    public static <T> byte[] serialize(T obj, String serializeType) {
        SerializeType serialize = SerializeType.queryByType(serializeType);
        if (serialize == null) {
            throw new RuntimeException("serialize is null");
        }

        ISerializer serializer = serializerMap.get(serialize);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.serialize(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T deserialize(byte[] data, Class<T> clazz, String serializeType) {

        SerializeType serialize = SerializeType.queryByType(serializeType);
        if (serialize == null) {
            throw new RuntimeException("serialize is null");
        }
        ISerializer serializer = serializerMap.get(serialize);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.deserialize(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
