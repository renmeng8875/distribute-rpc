package ares.remoting.framework.serialization.serializer.impl;

import ares.remoting.framework.serialization.serializer.ISerializer;
import org.msgpack.MessagePack;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author renmeng
 * @Description: MessagePack编解码器
 */
public class MessagePackSerializer implements ISerializer{

    private MessagePack messagePack = new MessagePack();

    private Set<String> registerCache = new HashSet<String>();

    @Override
    public <T> byte[] serialize(T obj) {

        byte[] b = null;
        try {
            if(!registerCache.contains(obj.getClass().getName())){
                messagePack.register(obj.getClass());
            }
            b = messagePack.write(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        T t = null;
        try {
            t = messagePack.read(data, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
}
