package com.app.babybaby.entity.file.eventFile;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.File;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"event"})
@Table(name = "TBL_EVENT_FILE")
@PrimaryKeyJoinColumn(name = "ID")

public class EventFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

}
