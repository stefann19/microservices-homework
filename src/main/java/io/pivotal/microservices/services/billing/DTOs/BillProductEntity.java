package io.pivotal.microservices.services.billing.DTOs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bill_product", schema = "billing", catalog = "")
public class BillProductEntity {
    private Integer id;
    private Integer billId;
    private Integer productId;
    private Double quantity;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bill_id", nullable = true)
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @Basic
    @Column(name = "product_id", nullable = true)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "quantity", nullable = true, precision = 0)
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillProductEntity that = (BillProductEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(billId, that.billId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billId, productId, quantity);
    }
}
