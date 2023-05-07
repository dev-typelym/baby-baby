package com.app.babybaby.entity.file.parentsBoardFile;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.file.File;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"parentsBoard"})
@Table(name = "TBL_PARENTS_BOARD_FILE")
@PrimaryKeyJoinColumn(name = "ID")
public class ParentsBoardFile extends File {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTS_BOARD_ID")
    private ParentsBoard parentsBoard;

}
