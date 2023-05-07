package com.app.babybaby.entity.file;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.type.FileType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"announcement", "review", "nowKids", "parentsBoard", "event"})
@Table(name = "TBL_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class File {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String fileOriginalName;
    @NotNull
    private String fileUUID;
    @NotNull
    private String filePath;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FileType fileStatus;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ANNOUNCEMENT_ID")
//    private Announcement announcement;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "REVIEW_ID")
//    private Review review;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "NOW_KIDS_ID")
//    private NowKids nowKids;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PARENTS_BOARD_ID")
//    private ParentsBoard parentsBoard;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "EVENT_ID")
//    private Event event;

}
