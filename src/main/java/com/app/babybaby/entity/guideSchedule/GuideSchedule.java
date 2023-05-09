package com.app.babybaby.entity.guideSchedule;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.calendar.Calendar;
import com.app.babybaby.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"user", "event", "calendar"})
@Table(name = "TBL_GUIDE_SCHEDULE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuideSchedule {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "CALENDAR_ID")
    private Calendar calendar;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Member user;

    public GuideSchedule(Calendar calendar, Event event, Member user) {
        this.calendar = calendar;
        this.event = event;
        this.user = user;
    }
}
