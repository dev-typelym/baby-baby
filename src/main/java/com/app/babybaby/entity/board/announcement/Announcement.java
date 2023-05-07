package com.app.babybaby.entity.board.announcement;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = "admin")
@Table(name = "TBL_ANNOUNCEMENT")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Announcement extends BoardInfo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID")
    private User admin;

    public Announcement(User admin) {
        this.admin = admin;
    }

    public Announcement(String boardTitle, String boardContent, User admin) {
        super(boardTitle, boardContent);
        this.admin = admin;
    }
}
