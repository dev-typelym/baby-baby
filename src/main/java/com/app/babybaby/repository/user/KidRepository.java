package com.app.babybaby.repository.user;

import com.app.babybaby.entity.user.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidRepository extends JpaRepository<Kid, Long>, KidQueryDsl {
}
