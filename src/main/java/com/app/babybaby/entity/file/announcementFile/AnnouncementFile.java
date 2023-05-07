package com.app.babybaby.entity.file.announcementFile;

import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.file.File;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"announcement"})
@Table(name = "TBL_ANNOUNCEMENT_FILE")
@PrimaryKeyJoinColumn(name = "ID")
public class AnnouncementFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

}
