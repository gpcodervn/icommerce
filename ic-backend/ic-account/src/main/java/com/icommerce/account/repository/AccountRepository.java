package com.icommerce.account.repository;

import com.icommerce.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select e from #{#entityName} e where e.username=?1")
    Optional<Account> findByUsername(String username);
}
