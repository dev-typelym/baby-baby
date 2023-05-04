package com.app.babybaby.entity.alert;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"user", "guideSchedule"})
@Table(name = "TBL_ALERT")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class alert {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    
}
