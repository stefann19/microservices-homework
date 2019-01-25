package io.pivotal.microservices.services.web;

import io.pivotal.microservices.services.web.Controllers.HomeController;
import io.pivotal.microservices.services.web.Controllers.WebAccountsController;
import io.pivotal.microservices.services.web.Controllers.WebBillingController;
import io.pivotal.microservices.services.web.Services.WebBillingService;
import io.pivotal.microservices.services.web.Services.WebAccountsService;
import io.pivotal.microservices.services.web.Services.WebProductService;
import io.pivotal.microservices.services.web.Services.WebUsersService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * Account-Service. Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class WebServer {

	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";
	public static final String BILLING_SERVICE_URL = "http://BILLING-SERVICE";
	public static final String USER_MANAGEMENT_SERVICE_URL = "http://USER.MANAGEMENT-SERVICE";
	public static final String WAREHOUSE_SERVICE_URL = "http://WAREHOUSE-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
	}

	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton" 
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public WebAccountsService accountsService() {
		return new WebAccountsService(ACCOUNTS_SERVICE_URL);
	}
	@Bean
	public WebBillingService billingService() {
		return new WebBillingService(BILLING_SERVICE_URL);
	}
	@Bean
	public WebUsersService usersService() {
		return new WebUsersService(USER_MANAGEMENT_SERVICE_URL);
	}
	@Bean
	public WebProductService productService() {
		return new WebProductService(WAREHOUSE_SERVICE_URL);
	}
	/**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 * 
	 * @return
	 */
	@Bean
	public WebAccountsController accountsController() {
		return new WebAccountsController(accountsService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}

	@Bean
	public WebBillingController billingController(){return new WebBillingController(billingService(),usersService(),productService());}
}
