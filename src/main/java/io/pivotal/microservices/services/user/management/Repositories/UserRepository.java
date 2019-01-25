package io.pivotal.microservices.services.user.management.Repositories;

import io.pivotal.microservices.services.user.management.DTOs.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Find an account with the specified account number.
     *
     * @param userEntityId
     * @return The account if found, null otherwise.
     */
    public UserEntity findById(int userEntityId);

    /**
     * Find accounts whose owner name contains the specified string
     *
     * @param username
     *            Any alphabetic string.
     * @return The list of matching accounts - always non-null, but may be
     *         empty.
     */
    public List<UserEntity> findAllByUserName(String username);
    /**
     * Fetch the number of accounts known to the system.
     *
     * @return The number of accounts.
     */
    @Query("SELECT count(*) from UserEntity ")
    public int countUserEntities();
}