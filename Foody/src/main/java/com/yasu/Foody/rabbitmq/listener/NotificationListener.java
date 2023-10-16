package com.yasu.Foody.rabbitmq.listener;

import com.yasu.Foody.rabbitmq.models.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {//consumer
    @RabbitListener(queues = "queue")
    public void handleMessage(Notification notification)  {
        System.out.println("********************************************************************************************************************************************************************************");
        System.out.println("Mesaj Gönderildi ...");
        System.out.println(notification.toString());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
