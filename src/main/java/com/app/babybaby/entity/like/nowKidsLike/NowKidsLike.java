package com.app.babybaby.entity.like.nowKidsLike;

import com.app.babybaby.entity.board.nowKids.NowKids;
import com.app.babybaby.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_NOW_KIDS_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NowKidsLike {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOW_KIDS_ID")
    private NowKids nowKids;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public NowKidsLike(NowKids nowKids, Member member) {
        this.nowKids = nowKids;
        this.member = member;
    }
}
