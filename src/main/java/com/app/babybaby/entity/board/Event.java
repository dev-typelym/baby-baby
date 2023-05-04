package com.app.babybaby.entity.board;

import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.calendar.GuideSchedule;
import com.app.babybaby.entity.embeddable.Address;
import com.app.babybaby.entity.user.User;
import com.app.babybaby.type.CategoryType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"user", "guideSchedule"})
@Table(name = "TBL_EVENT")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BoardInfo{

    @NotNull
    private Long eventRecruitCount;

    @NotNull
    @Embedded
    private Address eventLocation;

    @NotNull
    private Long eventPrice;

    @NotNull
    private String eventTitle;

    @NotNull
    private String eventContent;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @OneToOne
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private GuideSchedule guideSchedule;

}
