package com.app.babybaby.repository.user;

import com.app.babybaby.repository.user.follow.FollowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class FollowRepositoryTests {
    @Autowired
    FollowRepository followRepository;
}
