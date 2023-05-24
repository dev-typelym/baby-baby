package com.app.babybaby.repository.member.crew;


import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Crew;
import com.app.babybaby.entity.member.Guide;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CrewRepository extends JpaRepository<Crew, Long>, CrewQueryDsl{
    @Query("SELECT COUNT(c) FROM Crew c WHERE c.guide.id = :guideId")
    Long countCrewsByGuideId(@Param("guideId") Long guideId);
}
