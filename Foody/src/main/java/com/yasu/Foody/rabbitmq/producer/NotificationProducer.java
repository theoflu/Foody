package com.yasu.Foody.rabbitmq.producer;

import com.yasu.Foody.rabbitmq.models.Notification;
import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service

@RequiredArgsConstructor

public class NotificationProducer {

    @Value("${sr.rabbit.routing.name}")
    private String routingName;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart() {
        // Uygulama başladığında çalışmasını istediğiniz kodları buraya yazın.
        runSomething();

    }


    @Scheduled(fixedDelay = 5000, initialDelay = 5000)

    public void runSomething() {

        Notification notification = new Notification();
        notification.setNotificationID(UUID.randomUUID().toString());
        notification.setCreateAt(new Date());
        notification.setMessage("WELCOME TO RABBITMQ");
        notification.setSeen(Boolean.FALSE);
       sendToQueue(notification);//producer, şu message'ımı queue'ye yolla
    }

    private final RabbitTemplate rabbitTemplate;


    public void sendToQueue(Notification notification)  {
        System.out.println("CallerPerson Sent ID : " + notification.getNotificationID());// her mssage gonderdiginde gonderdigi messageın id'sini ekrana yazsın
        rabbitTemplate.convertAndSend(exchangeName,routingName, notification);//istedigimiz routinge message'ımızı atıcak

    }
}
