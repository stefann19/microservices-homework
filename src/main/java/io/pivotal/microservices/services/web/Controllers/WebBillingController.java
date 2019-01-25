package io.pivotal.microservices.services.web.Controllers;

import io.pivotal.microservices.services.billing.DTOs.BillEntity;
import io.pivotal.microservices.services.billing.DTOs.BillProductEntity;
import io.pivotal.microservices.services.user.management.DTOs.UserEntity;
import io.pivotal.microservices.services.warehouse.DTOs.ProductEntity;
import io.pivotal.microservices.services.web.Services.WebBillingService;
import io.pivotal.microservices.services.web.Services.WebProductService;
import io.pivotal.microservices.services.web.Services.WebUsersService;
import io.pivotal.microservices.services.web.Views.BillProductView;
import io.pivotal.microservices.services.web.Views.BillView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Client controller, fetches Account info from the microservice via
 * {@link WebBillingService}.
 *
 * @author Paul Chapman
 */
@Controller
public class WebBillingController {

    @Autowired
    protected WebBillingService billingService;
    @Autowired
    protected WebUsersService webUsersService;
    @Autowired
    protected WebProductService webProductService;
    protected Logger logger = Logger.getLogger(WebAccountsController.class
            .getName());

    public WebBillingController(WebBillingService billingService,WebUsersService webUsersService,WebProductService webProductService) {
        this.billingService = billingService;
        this.webUsersService = webUsersService;
        this.webProductService = webProductService;
    }


    @RequestMapping("/bills/{billId}")
    public String byNumber(Model model,
                           @PathVariable("billId") String billId) {

        logger.info("web-service byNumber() invoked: " + billId);

        BillEntity billEntity = billingService.findById(billId);
        logger.info("web-service byNumber() found: " + billEntity);
        UserEntity userEntity = webUsersService.findById( Integer.toString(billEntity.getUserId()));

        List<BillProductEntity> billProductEntities = Arrays.asList(billingService.findBillProductsByBillId(billId));
        List<BillProductView> billProductViews = new ArrayList<>();
        billProductEntities.forEach(billProduct->{
            BillProductView billProductView = new BillProductView();
            billProductView.quantity = billProduct.getQuantity();
            billProductView.id = billProduct.getId();
            ProductEntity productEntity =  webProductService.findById(Integer.toString(billProduct.getProductId()));
            billProductView.productName =productEntity.getName();
            billProductView.productUrl = productEntity.getUrl();
            billProductViews.add(billProductView);
        });


        BillView billView = new BillView();
        billView.id = billEntity.getId();
        billView.username = userEntity.getUserName();
        billView.billProductViews = billProductViews;

        model.addAttribute("billView", billView);
        return "bill";
    }
}
