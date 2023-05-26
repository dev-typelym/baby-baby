package com.app.babybaby.entity.member;

import com.app.babybaby.entity.audit.Period;
import com.app.babybaby.type.AlertReadStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(exclude = {"following", "follower"})
@Table(name = "TBL_FOLLOW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member following;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member follower;

    private LocalDate followDate;

    @ColumnDefault("'UNREAD'")
    @Enumerated(EnumType.STRING)
    private AlertReadStatus alertReadStatus;

    @Builder
    public Follow(Member following, Member follower) {
        this.follower = follower;
        this.following = following;
    }

    public void updateStatus(){
        this.alertReadStatus = alertReadStatus.READ;
    }

}
