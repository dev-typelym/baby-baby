package com.app.babybaby.repository.calendar;

import com.app.babybaby.entity.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long>, CalendarQueryDsl {
}
