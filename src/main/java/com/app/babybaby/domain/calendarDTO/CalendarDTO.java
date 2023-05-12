package com.app.babybaby.domain.calendarDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CalendarDTO {
    private Long id;
    private String calendarName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
