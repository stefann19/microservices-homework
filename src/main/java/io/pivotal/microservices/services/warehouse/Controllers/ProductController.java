package io.pivotal.microservices.services.warehouse.Controllers;

import io.pivotal.microservices.exceptions.AccountNotFoundException;
import io.pivotal.microservices.services.warehouse.DTOs.ProductEntity;
import io.pivotal.microservices.services.warehouse.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ProductController {

    protected Logger logger = Logger.getLogger(ProductController.class
            .getName());
    protected ProductRepository productRepository;

    /**
     * Create an instance plugging in the respository of Accounts.
     *
     * @param productRepository
     *            An account repository implementation.
     */
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;

        logger.info("ProductRepository says system has "
                + productRepository.countProductEntities() + " products");
    }

    /**
     * Fetch an account with the specified account number.
     *
     * @param productId
     *            A numeric, 9 digit account number.
     * @return The product if found.
     * @throws AccountNotFoundException
     *             If the number is not recognised.
     */
    @RequestMapping("/products/{productId}")
    public ProductEntity byNumber(@PathVariable("productId") String productId) {

        logger.info("warehouse-service byNumber() invoked: " + productId);
        ProductEntity productEntity = productRepository.findById( Integer.parseInt(productId));

        logger.info("warehouse-service byNumber() found: " + productEntity);

        if (productEntity == null)
            throw new AccountNotFoundException(productId);
        else {
            return productEntity;
        }
    }
    /**
     * Fetch all the products.
     *
     * @return The products if any.
     * @throws AccountNotFoundException
     */
    @RequestMapping("/getAllProducts")
    public List<ProductEntity> getAllProducts() {

        logger.info("warehouse-service getAllProducts() invoked: ");
        List<ProductEntity> productEntities = productRepository.getAllProductEntities();

        logger.info("warehouse-service getAllProducts() invoked: ");

        if (productEntities == null)
            throw new AccountNotFoundException("products :)");
        else {
            return productEntities;
        }
    }
}
