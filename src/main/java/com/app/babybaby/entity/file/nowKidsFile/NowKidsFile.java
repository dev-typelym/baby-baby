package com.app.babybaby.entity.file.nowKidsFile;

import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.file.File;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"nowKids"})
@Table(name = "TBL_NOW_KIDS_FILE")
@PrimaryKeyJoinColumn(name = "ID")

public class NowKidsFile extends File {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOW_KIDS_ID")
    private NowKids nowKids;
}
