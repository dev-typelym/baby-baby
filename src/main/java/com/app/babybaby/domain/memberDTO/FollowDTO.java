package com.app.babybaby.domain.memberDTO;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.type.AlertReadStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Data
public class FollowDTO {
    private Long id;
    private Member following;
    private Member follower;
    private LocalDate followDate;
    private AlertReadStatus alertReadStatus;

    @Builder
    public FollowDTO(Long id, Member following, Member follower, LocalDate followDate, AlertReadStatus alertReadStatus) {
        this.id = id;
        this.following = following;
        this.follower = follower;
        this.followDate = followDate;
        this.alertReadStatus = alertReadStatus;
    }

}
