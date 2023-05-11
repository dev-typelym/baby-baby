package com.app.babybaby.service.boardService.nowKidsService;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
//이름 바꾸지 말기
public interface NowKidsServices {

    public Page<NowKidsDTO> getAllInfoForListDesc(int pageNum, int pageSize, Long sessionId);
    public List<Event> getBoardAndCalendarByGeneralGuideId(Long sessionId);

    default NowKids toNowKidsEntity(NowKidsDTO nowKidsDTO){
        return NowKids.builder()
                .id(nowKidsDTO.getNowKidsId())
                .build();
    }



    default Member toMemberEntity(NowKidsDTO nowKidsDTO){
        return Member.builder()
                .id(nowKidsDTO.getMemberId())
                .memberProfileOriginalName(nowKidsDTO.getMemberProfileOriginalName())
                .memberProfilePath(nowKidsDTO.getMemberProfilePath())
                .memberProfileUUID(nowKidsDTO.getMemberProfileUUID())
                .memberNickname(nowKidsDTO.getMemberNickname())
                .memberRegisterDate(nowKidsDTO.getMemberRegisterDate())
                .memberType(nowKidsDTO.getMemberType())
                .build();

    }

    default Event toEventEntity(NowKidsDTO nowKidsDTO){
        return Event.builder()
                .id(nowKidsDTO.getEventId())
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

    default List<Kid> toKidEntity(NowKidsDTO nowKidsDTO){
        return nowKidsDTO.getKids().stream()
                .map(kid -> Kid.builder()
                .kidAge(kid.getKidAge())
                        .kidGender(kid.getKidGender())
                        .kidName(kid.getKidName())
                        .kidAge(kid.getKidAge())
                        .build()
                ).collect(Collectors.toList());
    }


    default NowKidsDTO toNowKidsDTO(NowKids nowKids) {
        return NowKidsDTO.builder()
                .calendar(nowKids.getEvent().getCalendar())
                .uploadTime(nowKids.getUpdateDate())
                .eventUpdateTime(nowKids.getUpdateDate())
                .eventUploadTIme(nowKids.getRegisterDate())
                .nowKidsId(nowKids.getId())
                .eventId(nowKids.getEvent().getId())
                .memberId(nowKids.getGuide().getId())
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

