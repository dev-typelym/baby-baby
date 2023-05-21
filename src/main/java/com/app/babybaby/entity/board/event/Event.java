package com.app.babybaby.entity.board.event;

import com.app.babybaby.entity.board.BoardInfo;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.file.eventFile.EventFile;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.CategoryType;
import com.sun.istack.NotNull;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"company", "calendar", "evenFiles","reviews"})
@Table(name = "TBL_EVENT")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BoardInfo {

    @NotNull
    private Long eventRecruitCount;

    @NotNull
    @Embedded
    private Address eventLocation;

    @NotNull
    private Long eventPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType category;

    /* 파일 List */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<EventFile> eventFiles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Calendar calendar;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Member company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<Review> reviews;

    public Event(Long eventRecruitCount, Address eventLocation, Long eventPrice, CategoryType category, Calendar calendar, Member company) {
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.category = category;
        this.calendar = calendar;
        this.company = company;
    }

    @Builder
    public Event(String boardTitle, String boardContent, Long eventRecruitCount, Address eventLocation, Long eventPrice, CategoryType category, Calendar calendar, Member company, List<EventFile> eventFiles) {
        super(boardTitle, boardContent);
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.category = category;
        this.calendar = calendar;
        this.company = company;
        this.eventFiles = eventFiles;
    }

    @Builder
    public Event(Long id, String boardTitle, String boardContent, Long eventRecruitCount, Address eventLocation, Long eventPrice, CategoryType category, Calendar calendar, Member company, List<EventFile> eventFiles) {
        super(id, boardTitle, boardContent);
        this.eventRecruitCount = eventRecruitCount;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.category = category;
        this.calendar = calendar;
        this.company = company;
        this.eventFiles = eventFiles;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
