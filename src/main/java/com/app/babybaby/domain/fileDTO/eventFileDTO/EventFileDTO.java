package com.app.babybaby.domain.fileDTO.eventFileDTO;

import com.app.babybaby.type.FileType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
public class EventFileDTO {
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String fileOriginalName;
    private String filePath;
    private FileType fileStatus;
    private String fileUUID;
    private Long id;
}
