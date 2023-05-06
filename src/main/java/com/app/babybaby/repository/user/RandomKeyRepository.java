package com.app.babybaby.repository.user;

import com.app.babybaby.entity.user.RandomKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandomKeyRepository extends JpaRepository<RandomKey, Long>, RandomKeyQueryDsl {
}
