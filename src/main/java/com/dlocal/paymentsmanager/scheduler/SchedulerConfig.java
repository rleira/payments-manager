package com.dlocal.paymentsmanager.scheduler;

import com.dlocal.paymentsmanager.scheduler.task.FixerIOTask;
import com.dlocal.paymentsmanager.scheduler.task.PaymentProcessTask;
import com.dlocal.paymentsmanager.services.MasterSlaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private Environment env;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(Integer.parseInt(env.getProperty("scheduler.threads")));
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        threadPoolTaskScheduler.initialize();
        if (MasterSlaveService.isMaster()) {
            threadPoolTaskScheduler.schedule(new FixerIOTask(), new CronTrigger("*/30 * * * * ?"));
        }
        threadPoolTaskScheduler.schedule(new PaymentProcessTask(), new CronTrigger("*/30 * * * * ?"));
        return threadPoolTaskScheduler;
    }
}
