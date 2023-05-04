package com.app.babybaby.entity.audit;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class Period {
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void create(){
        this.registerDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){
        this.updateDate = LocalDateTime.now();
    }
}
