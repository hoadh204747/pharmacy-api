package com.howie.pharmacy.pharmacy_store.rabbitmq.consumer;

import com.howie.pharmacy.pharmacy_store.config.RabbitMQConfig;
import com.howie.pharmacy.pharmacy_store.rabbitmq.event.OrderCreateEvent;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message + " from queue: " + RabbitMQConfig.QUEUE_NAME);
        // Xử lý tin nhắn ở đây
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_QUEUE)
    public void receiveOrderCreatedEvent(OrderCreateEvent orderCreatedEvent) {
        System.out.println("Received Order Created Event: " + orderCreatedEvent + " from queue: "
                + RabbitMQConfig.ORDER_CREATED_QUEUE);
        // Xử lý sự kiện tạo đơn hàng ở đây
    }
}
