package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ParentsBoardRepositoryTests {
    @Autowired
    ParentsBoardRepository parentsBoardRepository;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

//    @Test
//    public void saveTest(){
//        ParentsBoard parentsBoard = new ParentsBoard("제목1","내용1","1L", "1L", "","");
//        parentsBoardRepository.save(parentsBoard);
//    }

}
