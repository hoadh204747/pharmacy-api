package com.howie.pharmacy.pharmacy_store.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "my-exchange";
    public static final String QUEUE_NAME = "my-queue";
    public static final String ROUTING_KEY = "my.routing.key";

    // Tên Exchange và Queue cho sự kiện tạo đơn hàng
    public static final String ORDER_EVENTS_EXCHANGE = "order.events";
    public static final String ORDER_CREATED_QUEUE = "order.created.queue";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created"; // Routing key cho sự kiện tạo đơn hàng

    // Queue cho sự kiện cập nhật trạng thái đơn hàng (ví dụ: từ payment service)
    public static final String ORDER_STATUS_UPDATE_QUEUE = "order.status.update.queue";
    public static final String ORDER_STATUS_UPDATE_ROUTING_KEY = "order.status.update";

    // Khai báo Queue
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false); // Tên queue, durable (true/false)
    }

    // Khai báo Exchange (ở đây dùng TopicExchange)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Liên kết Queue với Exchange bằng Routing Key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    // Khai báo Exchange (TopicExchange phù hợp cho các loại sự kiện)
    @Bean
    public TopicExchange orderEventsExchange() {
        return new TopicExchange(ORDER_EVENTS_EXCHANGE);
    }

    // Khai báo Queue cho sự kiện Order Created
    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(ORDER_CREATED_QUEUE, true); // durable: true - đảm bảo queue tồn tại ngay cả khi RabbitMQ khởi động lại
    }

    // Liên kết Queue orderCreatedQueue với Exchange orderEventsExchange
    @Bean
    public Binding bindingOrderCreated(Queue orderCreatedQueue, TopicExchange orderEventsExchange) {
        return BindingBuilder.bind(orderCreatedQueue)
                .to(orderEventsExchange)
                .with(ORDER_CREATED_ROUTING_KEY);
    }

    // Khai báo Queue cho sự kiện Order Status Update
    @Bean
    public Queue orderStatusUpdateQueue() {
        return new Queue(ORDER_STATUS_UPDATE_QUEUE, true);
    }

    // Liên kết Queue orderStatusUpdateQueue với Exchange orderEventsExchange
    @Bean
    public Binding bindingOrderStatusUpdate(Queue orderStatusUpdateQueue, TopicExchange orderEventsExchange) {
        return BindingBuilder.bind(orderStatusUpdateQueue)
                .to(orderEventsExchange)
                .with(ORDER_STATUS_UPDATE_ROUTING_KEY);
    }

    // Cấu hình Message Converter để tự động chuyển đổi Object sang JSON và ngược lại
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Cấu hình RabbitTemplate để gửi message
    // @Bean
    // public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    //     final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     rabbitTemplate.setMessageConverter(jsonMessageConverter());
    //     return rabbitTemplate;
    // }
}