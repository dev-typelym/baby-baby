package com.app.babybaby.repository.user;

import com.app.babybaby.repository.user.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;
}
