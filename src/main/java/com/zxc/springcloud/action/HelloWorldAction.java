package com.zxc.springcloud.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldAction {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    public DiscoveryClient client;

    @RequestMapping("/hello")
    public String hello() {
//        ServiceInstance instance = (ServiceInstance) this.client.getInstances("server-one");
//        this.log.info(instance.getHost() + ":" + instance.getServiceId());
        return "hello ";
    }

}
