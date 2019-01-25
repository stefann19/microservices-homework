package io.pivotal.microservices.services.warehouse.Repositories;

import io.pivotal.microservices.services.warehouse.DTOs.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    /**
     * Find an account with the specified account number.
     *
     * @param productEntityId
     * @return The account if found, null otherwise.
     */
    public ProductEntity findById(int productEntityId);

    /**
     * Find accounts whose owner name contains the specified string
     *
     * @param productName
     *            Any alphabetic string.
     * @return The list of matching accounts - always non-null, but may be
     *         empty.
     */
    public List<ProductEntity> findAllByName(String productName);
    /**
     * Fetch the number of accounts known to the system.
     *
     * @return The number of accounts.
     */
    @Query("SELECT count(*) from ProductEntity")
    public int countProductEntities();
}