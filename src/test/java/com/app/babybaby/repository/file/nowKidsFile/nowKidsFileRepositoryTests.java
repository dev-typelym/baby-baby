package com.app.babybaby.repository.file.nowKidsFile;

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
public class nowKidsFileRepositoryTests {
    @Autowired
    NowKidsFileRepository nowKidsFileRepository;



    @Test
    public void findAllNowKidsFilesWithNowKidsIdTest(){
        log.info(nowKidsFileRepository.findAllNowKidsFilesWithNowKidsId(455L).toString());
    }
}
