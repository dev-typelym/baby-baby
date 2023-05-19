package com.app.babybaby.entity.file.parentsBoardFile;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.File;
import com.app.babybaby.type.FileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString(callSuper = true, exclude = {"parentsBoard"})
@Table(name = "TBL_PARENTS_BOARD_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentsBoardFile extends File {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTS_BOARD_ID")
    private ParentsBoard parentsBoard;

    public ParentsBoardFile(ParentsBoard parentsBoard) {
        this.parentsBoard = parentsBoard;
    }

    @Builder
    public ParentsBoardFile(String fileOriginalName, String fileUUID, String filePath, FileType fileStatus, ParentsBoard parentsBoard) {
        super(fileOriginalName, fileUUID, filePath, fileStatus);
        this.parentsBoard = parentsBoard;
    }

    public ParentsBoardFile(String fileOriginalName, String fileUUID, String filePath, FileType fileStatus) {
        super(fileOriginalName, fileUUID, filePath, fileStatus);
        this.parentsBoard = parentsBoard;
    }

}
