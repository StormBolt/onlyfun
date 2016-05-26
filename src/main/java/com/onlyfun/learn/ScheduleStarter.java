package com.onlyfun.learn;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by dujiacheng on 2016/4/22.
 */
@Service
public class ScheduleStarter implements ApplicationListener<ContextRefreshedEvent> {
    private static  Logger logger = Logger.getLogger(ScheduleStarter.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
