package io.pivotal.microservices.services.web.Services;

import io.pivotal.microservices.services.warehouse.DTOs.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class WebProductService {
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;


    protected Logger logger = Logger.getLogger(WebProductService.class
            .getName());

    public WebProductService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }
    public ProductEntity findById(String productId) {

        logger.info("findByNumber() invoked: for " + productId);
        return restTemplate.getForObject(serviceUrl + "/products/{number}",
                ProductEntity.class, productId);
    }

}
