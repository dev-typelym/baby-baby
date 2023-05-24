package com.app.babybaby.repository.member.guide;

import com.app.babybaby.entity.member.Guide;
import com.app.babybaby.type.GuideAvailableType;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GuidRepository extends JpaRepository<Guide, Long>, GuideQueryDsl {
        public Guide findFirstByEventIdAndAvailableTypeOrderById(Long eventId, GuideAvailableType availableType);
}
