package com.app.babybaby.service.board.event;


import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.search.board.parentsBoard.EventBoardSearch;
import com.app.babybaby.type.CategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class EventServiceTests {

    @Autowired
    EventService eventService;


    @Test
    public void findTest(){
        Pageable pageable = PageRequest.of(0, 10);
        EventBoardSearch eventBoardSearch = new EventBoardSearch();
        eventBoardSearch.setBoardTitle("검색조건");
        eventService.findEventListWithPaging(eventBoardSearch,pageable).stream().map(EventDTO::getBoardTitle).forEach(log::info);
//        eventService.findEventListWithPaging(eventBoardSearch, pageable);
    }


}
