package io.pivotal.microservices.services.billing.Repositories;

import java.util.List;

import io.pivotal.microservices.services.billing.DTOs.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<BillEntity, Long> {
    /**
     * Find an account with the specified account number.
     *
     * @param billEntityId
     * @return The account if found, null otherwise.
     */
    public BillEntity findById(int billEntityId);

    /**
     * Find accounts whose owner name contains the specified string
     *
     * @param billEntityId
     *            Any alphabetic string.
     * @return The list of matching accounts - always non-null, but may be
     *         empty.
     */
    public List<BillEntity> findAllByUserId(int billEntityId);
    /**
     * Fetch the number of accounts known to the system.
     *
     * @return The number of accounts.
     */
    @Query("SELECT count(*) from BillEntity")
    public int countBillEntities();
}
