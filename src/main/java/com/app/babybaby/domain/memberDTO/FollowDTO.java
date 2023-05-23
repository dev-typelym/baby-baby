package com.app.babybaby.domain.memberDTO;

import com.app.babybaby.entity.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@RequiredArgsConstructor
@Data
public class FollowDTO {
    private Long id;
    private Member following;
    private Member follower;

    @Builder
    public FollowDTO(Long id, Member following, Member follower) {
        this.id = id;
        this.following = following;
        this.follower = follower;
    }
}
