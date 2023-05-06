package com.app.babybaby.entity.user;

import com.app.babybaby.entity.board.event.Event;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"kid", "generalGuide", "adminGuide"})
@Table(name = "TBL_CREW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crew{
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @OneToOne
    @JoinColumn
    private Kid kid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENERAL_GUIDE_ID")
    private User generalGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_GUIDE_ID")
    private User adminGuide;

}
