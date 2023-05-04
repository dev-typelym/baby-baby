package com.app.babybaby.entity.user;

import com.app.babybaby.entity.board.Event;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
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
    private User generalGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    private User adminGuide;





}
