package com.app.babybaby.entity.board.announcement;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.file.announcementFile.AnnouncementFile;
import com.app.babybaby.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true, exclude = "admin")
@Table(name = "TBL_ANNOUNCEMENT")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Announcement extends BoardInfo {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
    private List<AnnouncementFile> announcementFiles  = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID")
    private Member admin;

    public Announcement(Member admin) {
        this.admin = admin;
    }

    public Announcement(String boardTitle, String boardContent, Member admin) {
        super(boardTitle, boardContent);
        this.admin = admin;
    }
}
