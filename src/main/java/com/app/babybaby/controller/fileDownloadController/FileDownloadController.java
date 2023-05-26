package com.app.babybaby.controller.fileDownloadController;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pdf/*")
public class FileDownloadController {
    @GetMapping("file/download/{filePath}/{fileUUID}/{fileOriginalName}")

    @ResponseBody
    public void download(@PathVariable String filePath, @PathVariable String fileUUID,@     PathVariable  String fileOriginalName, HttpServletResponse response) throws IOException {

        String path = "C:/upload/Member/Profile/2023/05/26/b3b724f7-c739-4fe9-8289-91f146d151e8_입사지원서-임의택.pdf";
        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode( "fileDownload.png", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}