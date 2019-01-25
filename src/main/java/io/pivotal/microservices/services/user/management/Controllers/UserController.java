package io.pivotal.microservices.services.user.management.Controllers;

import io.pivotal.microservices.exceptions.AccountNotFoundException;
import io.pivotal.microservices.services.user.management.DTOs.UserEntity;
import io.pivotal.microservices.services.user.management.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class UserController {

    protected Logger logger = Logger.getLogger(UserController.class
            .getName());
    protected UserRepository userRepository;

    /**
     * Create an instance plugging in the respository of Accounts.
     *
     * @param userRepository
     *            An account repository implementation.
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

        logger.info("UserRepository says system has "
                + userRepository.countUserEntities() + " users");
    }

    /**
     * Fetch an account with the specified account number.
     *
     * @param userId
     *            A numeric, 9 digit account number.
     * @return The account if found.
     * @throws AccountNotFoundException
     *             If the number is not recognised.
     */
    @RequestMapping("/users/{userId}")
    public UserEntity byNumber(@PathVariable("userId") String userId) {

        logger.info("user.management byNumber() invoked: " + userId);
        UserEntity userEntity = userRepository.findById( Integer.parseInt(userId));

        logger.info("user.management-service byNumber() found: " + userEntity);

        if (userEntity == null)
            throw new AccountNotFoundException(userId);
        else {
            return userEntity;
        }
    }
}
