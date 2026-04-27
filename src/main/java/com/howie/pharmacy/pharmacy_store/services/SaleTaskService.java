package com.howie.pharmacy.pharmacy_store.services;

import java.time.LocalDateTime;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.howie.pharmacy.pharmacy_store.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class SaleTaskService {
    private final ProductRepository productRepository;

    public SaleTaskService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        System.out.println("Ứng dụng đã khởi động. Kiểm tra và cập nhật các sản phẩm hết hạn khuyến mãi...");
    }

    @Scheduled(cron = "0 * * * * *") // Chạy vào phút đầu tiên của mỗi giờ
    @Transactional
    public void updateExpiredSales() {
        System.out.println("Đang kiểm tra và cập nhật các sản phẩm hết hạn khuyến mãi...");

        // int updatedProducts =
        // productRepository.closeExpiredSales(LocalDateTime.now());

        // if (updatedProducts > 0) {
        // System.out.println("Đã cập nhật " + updatedProducts + " sản phẩm hết hạn
        // khuyến mãi.");
        // }
    }

}
