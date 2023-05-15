package com.app.babybaby.domain.memberDTO;


import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.GenderType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class KidDTO {
    private Long id;
    private String kidName;
    private Integer kidAge;
    private GenderType kidGender;

    private String eventTitle;
    private LocalDateTime eventStartDate;

    private Member parent;

    @Builder
    public KidDTO(Long id, String kidName, Integer kidAge, GenderType kidGender, String eventTitle, LocalDateTime eventStartDate, Member parent) {
        this.id = id;
        this.kidName = kidName;
        this.kidAge = kidAge;
        this.kidGender = kidGender;
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.parent = parent;
    }
}
