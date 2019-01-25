package io.pivotal.microservices.services.web.Services;

import io.pivotal.microservices.services.billing.DTOs.BillEntity;
import io.pivotal.microservices.services.billing.DTOs.BillProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Service
public class WebBillingService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;


    protected Logger logger = Logger.getLogger(WebAccountsService.class
            .getName());

    public WebBillingService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }

    public BillEntity findById(String billId) {

        logger.info("findByNumber() invoked: for " + billId);
        return restTemplate.getForObject(serviceUrl + "/bills/{number}",
                BillEntity.class, billId);
    }
    public BillProductEntity[] findBillProductsByBillId(String billId) {

        logger.info("findByNumber() invoked: for " + billId);
        return restTemplate.getForObject(serviceUrl + "/billProductsByBillId/{number}",
                BillProductEntity[].class, billId);
    }
}
