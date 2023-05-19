package com.app.babybaby.service.board.parentsBoard;


import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.type.CategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class ParentsBoardServiceTests {
    @Autowired
    ParentsBoardService parentsBoardService;

    @Test
    public void get2BoardsTest(){
        log.info("parents: " + parentsBoardService.find2RecentDesc(CategoryType.AGRICULTURE));
    }



}
