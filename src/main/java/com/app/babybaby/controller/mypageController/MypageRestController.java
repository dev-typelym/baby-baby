package com.app.babybaby.controller.mypageController;


import com.app.babybaby.domain.memberDTO.FollowDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypages/*")
public class MypageRestController {
    
//    나를 팔로우 하는 사람들
    @PostMapping
    public FollowDTO getFollowing(){
        Long sessionId = 2L;
        return null;
    }
}
