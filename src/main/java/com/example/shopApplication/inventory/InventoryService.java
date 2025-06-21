package com.example.shopApplication.inventory;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventoryService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    public Product getProduct(Long id) {
        return entityManager.find(Product.class, id);
    }

    public Product createProduct(String name, int quantity) {
        Product product = new Product(name, quantity);
        entityManager.persist(product);
        return product;
    }

    public boolean reserveProduct(Long productId, int quantity) {
        Product product = getProduct(productId);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            return true;
        }
        return false;
    }
}
