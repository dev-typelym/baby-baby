package com.app.babybaby.domain.boardDTO.nowKidsDTO;


import com.app.babybaby.domain.fileDTO.nowKidsFileDTO.NowKidsFileDTO;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.*;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
public class NowKidsDTO {
    private Long id;
    
    /* 체험학습 정보 */
    private String boardTitle;
    private String boardContent;
    private Long eventRecruitCount;
    private Address eventLocation;
    private CategoryType category;

//    게시글을 올린 통솔자의 정보 조회
    private String memberNickname;
    private String memberProfileOriginalName;
    private String memberProfileUUID;
    private String memberProfilePath;
    private LocalDateTime memberRegisterDate;
    private MemberType memberType;
    private AcceptanceType memberGuideStatus;
    private SleepType memberSleep;
    private GuideType memberGuideType;

//    해당 이벤트의 파일을 조회
    private List<NowKidsFile> nowKidsFiles;

}
