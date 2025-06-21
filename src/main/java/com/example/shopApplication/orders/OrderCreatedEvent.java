package com.example.shopApplication.orders;

public class OrderCreatedEvent {
    private final Long orderId;
    private final Long productId;
    private final int quantity;

    public OrderCreatedEvent(Long orderId, Long productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getOrderId() { return orderId; }
    public Long getProductId() { return productId; }
    public int getQuantity() { return quantity; }
}
