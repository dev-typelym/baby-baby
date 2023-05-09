package com.app.babybaby.repository.user.user;

import com.app.babybaby.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long>, UserQueryDsl {
}
