package com.app.babybaby.entity.member;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.guideSchedule.GuideSchedule;
import com.app.babybaby.type.GuideAvailableType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"event", "generalGuide", "adminGuide", "guideSchedule, crew"})
@Table(name = "TBL_GUIDE")
@DynamicUpdate
@DynamicInsert
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
    @JoinColumn(name = "GENERAL_GUIDE_ID")
    private Member generalGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_GUIDE_ID")
    private Member adminGuide;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'AVAILABLE'")
    private GuideAvailableType availableType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guide")
    private List<Crew> crews;

    public Guide(Event event, Member generalGuide, Member adminGuide, List<Crew> crews) {
        this.event = event;
        this.generalGuide = generalGuide;
        this.adminGuide = adminGuide;
        this.crews = crews;
    }

    public Guide(Event event, Member adminGuide, List<Crew> crews) {
        this.event = event;
        this.adminGuide = adminGuide;
        this.crews = crews;
    }

    public Guide(Event event, Member adminGuide) {
        this.event = event;
        this.adminGuide = adminGuide;
    }

    public void setAvailableType(GuideAvailableType availableType) {
        this.availableType = availableType;
    }
}
