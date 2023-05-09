package com.app.babybaby.repository.board.event;

import com.app.babybaby.entity.board.event.Event;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.event.QEvent.event;

@RequiredArgsConstructor
public class EventQueryDslImpl implements EventQueryDsl {
    private final JPAQueryFactory query;


    //    이벤트 게시판 목록
    @Override
    public Page<Event> findEventList(Pageable pageable) {
        List<Event> events = query.select(event).from(event).orderBy(event.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(event.count()).from(event).fetchOne();


        return new PageImpl<>(events,pageable,count);
    }

//    일단 이벤트 게시판 상세
    @Override
    public Optional<Event> findEventById(Long id) {
        return Optional.ofNullable(
                query.select(event)
                        .from(event)
                        .join(event.company)
                        .fetchJoin()
                        .where(event.id.eq(id))
                        .fetchOne());
    }

}
