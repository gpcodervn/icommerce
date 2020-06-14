package com.icommerce.audit.repository;

import com.icommerce.audit.entity.CustomerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerActivityRepository extends JpaRepository<CustomerActivity, Long> {
}
