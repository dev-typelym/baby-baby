package com.app.babybaby.repository.member.kid;

import com.app.babybaby.entity.member.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidRepository extends JpaRepository<Kid, Long>, KidQueryDsl {
}
