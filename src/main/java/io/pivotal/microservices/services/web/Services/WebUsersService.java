package io.pivotal.microservices.services.web.Services;

import io.pivotal.microservices.services.user.management.DTOs.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class WebUsersService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;


    protected Logger logger = Logger.getLogger(WebUsersService.class
            .getName());

    public WebUsersService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }

    public UserEntity findById(String userId) {

        logger.info("findByNumber() invoked: for " + userId);
        return restTemplate.getForObject(serviceUrl + "/users/{number}",
                UserEntity.class, userId);
    }
}
