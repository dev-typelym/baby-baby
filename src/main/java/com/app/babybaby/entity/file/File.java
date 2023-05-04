package com.app.babybaby.entity.file;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.type.FileType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = "boardInfo")
@Table(name = "TBL_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class File {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String fileOriginalName;
    @NotNull
    private String fileUUID;
    @NotNull
    private String filePath;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FileType fileStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardInfo boardInfo;
}
