package com.app.babybaby.repository.file.nowKidsFile;

import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;

import java.util.List;

public interface NowKidsFileQueryDsl {
    public List<NowKidsFile> findAllNowKidsFilesWithNowKidsId(Long nowKidsId);
    public void deleteByNowKidsId(Long eventId);
}
