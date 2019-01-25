package io.pivotal.microservices.services.web.Views;

import java.util.List;

public class BillView {
    public int id;
    public String username;
    public int numberOfProducts;
    public float value;
    public List<BillProductView> billProductViews;
}
