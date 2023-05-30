package com.app.babybaby.controller.fileController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviewFiles/*")
@Slf4j
public class ReviewFileController {
    //    파일 업로드 하면 Ajax로 들어옴
    @PostMapping("upload")
    @ResponseBody
    public List<String> uploadP (@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        List<String> uuids = new ArrayList<>();
        String path = "/C:/upload/Review/" + getPath();
        log.info("path는 " + path);
        File file = new File(path);
        if(!file.exists()) {file.mkdirs();}
        for(int i=0; i < multipartFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
        }
        return uuids;
    }

    //  파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("/C:/upload", fileName));
    }



    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
