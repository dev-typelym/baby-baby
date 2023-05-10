package com.app.babybaby.service.boardService.nowKids;


import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.repository.board.nowKids.NowKidsRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class NowKidsServiceTests {

}
