package io.pivotal.microservices.services.billing.Controllers;


import io.pivotal.microservices.exceptions.AccountNotFoundException;
import io.pivotal.microservices.services.billing.DTOs.BillEntity;
import io.pivotal.microservices.services.billing.DTOs.BillProductEntity;
import io.pivotal.microservices.services.billing.Repositories.BillProductRepository;
import io.pivotal.microservices.services.billing.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class BillController {

    protected Logger logger = Logger.getLogger(BillController.class
            .getName());
    protected BillRepository billRepository;
    protected BillProductRepository billProductRepository;

    /**
     * Create an instance plugging in the respository of Accounts.
     *
     * @param billRepository
     *            An account repository implementation.
     */
    @Autowired
    public BillController(BillRepository billRepository, BillProductRepository billProductRepository) {
        this.billRepository = billRepository;
        this.billProductRepository = billProductRepository;
        logger.info("BillRepository says system has "
                + billRepository.countBillEntities() + " bills");
    }


    /**
     * Fetch an account with the specified account number.
     *
     * @param billId
     *            A numeric, 9 digit account number.
     * @return The account if found.
     * @throws AccountNotFoundException
     *             If the number is not recognised.
     */
    @RequestMapping("/bills/{billId}")
    public BillEntity byNumber(@PathVariable("billId") String billId) {

        logger.info("billing-service byNumber() invoked: " + billId);
        BillEntity billEntity = billRepository.findById( Integer.parseInt(billId));

        logger.info("billing-service byNumber() found: " + billEntity);

        if (billEntity == null)
            throw new AccountNotFoundException(billId);
        else {
            return billEntity;
        }
    }
    @RequestMapping("/billProductsByBillId/{billId}")
    public List<BillProductEntity> billProductsByBillId(@PathVariable("billId") String billId) {

        logger.info("billing-service byNumber() invoked: " + billId);
        List<BillProductEntity> billProductEntities = billProductRepository.findBillProductEntitiesByBillId( Integer.parseInt(billId));

        logger.info("billing-service byNumber() found: " + billProductEntities);

        if (billProductEntities == null)
            throw new AccountNotFoundException(billId);
        else {
            return billProductEntities;
        }
    }
}
