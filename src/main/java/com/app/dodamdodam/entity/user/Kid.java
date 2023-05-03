package com.app.dodamdodam.entity.user;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_KID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Kid {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String kidName;
    @NotNull
    private Long kidAge;

    /*일대일*/
    @OneToOne(fetch = FetchType.LAZY)
    private Event eventId;
    @OneToOne(fetch = FetchType.LAZY)
    private User userId;
    @OneToOne(fetch = FetchType.LAZY)
    private User guideId;


}
