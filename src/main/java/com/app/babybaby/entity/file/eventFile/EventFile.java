package com.app.babybaby.entity.file.eventFile;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.file.File;
import com.app.babybaby.type.FileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true, exclude = {"event"})
@Table(name = "TBL_EVENT_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    public EventFile(Event event) {
        this.event = event;
    }
    @Builder
    public EventFile(String fileOriginalName, String fileUUID, String filePath, FileType fileStatus, Event event) {
        super(fileOriginalName, fileUUID, filePath, fileStatus);
        this.event = event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
