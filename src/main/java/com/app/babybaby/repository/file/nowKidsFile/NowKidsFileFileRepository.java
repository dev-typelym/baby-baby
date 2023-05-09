package com.app.babybaby.repository.file.nowKidsFile;

import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NowKidsFileFileRepository extends JpaRepository<NowKidsFile, Long>, NowKidsFileQueryDsl {
}
