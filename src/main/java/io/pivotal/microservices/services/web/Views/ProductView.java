package io.pivotal.microservices.services.web.Views;

public class ProductView {
    public String name;
    public String description;
    public String url;
    public String cartStatus;

    public ProductView(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
        cartStatus = "far fa-check-circle";
    }
}
