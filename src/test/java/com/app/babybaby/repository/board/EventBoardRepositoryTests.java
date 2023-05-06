package com.app.babybaby.repository.board;

import com.app.babybaby.repository.board.event.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EventBoardRepositoryTests {
    @Autowired
    EventRepository eventRepository;
}
