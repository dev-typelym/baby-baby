package com.app.babybaby.entity.board;

import com.app.babybaby.entity.audit.Period;
import com.app.babybaby.entity.file.File;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_BOARD_INFO")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BoardInfo extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String boardTitle;
    private String boardContent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardInfo")
    private List<File> files;

    public void addFiles(File file){
        this.files.add(file);
    }
}
