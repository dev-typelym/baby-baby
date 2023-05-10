package com.app.babybaby.entity.board.nowKids;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.app.babybaby.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"event", "guide"})
@Table(name = "TBL_NOW_KIDS")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NowKids extends BoardInfo {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GUIDE_ID")
    private Member guide;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nowKids")
    private List<NowKidsFile> nowKidsFile;

    public NowKids(Event event, Member guide) {
        this.event = event;
        this.guide = guide;
    }
    @Builder
    public NowKids(Long id, String boardTitle, String boardContent, Event event, Member guide) {
        super(id, boardTitle, boardContent);
        this.event = event;
        this.guide = guide;
    }

    public NowKids(String boardTitle, String boardContent, Event event, Member guide) {
        super(boardTitle, boardContent);
        this.event = event;
        this.guide = guide;
    }
}
