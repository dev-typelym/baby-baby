package com.app.babybaby.repository.like.eventLike;

import com.app.babybaby.entity.board.event.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class EventLikeRepositoryTests {
    @Autowired
    EventLikeRepository eventLikeRepository;

//   내가 누른 좋아요 조회
//    @Test
//    public void findAllByMemberLikesWithPaging_QueryDSL_Test() {
//        Slice<Event> eventLikes = eventLikeRepository.findAllByMemberLikesWithPaging_QueryDSL(PageRequest.of(0, 10), 1L);
//        eventLikes.get().map(Event::toString).forEach(log::info);
//    }
}
