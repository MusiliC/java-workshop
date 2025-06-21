package com.example.shopApplication.orders;

import com.example.shopApplication.inventory.InventoryService;
import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    private final InventoryService inventoryService;
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(InventoryService inventoryService, ApplicationEventPublisher eventPublisher) {
        this.inventoryService = inventoryService;
        this.eventPublisher = eventPublisher;
    }

    public Order createOrder(Long productId, int quantity) {
        if (inventoryService.reserveProduct(productId, quantity)) {
            Order order = new Order(productId, quantity);
            order.setStatus("CONFIRMED");
            entityManager.persist(order);

            eventPublisher.publishEvent(new OrderCreatedEvent(order.getId(), productId, quantity));
            return order;
        } else {
            throw new RuntimeException("Insufficient inventory");
        }
    }

    public List<Order> getAllOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public Order getOrder(Long id) {
        return entityManager.find(Order.class, id);
    }
}
