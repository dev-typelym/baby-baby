package com.app.babybaby.service.calendar;

import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.entity.calendar.Calendar;

public interface CalendarService {

    default CalendarDTO toCalendarDTO(Calendar calendar){
        return CalendarDTO.builder()
                .calendarName(calendar.getCalendarName())
                .endDate(calendar.getEndDate())
                .startDate(calendar.getStartDate())
                .id(calendar.getId())
                .build();
    }

    default Calendar toCalendarEntity(CalendarDTO calendarDTO){
        return Calendar.builder()
                .calendarName(calendarDTO.getCalendarName())
                .endDate(calendarDTO.getEndDate())
                .startDate(calendarDTO.getStartDate())
                .build();
    }
}
