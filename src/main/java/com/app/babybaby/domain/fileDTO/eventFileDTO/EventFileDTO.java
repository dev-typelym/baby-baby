package com.app.babybaby.domain.fileDTO.eventFileDTO;

import com.app.babybaby.type.FileType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Component
@Data
@RequiredArgsConstructor
public class EventFileDTO {
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String fileOriginalName;
    private String filePath;
    private FileType fileStatus;
    private String fileUUID;
    private Long id;

    @Builder
    public EventFileDTO(LocalDateTime registerDate, LocalDateTime updateDate, String fileOriginalName, String filePath, FileType fileStatus, String fileUUID, Long id) {
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.fileOriginalName = fileOriginalName;
        this.filePath = filePath;
        this.fileStatus = fileStatus;
        this.fileUUID = fileUUID;
        this.id = id;
    }
}
