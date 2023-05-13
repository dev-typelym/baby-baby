package com.app.babybaby.service.file.nowKidsFile;

import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;

import java.util.List;

public interface NowKidsFileService {

    public Event getBoardInfoByEventId(Long eventId);

    public void saveAllFiles(List<NowKidsFileDTO> files, Long nowKidsId);

    default NowKidsFile toNowKidsFileEntity(NowKidsFileDTO nowKidsFileDTO){
        return NowKidsFile.builder()
                .fileOriginalName(nowKidsFileDTO.getFileOriginalName())
                .filePath(nowKidsFileDTO.getFilePath())
                .fileUUID(nowKidsFileDTO.getFileUUID())
                .fileStatus(nowKidsFileDTO.getFileType())
                .build();
    }

}
