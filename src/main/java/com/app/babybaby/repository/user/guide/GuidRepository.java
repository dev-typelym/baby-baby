package com.app.babybaby.repository.user.guide;

import com.app.babybaby.entity.user.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuidRepository extends JpaRepository<Guide, Long>, GuideQueryDsl {
}
