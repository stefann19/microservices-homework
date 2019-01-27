package io.pivotal.microservices.services.web.Controllers;

import io.pivotal.microservices.services.warehouse.DTOs.ProductEntity;
import io.pivotal.microservices.services.web.Services.WebProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Client controller, fetches Account info from the microservice via
 * {@link WebProductService}.
 *
 * @author Paul Chapman
 */
@Controller
public class WebProductController {
    @Autowired
    protected WebProductService webProductService;
    protected Logger logger = Logger.getLogger(WebProductController.class
            .getName());

    public WebProductController(WebProductService webProductService) {
        this.webProductService = webProductService;


    }

    @RequestMapping("/products/{productId}")
    public String getProductById(Model model,
                           @PathVariable("productId") String productId) {

        logger.info("warehouse-service getProductById() invoked: " + productId);

        ProductEntity productEntity = webProductService.findById(productId);
        logger.info("warehouse-service getProductById() found: " + productEntity);

        model.addAttribute("product", productEntity);
        return "product";
    }

}
