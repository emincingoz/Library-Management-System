package com.emincingoz.mailservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${message-broker-queues.job-mail-create}")
    private String jobMailCreateQueue;

    @Value("${message-broker-queues.job-mail-update}")
    private String jobMailUpdateQueue;

    @Value("${message-broker-queues.job-mail-delete}")
    private String jobMailDeleteQueue;

    @Value("${message-broker-queues.job-mail-send}")
    private String jobMailSendQueue;

    @Bean
    Queue jobMailCreate() { return new Queue(this.jobMailCreateQueue, false); }

    @Bean
    Queue jobMailUpdate() {
        return new Queue(this.jobMailUpdateQueue, false);
    }

    @Bean
    Queue jobMailDelete() {
        return new Queue(this.jobMailDeleteQueue, false);
    }

    @Bean
    Queue jobMailSend() {return new Queue(this.jobMailSendQueue, false);}
}
