package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.purchase.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>, PurchaseQueryDsl {
}
