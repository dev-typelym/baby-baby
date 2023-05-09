package com.app.babybaby.repository.member.crew;


import com.app.babybaby.entity.member.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Long>, CrewQueryDsl{
}
