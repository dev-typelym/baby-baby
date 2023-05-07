package com.app.babybaby.repository.board;

import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ParentsBoardRepositoryTests {

    @Autowired
    ParentsBoardRepository parentsBoardRepository;


    @Test
    public void saveTest(){
    }

    @Test
    public void findAllTest(){
        log.info(parentsBoardRepository.findAll().toString());
    }

    @Test
    public void findByIdTest(){

    }
}
