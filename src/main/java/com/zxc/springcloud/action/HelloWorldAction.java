package com.zxc.springcloud.action;

import com.zxc.springcloud.pojo.User;
import com.zxc.springcloud.util.MapConvertUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloWorldAction {

    private Log log = LogFactory.getLog(this.getClass());

    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    public DiscoveryClient client;

    @RequestMapping("/hello")
    public String hello() {
        ServiceInstance instance = this.client.getInstances("SERVER").get(0);
        Map map = instance.getMetadata();
        for (Object entry : map.entrySet()) {
            if (entry instanceof Map.Entry)
                this.log.info(((Map.Entry) entry).getKey() + ":" + ((Map.Entry) entry).getValue());
        }
        this.log.info(instance.getHost() + ":" + instance.getServiceId() + ":" + instance.getMetadata());
        this.log.info(this.count.getAndAdd(1));
        return "hello world";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User getUser (HttpEntity entity) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.log.info(entity);
        HttpHeaders headers = entity.getHeaders();
        for (Object header : headers.entrySet()) {
            if (header instanceof Map.Entry)
                this.log.info(((Map.Entry) header).getKey() + ":" + ((Map.Entry) header).getValue());
        }
        User user = (User) MapConvertUtil.convert((Map) entity.getBody(), User.class);
        return user;
    }

    @RequestMapping("/send/{what}")
    public String sendWhat(@PathVariable("what") String what) {
        return what;
    }

}
