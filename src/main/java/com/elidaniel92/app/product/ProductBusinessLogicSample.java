package com.elidaniel92.app.product;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ProductBusinessLogicSample {
    private final ProductDAO productDAO;
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private int count = 1;

    @Inject
    public ProductBusinessLogicSample(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // Print all products
    public void printAllProducts() throws SQLException {
        try {
            System.out.println("====================================================");
            String date = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(java.time.LocalDateTime.now());
            System.out.println("Running SQL task at " + date);
            productDAO.getAllProducts().forEach(System.out::println);
            System.out.println("====================================================");
            System.out.println("");
        } catch (SQLException e) {
            log.error("Cannot print products", e);
            throw e;
        }
    }

    // Create a new product
    public void createProduct() throws SQLException {
        ProductEntity newProduct = new ProductEntity( "New Product" + count, 100 + count, 10 + count);
        ProductEntity createdProduct = productDAO.createProduct(newProduct);
        System.out.println("Created Product: " + createdProduct);
    }

    public void updateProduct() throws SQLException {
        int id = count;
        ProductEntity product = productDAO.getProductById(id);
        if (product != null) {
            product.setName("Updated Product Name " + count);
            product.setPrice(200);
            product.setQuantityInStock(20);
            boolean isUpdated = productDAO.updateProduct(product);
            if (isUpdated) {
                System.out.println("Updated Product: " + productDAO.getProductById(id));
            } else {
                System.out.println("Product not found or update failed.");
            }
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public void deleteProduct() throws SQLException {
        int id = count;
        boolean isDeleted = productDAO.deleteProduct(id);
        if (isDeleted) {
            System.out.println("Deleted Product with ID " + id);
        } else {
            System.out.println("Product with ID " + id + " not found or delete failed.");
        }
        count++;
    }
}
