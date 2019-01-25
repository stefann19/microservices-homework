package io.pivotal.microservices.services.billing.Repositories;

import io.pivotal.microservices.services.billing.DTOs.BillProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillProductRepository extends JpaRepository<BillProductEntity, Long> {
    /**
     * Find an account with the specified account number.
     *
     * @param billProductEntityId
     * @return The account if found, null otherwise.
     */
    public BillProductEntity findById(int billProductEntityId);

    /**
     * Find accounts whose owner name contains the specified string
     *
     * @param billEntityId
     *            Any alphabetic string.
     * @return The list of matching accounts - always non-null, but may be
     *         empty.
     */
    public List<BillProductEntity> findBillProductEntitiesByBillId(int billEntityId);
    /**
     * Fetch the number of accounts known to the system.
     *
     * @return The number of accounts.
     */
    @Query("SELECT count(*) from BillProductEntity")
    public int countBillProductEntities();
}
