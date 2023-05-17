package com.app.babybaby.entity.board.parentsBoard;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.reply.parentsBoardReply.ParentsBoardReply;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"event", "member", "parentsBoardReplies"})
@Table(name = "TBL_PARENTS_BOARD")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentsBoard extends BoardInfo {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentsBoard", cascade = CascadeType.REMOVE)
    private List<ParentsBoardFile> parentsBoardFiles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentsBoard")
    private List<ParentsBoardReply> parentsBoardReplies;

    private String representFileUUID;
    private String representFileOrginName;
    private String representFilePath;


    public ParentsBoard(Event event, Member member, List<ParentsBoardReply> parentsBoardReplies) {
        this.event = event;
        this.member = member;
        this.parentsBoardReplies = parentsBoardReplies;
    }


    public ParentsBoard(String boardTitle, String boardContent, Event event, Member member, List<ParentsBoardReply> parentsBoardReplies) {
        super(boardTitle, boardContent);
        this.event = event;
        this.member = member;
        this.parentsBoardReplies = parentsBoardReplies;
    }

//    부모님 마당 파일 업로드 용 빌더
    @Builder
    public ParentsBoard(Long id, String boardTitle, String boardContent, List<ParentsBoardFile> parentsBoardFiles, Event event, Member member, List<ParentsBoardReply> parentsBoardReplies, String representFileUUID, String representFileOrginName, String representFilePath) {
        super(id, boardTitle, boardContent);
        this.representFileUUID = representFileUUID;
        this.representFileOrginName = representFileOrginName;
        this.representFilePath = representFilePath;
    }

}
