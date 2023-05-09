package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.babybaby.entity.board.event.QEvent.event;

@RequiredArgsConstructor
public class EventQueryDslImpl implements EventQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public List<Event> findEventList(Pageable pageable) {
        return query.select(event).from(event).orderBy(event.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }

}
