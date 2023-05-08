package com.app.babybaby.entity.user;

import com.app.babybaby.entity.board.event.Event;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"kid", "guide"})
@Table(name = "TBL_CREW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crew{
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn
    private Kid kid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GUIDE_ID")
    private Guide guide;

    public Crew(Kid kid, Guide guide) {
        this.kid = kid;
        this.guide = guide;
    }
}
