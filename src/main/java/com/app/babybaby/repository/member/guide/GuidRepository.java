package com.app.babybaby.repository.member.guide;

import com.app.babybaby.entity.member.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuidRepository extends JpaRepository<Guide, Long>, GuideQueryDsl {
}
