package com.app.babybaby.domain.adminDTO;

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
}
