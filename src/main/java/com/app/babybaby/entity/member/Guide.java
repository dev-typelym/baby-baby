package com.app.babybaby.entity.member;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.guideSchedule.GuideSchedule;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"event", "generalGuide", "adminGuide", "guideSchedule, crew"})
@Table(name = "TBL_GUIDE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Guide {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GUIDE_SCHEDULE")
    private GuideSchedule guideSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENERAL_GUIDE_ID")
    private Member generalGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_GUIDE_ID")
    private Member adminGuide;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guide")
    private List<Crew> crews;

    public Guide(Event event, GuideSchedule guideSchedule, Member generalGuide, Member adminGuide, List<Crew> crews) {
        this.event = event;
        this.guideSchedule = guideSchedule;
        this.generalGuide = generalGuide;
        this.adminGuide = adminGuide;
        this.crews = crews;
    }

    public Guide(Event event, GuideSchedule guideSchedule, Member adminGuide, List<Crew> crews) {
        this.event = event;
        this.guideSchedule = guideSchedule;
        this.adminGuide = adminGuide;
        this.crews = crews;
    }
}
