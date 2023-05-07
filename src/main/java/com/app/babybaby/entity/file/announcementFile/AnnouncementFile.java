package com.app.babybaby.entity.file.announcementFile;

import com.app.babybaby.entity.board.announcement.Announcement;
import com.app.babybaby.entity.file.File;
import com.app.babybaby.type.FileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"announcement"})
@Table(name = "TBL_ANNOUNCEMENT_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnouncementFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

    public AnnouncementFile(Announcement announcement) {
        this.announcement = announcement;
    }

    public AnnouncementFile(String fileOriginalName, String fileUUID, String filePath, FileType fileStatus, Announcement announcement) {
        super(fileOriginalName, fileUUID, filePath, fileStatus);
        this.announcement = announcement;
    }
}
