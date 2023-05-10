package com.app.babybaby.service.boardService.NowKids;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public interface NowKidsService {
    public List<NowKidsDTO> getAllInfoForList();

    default NowKids toNowKidsEntity(NowKidsDTO nowKidsDTO){
        return NowKids.builder()
                .id(nowKidsDTO.getId())
                .build();
    }


    default Member toMemberEntity(NowKidsDTO nowKidsDTO){
        return Member.builder()
                .memberProfileOriginalName(nowKidsDTO.getMemberProfileOriginalName())
                .memberProfilePath(nowKidsDTO.getMemberProfilePath())
                .memberProfileUUID(nowKidsDTO.getMemberProfileUUID())
                .memberNickname(nowKidsDTO.getMemberNickname())
                .memberRegisterDate(nowKidsDTO.getMemberRegisterDate())
                .memberType(nowKidsDTO.getMemberType())
                .memberGuideStatus(nowKidsDTO.getMemberGuideStatus())
                .memberSleep(nowKidsDTO.getMemberSleep())
                .memberGuideType(nowKidsDTO.getMemberGuideType())
                .build();

    }

    default Event toEventEntity(NowKidsDTO nowKidsDTO){
        return Event.builder()
                .boardTitle(nowKidsDTO.getBoardTitle())
                .boardContent(nowKidsDTO.getBoardContent())
                .eventRecruitCount(nowKidsDTO.getEventRecruitCount())
                .eventLocation(nowKidsDTO.getEventLocation())
                .category(nowKidsDTO.getCategory())
                .build();
    }

    default List<NowKidsFile> toNowKidsFileEntity(NowKidsDTO nowKidsDTO){
        return nowKidsDTO.getNowKidsFiles().stream()
                .map(nowKidsFile -> NowKidsFile.builder()
                        .fileOriginalName(nowKidsFile.getFileOriginalName())
                        .fileUUID(nowKidsFile.getFileUUID())
                        .filePath(nowKidsFile.getFilePath())
                        .fileStatus(nowKidsFile.getFileStatus())
                        .build())
                .collect(Collectors.toList());
    }


    default NowKidsDTO toNowKidsDTO(NowKids nowKids) {
        return NowKidsDTO.builder()
                .id(nowKids.getId())
                .boardTitle(nowKids.getEvent().getBoardTitle())
                .memberProfileOriginalName(nowKids.getGuide().getMemberProfileOriginalName())
                .memberProfilePath(nowKids.getGuide().getMemberProfilePath())
                .memberProfileUUID(nowKids.getGuide().getMemberProfileUUID())
                .memberNickname(nowKids.getGuide().getMemberNickname())
                .memberType(nowKids.getGuide().getMemberType())
                .memberGuideStatus(nowKids.getGuide().getMemberGuideStatus())
                .memberSleep(nowKids.getGuide().getMemberSleep())
                .memberGuideType(nowKids.getGuide().getMemberGuideType())
                .eventRecruitCount(nowKids.getEvent().getEventRecruitCount())
                .eventLocation(nowKids.getEvent().getEventLocation())
                .category(nowKids.getEvent().getCategory())
                .nowKidsFiles(nowKids.getNowKidsFile()
                        .stream()
                        .map(nowKidsFile -> NowKidsFile.builder()
                                .fileOriginalName(nowKidsFile.getFileOriginalName())
                                .fileUUID(nowKidsFile.getFileUUID())
                                .filePath(nowKidsFile.getFilePath())
                                .fileStatus(nowKidsFile.getFileStatus())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }






}

