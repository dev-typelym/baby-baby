package com.app.babybaby.repository.reply.parentsBoardReply;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ParentsBoardServiceReplyServiceRepositoryTests {

    @Autowired
    private ParentsBoardReplyRepository parentsBoardReplyRepository;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void ParentsBoardReplyListTest() {
        Pageable pageable = PageRequest.of(0, 10);
        log.info(parentsBoardReplyRepository.findAllReplyByBoardIdWithPaging(pageable, 541L).getContent().toString());
    }

}
