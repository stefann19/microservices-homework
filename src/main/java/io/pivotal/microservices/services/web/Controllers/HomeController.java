package io.pivotal.microservices.services.web.Controllers;

import io.pivotal.microservices.services.warehouse.DTOs.ProductEntity;
import io.pivotal.microservices.services.web.Services.WebAccountsService;
import io.pivotal.microservices.services.web.Services.WebProductService;
import io.pivotal.microservices.services.web.Views.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Home page controller.
 * 
 * @author Paul Chapman
 */
@Controller
public class HomeController {

	protected Logger logger = Logger.getLogger(HomeController.class
			.getName());

	@Autowired
	protected WebProductService webProductService;

	public HomeController(WebProductService webProductService){this.webProductService = webProductService;}

	@RequestMapping("/")
	public String home(Model model) {
		List<ProductEntity> productEntities = Arrays.asList(webProductService.getAllProducts());
		logger.info("warehouse-service getAllProducts()");

		List<ProductView> productViews = productEntities.stream().map(productEntity -> new ProductView(productEntity.getName(),productEntity.getDescription(),productEntity.getUrl())).collect(Collectors.toList());
		model.addAttribute("products", productViews);
		return "index";
	}

}
