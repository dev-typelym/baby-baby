package com.app.babybaby.entity.calendar;

import com.app.babybaby.entity.board.Event;
import com.app.babybaby.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(exclude = "user")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guideSchedule")
    private List<Event> events;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
