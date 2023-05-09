package com.app.babybaby.entity.user;


import com.app.babybaby.type.GenderType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"parent"})
@Table(name = "TBL_KID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Kid {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String kidName;
    @NotNull
    @ColumnDefault("0")
    private Integer kidAge;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderType kidGender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private User parent;

    public Kid(String kidName, Integer kidAge, GenderType kidGender, User parent) {
        this.kidName = kidName;
        this.kidAge = kidAge;
        this.kidGender = kidGender;
        this.parent = parent;
    }
}
