package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>, EventQueryDsl {
}
