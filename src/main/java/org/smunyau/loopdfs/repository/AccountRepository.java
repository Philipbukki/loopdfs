package org.smunyau.loopdfs.repository;
import org.smunyau.loopdfs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
