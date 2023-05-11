package com.app.babybaby.domain.boardDTO.nowKidsDTO;

import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.like.nowKidsLike.NowKidsLike;
import com.app.babybaby.entity.member.Kid;
import com.app.babybaby.type.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter @Setter @ToString(exclude = {"nowKidsLikes", "nowKidsFiles", "kids"})
@NoArgsConstructor
public class NowKidsDTO {
    /* 이벤트 id */
    private Long nowKidsId;

    /* 체험학습 정보 */
    private Long eventId;
    private String boardTitle;
    private String boardContent;
    private Long eventRecruitCount;
    private Address eventLocation;
    private CategoryType category;
    private Calendar calendar;
    private LocalDateTime eventUploadTIme;
    private LocalDateTime eventUpdateTime;

    //    게시글을 올린 통솔자의 정보 조회
    private Long memberId;
    private String memberNickname;
    private String memberProfileOriginalName;
    private String memberProfileUUID;
    private String memberProfilePath;
    private LocalDateTime memberRegisterDate;
    private MemberType memberType;
    private AcceptanceType memberGuideStatus;
    private SleepType memberSleep;
    private GuideType memberGuideType;

    private Boolean isRecent;
    private LocalDateTime uploadTime;


    //    좋아요 가져오기
    private List<NowKidsLike> nowKidsLikes;

    //    해당 이벤트의 파일을 조회
    private List<NowKidsFile> nowKidsFiles;

    //    통솔중인 아이들 조회
    private List<Kid> kids;

    @Builder
    public NowKidsDTO(Long nowKidsId, Long eventId, String boardTitle, String boardContent, Long eventRecruitCount, Address eventLocation, CategoryType category, Calendar calendar, LocalDateTime eventUploadTIme, LocalDateTime eventUpdateTime, Long memberId, String memberNickname, String memberProfileOriginalName, String memberProfileUUID, String memberProfilePath, LocalDateTime memberRegisterDate, MemberType memberType, AcceptanceType memberGuideStatus, SleepType memberSleep, GuideType memberGuideType, Boolean isRecent, LocalDateTime uploadTime, List<NowKidsLike> nowKidsLikes, List<NowKidsFile> nowKidsFiles, List<Kid> kids) {
        this.nowKidsId = nowKidsId;
        this.eventId = eventId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.category = category;
        this.calendar = calendar;
        this.eventUploadTIme = eventUploadTIme;
        this.eventUpdateTime = eventUpdateTime;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.memberProfileOriginalName = memberProfileOriginalName;
        this.memberProfileUUID = memberProfileUUID;
        this.memberProfilePath = memberProfilePath;
        this.memberRegisterDate = memberRegisterDate;
        this.memberType = memberType;
        this.memberGuideStatus = memberGuideStatus;
        this.memberSleep = memberSleep;
        this.memberGuideType = memberGuideType;
        this.isRecent = isRecent;
        this.uploadTime = uploadTime;
        this.nowKidsLikes = nowKidsLikes;
        this.nowKidsFiles = nowKidsFiles;
        this.kids = kids;
    }
}
