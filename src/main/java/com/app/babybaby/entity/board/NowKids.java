package com.app.babybaby.entity.board;

import com.app.babybaby.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_NOW_KIDS")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NowKids extends BoardInfo{

    @OneToOne(fetch = FetchType.LAZY)
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    private User guide;
}
