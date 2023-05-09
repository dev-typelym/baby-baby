package com.app.babybaby.repository.member.member;

import com.app.babybaby.entity.member.RandomKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandomKeyRepository extends JpaRepository<RandomKey, Long>, RandomKeyQueryDsl {
}
