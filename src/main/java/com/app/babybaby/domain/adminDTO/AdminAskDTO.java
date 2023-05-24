package com.app.babybaby.domain.adminDTO;

import com.app.babybaby.type.ProcessType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminAskDTO {
    private Long id;
    private String askTitle;
    private String writerName;
    private LocalDateTime writeDate;
    private String askContent;
    private ProcessType askStatus;
}
