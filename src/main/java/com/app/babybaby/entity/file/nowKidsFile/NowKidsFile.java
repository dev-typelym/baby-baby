package com.app.babybaby.entity.file.nowKidsFile;

import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.File;
import com.app.babybaby.type.FileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"nowKids"})
@Table(name = "TBL_NOW_KIDS_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NowKidsFile extends File {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOW_KIDS_ID")
    private NowKids nowKids;

    public NowKidsFile(NowKids nowKids) {
        this.nowKids = nowKids;
    }

    public NowKidsFile(String fileOriginalName, String fileUUID, String filePath, FileType fileStatus, NowKids nowKids) {
        super(fileOriginalName, fileUUID, filePath, fileStatus);
        this.nowKids = nowKids;
    }
}
