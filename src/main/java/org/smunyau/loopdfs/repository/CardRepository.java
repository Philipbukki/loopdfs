package org.smunyau.loopdfs.repository;

import org.smunyau.loopdfs.entity.Account;
import org.smunyau.loopdfs.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
}
