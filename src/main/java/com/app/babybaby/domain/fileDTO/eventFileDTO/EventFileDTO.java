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

@Data
@Builder
public class EventFileDTO {
    private Long id;
    private String fileOriginalName;
    private String fileUUID;
    private String filePath;
    private FileType fileStatus;

}
