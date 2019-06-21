package com.lwl.demo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	@Scheduled(cron = "* * * * * ?")
    public void runJob() {
        System.err.println("【*** MyTaskB - 间隔调度  ***】"
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                        .format(new Date()));
    }
}
