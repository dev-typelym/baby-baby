package com.app.babybaby.entity.board;

import com.app.babybaby.entity.user.User;
import com.app.babybaby.type.ProcessType;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = "user")
@Table(name = "TBL_ASK")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class Ask extends BoardInfo {

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'HOLD'")
    private ProcessType askStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;


}
