package ares.remoting.test;

import ares.remoting.test.bean.Person;
import ares.remoting.test.bean.TestBean;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author renmeng created on 16/10/5.
 * @version $Id$
 */
public class MainClient {

    private static final Logger logger = LoggerFactory.getLogger(MainClient.class);

    public static void main(String[] args) throws Exception {

        //引入远程服务
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ares-client.xml");
        //获取远程服务
        final HelloService helloService = (HelloService) context.getBean("remoteHelloService");

        long count = 10000000000L;

        //调用服务并打印结果
        for (int i = 0; i < count; i++) {
            try {
                Person p = new Person();
                p.setAge(18);
                p.setNum(count);
                p.setName(RandomStringUtils.randomAlphanumeric(10));
                String result = helloService.sayHello(p);
                System.err.println(result);
            } catch (Exception e) {
                logger.warn("--------", e);
            }
        }

        //关闭jvm
        System.exit(0);
    }
}
