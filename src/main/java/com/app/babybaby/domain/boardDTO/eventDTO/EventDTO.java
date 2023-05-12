package com.app.babybaby.domain.boardDTO.eventDTO;

import com.app.babybaby.domain.calendarDTO.CalendarDTO;
import com.app.babybaby.domain.fileDTO.eventFileDTO.EventFileDTO;
import com.app.babybaby.domain.memberDTO.MemberDTO;
import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.CategoryType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class EventDTO {
    private BoardInfo board;
    private Long eventRecruitCount;
    private Address eventLocation;
    private Long eventPrice;
    private CategoryType category;
    private CalendarDTO calendar;
    private Member company;
    private List<EventFileDTO> eventFileDTOS;
}
