package com.app.babybaby.repository.like;

import com.app.babybaby.repository.board.NowKidsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class NowKidsRepositoryTests {
    @Autowired
    NowKidsRepository nowKidsRepository;
}
