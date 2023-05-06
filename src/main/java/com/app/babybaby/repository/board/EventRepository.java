package com.app.babybaby.repository.board;

import com.app.babybaby.entity.board.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>, EventQueryDsl {
}
