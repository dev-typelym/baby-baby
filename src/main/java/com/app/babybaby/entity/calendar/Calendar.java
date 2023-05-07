package com.app.babybaby.entity.calendar;

import com.app.babybaby.type.CategoryType;
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
    private String calendarName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    public Calendar(String calendarName, CategoryType categoryType, LocalDateTime startDate, LocalDateTime endDate) {
        this.calendarName = calendarName;
        this.categoryType = categoryType;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
