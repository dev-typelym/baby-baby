package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.type.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>, EventQueryDsl {
    public Event findTop1ByCategoryOrderByRegisterDateDesc(CategoryType category);
}
