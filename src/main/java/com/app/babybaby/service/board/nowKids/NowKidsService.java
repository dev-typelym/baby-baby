package com.app.babybaby.service.board.nowKids;

import com.app.babybaby.domain.boardDTO.nowKidsDTO.NowKidsDTO;
import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.service.file.nowKidsFile.NowKidsFileService;
import com.querydsl.core.Tuple;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
//이름 바꾸지 말기
public interface NowKidsService {

    public Page<NowKidsDTO> getAllInfoForListDesc(int pageNum, int pageSize, Long sessionId);

    public List<NowKidsDTO>  getBoardAndCalendarByGeneralGuideId(Long sessionId);

    public List<MemberDTO> find8AuthorDesc();

    default com.app.babybaby.entity.board.nowKids.NowKids toNowKidsEntity(NowKidsDTO nowKidsDTO){
        return com.app.babybaby.entity.board.nowKids.NowKids.builder()
                .id(nowKidsDTO.getNowKidsId())
                .build();
    }



    default Member toMemberEntity(NowKidsDTO nowKidsDTO){
        return Member.joinMemberBuilder()
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
                .category(nowKidsDTO.getCategory())
                .build();
    }

//    default List<NowKidsFile> toNowKidsFileEntity(NowKidsDTO nowKidsDTO){
//        return nowKidsDTO.getNowKidsFiles().stream()
//                .map(nowKidsFile -> NowKidsFile.builder()
//                        .fileOriginalName(nowKidsFile.getFileOriginalName())
//                        .fileUUID(nowKidsFile.getFileUUID())
//                        .filePath(nowKidsFile.getFilePath())
//                        .fileStatus(nowKidsFile.getFileStatus())
//                        .build())
//                .collect(Collectors.toList());
//    }

//    default List<Kid> toKidEntity(NowKidsDTO nowKidsDTO){
//        return nowKidsDTO.getKids().stream()
//                .map(kid -> Kid.builder()
//                .kidAge(kid.getKidAge())
//                        .kidGender(kid.getKidGender())
//                        .kidName(kid.getKidName())
//                        .kidAge(kid.getKidAge())
//                        .build()
//                ).collect(Collectors.toList());
//    }

    default NowKidsDTO toNowKidsDTO(com.app.babybaby.entity.board.nowKids.NowKids nowKids) {
        return NowKidsDTO.builder()
                .eventAddress(nowKids.getEvent().getEventLocation().getAddress())
                .eventAddressDetail(nowKids.getEvent().getEventLocation().getAddressDetail())
                .eventAddressSubDetail(nowKids.getEvent().getEventLocation().getAddressSubDetail())
                .eventPostCode(nowKids.getEvent().getEventLocation().getPostcode())
                .eventStartDate(nowKids.getEvent().getCalendar().getStartDate())
                .eventEndDate(nowKids.getEvent().getCalendar().getEndDate())
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
                .category(nowKids.getEvent().getCategory())
                .files(nowKids.getNowKidsFile()
                                .stream().map(nowKidsFile -> NowKidsFileDTO.builder()
                                        .fileOriginalName(nowKidsFile.getFileOriginalName())
                                        .fileUUID(nowKidsFile.getFileUUID())
                                        .filePath(nowKidsFile.getFilePath())
                                        .build())
                                .collect(Collectors.toList())
                         )
                .build();
    }







}

