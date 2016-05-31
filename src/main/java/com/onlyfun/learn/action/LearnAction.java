package com.onlyfun.learn.action;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.onlyfun.learn.common.Person;
import com.onlyfun.learn.model.RequestResult;
import com.onlyfun.learn.rabbitmq.spring.service.MQProducer;
import com.onlyfun.learn.rabbitmq.spring.service.QueueListenter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dujiacheng on 2016/4/22.
 */
@Controller
public class LearnAction extends AbstractAction{
    private static Logger logger = Logger.getLogger(LearnAction.class);
    private static Cache<String, String> cacheBuild = CacheBuilder.newBuilder().expireAfterWrite(100,TimeUnit.SECONDS).build();
    private AtomicInteger count = new AtomicInteger(0);
    @Resource
    private MQProducer mqProducer;
    @Resource
    private QueueListenter queueListenter;

    @RequestMapping(value = "learn")
    public void learn(HttpServletRequest req, HttpServletResponse res,String key){

//        try {
//            cacheBuild.get(key, new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    String value = count + "";
//                    logger.info("it is " + count);
//                    count.addAndGet(1);
//                    return value;
//                }
//            });
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("a",2);
        map.put("b",3);

        Person person = new Person();
        person.setAge(2);
        person.setName("jareddu");

        RequestResult result = new RequestResult();
        result.setError(0);
        result.setMsg("success");
        //result.addAll(map);

        String json =  JSON.toJSONString(result);
        RequestResult  result2 = JSON.parseObject(json,RequestResult.class);
        String json2 =  JSON.toJSONString(result2);



        writeResponse(res,json,0,null);
    }

    /***
     *
     * @param req
     * @param res
     * @param queueKey
     * @param msg
     */
    @RequestMapping(value = "pulish")
    public void pulish(HttpServletRequest req, HttpServletResponse res,String exchange,String routingkey,String msg){
        mqProducer.sendDataToQueue(exchange,routingkey,msg);
        writeResponse(res,"success",0,null);
    }

    @RequestMapping(value = "isLive")
    public void isLive(HttpServletRequest req, HttpServletResponse res,String queueKey,String msg){
        writeResponse(res,"isLive",0,null);
    }



}


