package com.app.babybaby.entity.board.parentsBoard;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import com.app.babybaby.entity.reply.ParentsBoardReply;
import com.app.babybaby.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"event", "user"})
@Table(name = "TBL_PARENTS_BOARD")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentsBoard extends BoardInfo {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentsBoard", cascade = CascadeType.REMOVE)
    private List<ParentsBoardFile> parentsBoardFiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentsBoard")
    private List<ParentsBoardReply> parentsBoardReplies;

    public ParentsBoard(Event event, User user, List<ParentsBoardReply> parentsBoardReplies) {
        this.event = event;
        this.user = user;
        this.parentsBoardReplies = parentsBoardReplies;
    }


    public ParentsBoard(String boardTitle, String boardContent, Event event, User user, List<ParentsBoardReply> parentsBoardReplies) {
        super(boardTitle, boardContent);
        this.event = event;
        this.user = user;
        this.parentsBoardReplies = parentsBoardReplies;
    }
}
