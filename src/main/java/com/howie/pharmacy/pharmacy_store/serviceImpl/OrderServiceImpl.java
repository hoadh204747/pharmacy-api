package com.howie.pharmacy.pharmacy_store.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.howie.pharmacy.pharmacy_store.config.RabbitMQConfig;
import com.howie.pharmacy.pharmacy_store.dto.order.OrderCreateDto;
import com.howie.pharmacy.pharmacy_store.dto.order.OrderDto;
import com.howie.pharmacy.pharmacy_store.dto.order.OrderResponseDto;
import com.howie.pharmacy.pharmacy_store.entity.Order;
import com.howie.pharmacy.pharmacy_store.entity.OrderItem;
import com.howie.pharmacy.pharmacy_store.entity.Product;
import com.howie.pharmacy.pharmacy_store.entity.Order.Status;
import com.howie.pharmacy.pharmacy_store.entity.ShippingAddress;
import com.howie.pharmacy.pharmacy_store.mapper.OrderItemMapper;
import com.howie.pharmacy.pharmacy_store.mapper.OrderMapper;
import com.howie.pharmacy.pharmacy_store.mapper.ShippingAddressMapper;
import com.howie.pharmacy.pharmacy_store.rabbitmq.event.OrderCreateEvent;
import com.howie.pharmacy.pharmacy_store.repository.OrderRepository;
import com.howie.pharmacy.pharmacy_store.repository.ProductRepository;
import com.howie.pharmacy.pharmacy_store.services.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShippingAddressMapper shippingAddressMapper;
    private final RabbitTemplate rabbitTemplate;
    private final OrderItemMapper orderItemMapper;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
            ShippingAddressMapper shippingAddressMapper, RabbitTemplate rabbitTemplate, OrderItemMapper orderItemMapper,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shippingAddressMapper = shippingAddressMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.orderItemMapper = orderItemMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toResponseDtoList(orders);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderCreateDto orderCreateDto) {
        Order order = orderMapper.toEntity(orderCreateDto);

        if (orderCreateDto.getShippingAddress() != null) {
            ShippingAddress shippingAddress = shippingAddressMapper.toEntity(orderCreateDto.getShippingAddress());
            shippingAddress.setOrder(order); // Rất quan trọng: Thiết lập mối quan hệ ngược lại
            order.setShippingAddress(shippingAddress); // Thiết lập mối quan hệ 1-1
        } else {
            throw new IllegalArgumentException("Shipping address is required to create an order.");
        }
        if (orderCreateDto.getOrderItems() != null && !orderCreateDto.getOrderItems().isEmpty()) {
            List<OrderItem> items = orderCreateDto.getOrderItems().stream()
                    .map(itemDto -> {
                        OrderItem item = orderItemMapper.toEntity(itemDto);
                        Product product = productRepository.findById(itemDto.getProductId())
                                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                        item.setProduct(product);
                        item.setOrder(order); // Thiết lập mối quan hệ ngược lại
                        return item;
                    })
                    .toList();
            order.setOrderItems(items);
        } else {
            throw new IllegalArgumentException("At least one order item is required to create an order.");
        }
        if (order.getTotalPrice() == null) {
            order.setTotalPrice(BigDecimal.ZERO);
        }
        if (order.getOrderCode() == null || order.getOrderCode().isEmpty()) {
            order.setOrderCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase()); // Tạo mã đơn hàng duy nhất
        }
        order.setSlugStatus(order.getStatus().name().toLowerCase());
        Order savedOrder = orderRepository.save(order);

        OrderCreateEvent orderCreateEvent = new OrderCreateEvent();
        orderCreateEvent.setOrderId(savedOrder.getId());
        orderCreateEvent.setOrderCode(savedOrder.getOrderCode());
        orderCreateEvent.setUserId(1);
        orderCreateEvent.setUserEmail("");

        // Gửi sự kiện OrderCreateEvent đến RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EVENTS_EXCHANGE, RabbitMQConfig.ORDER_CREATED_ROUTING_KEY,
                orderCreateEvent);
        System.out.println("Order " + savedOrder.getId() + " created and event published to RabbitMQ.");

        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(Integer id, OrderCreateDto orderCreateDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

    @Override
    public void deleteOrder(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public OrderDto updateOrderStatus(Integer id, Status newStatus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderStatus'");
    }

}
