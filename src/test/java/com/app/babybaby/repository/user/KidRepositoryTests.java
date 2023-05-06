package com.app.babybaby.repository.user;

import com.app.babybaby.repository.user.kid.KidRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class KidRepositoryTests {
    @Autowired
    KidRepository kidRepository;
}
