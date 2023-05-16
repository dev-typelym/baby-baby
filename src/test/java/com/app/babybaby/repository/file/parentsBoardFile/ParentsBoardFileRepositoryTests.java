package com.app.babybaby.repository.file.parentsBoardFile;

import com.app.babybaby.domain.fileDTO.parentsBoardFileDTO.ParentsBoardFileDTO;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.repository.board.parentsBoard.ParentsBoardRepository;
import com.app.babybaby.type.FileType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ParentsBoardFileRepositoryTests {

    @Autowired
    private ParentsBoardFileRepository parentsBoardFileRepository;

    @Autowired
    private ParentsBoardRepository parentsBoardRepository;

    @Transactional
    @Test
    public void parentsBoardFileSaveTest() {
//
//        // 부모 게시물 가져오기 (예: ID가 541인 게시물)
        ParentsBoard parentsBoard = parentsBoardRepository.findById(9L).get();
//
//        // 파일 정보 설정
        FileType fileStatus = FileType.MAIN;
//
//        // ParentsBoardFile 생성
        ParentsBoardFile parentsBoardFile = new ParentsBoardFile("공지사항2.png", "4a630d3e-80de-41a5-b9ed-a281b2c88035", "2023/05/13", fileStatus);
//
//        // ParentsBoardFile 저장
        parentsBoardFileRepository.save(parentsBoardFile);
//
    }


}
