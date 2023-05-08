package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventQueryDsl {
    public List<Event>findEventList(Pageable pageable);
}
