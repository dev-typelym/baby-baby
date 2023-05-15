package com.app.babybaby.entity.member;


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
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Member parent;

    public void setParent(Member parent) {
        this.parent = parent;
    }

    @Builder
    public static Kid createKid(Long id, String kidName, Integer kidAge, GenderType kidGender,Member parent) {
        Kid kid = new Kid();
        kid.id = id;
        kid.kidName = kidName;
        kid.kidAge = kidAge;
        kid.kidGender = kidGender;
        kid.parent = parent;
        return kid;
    }


}
