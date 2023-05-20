package com.app.babybaby.entity.member;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KID_ID")
    private Kid kid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GUIDE_ID")
    private Guide guide;

    @NotNull
    private String eventRegisterDate;

    @Builder
    public Crew(Kid kid, Guide guide) {
        this.kid = kid;
        this.guide = guide;
    }

    @Builder
    public Crew(Long id, Kid kid, Guide guide, String eventRegisterDate) {
        this.id = id;
        this.kid = kid;
        this.guide = guide;
        this.eventRegisterDate = eventRegisterDate;
    }
}
