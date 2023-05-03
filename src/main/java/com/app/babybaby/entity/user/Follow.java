package com.app.babybaby.entity.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_FOLLOW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    private User following;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    private User follower;
}
