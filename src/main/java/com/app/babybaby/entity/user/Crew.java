package com.app.babybaby.entity.user;

import com.app.babybaby.entity.board.event.Event;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"kid", "guides"})
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
    @JoinColumn(name = "GUIDES_ID")
    private Guide guides;

    public Crew(Kid kid, Guide guides) {
        this.kid = kid;
        this.guides = guides;
    }
}
