package com.app.babybaby.entity.calendar;

import com.app.babybaby.type.Category;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Table(name = "TBL_CALENDAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String calendarID;

    @NotNull
    private Category category;

    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
}
