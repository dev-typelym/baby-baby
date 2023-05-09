package com.app.babybaby.entity.like.eventLike;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_EVENT_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLike {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Member user;

    public EventLike(Event event, Member user) {
        this.event = event;
        this.user = user;
    }
}
