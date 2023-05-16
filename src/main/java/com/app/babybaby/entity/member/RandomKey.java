package com.app.babybaby.entity.member;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"member"})
@Table(name = "TBL_RANDOM_KEY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RandomKey {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String randomKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
