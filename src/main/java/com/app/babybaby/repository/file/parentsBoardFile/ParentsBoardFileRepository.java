package com.app.babybaby.repository.file.parentsBoardFile;

import com.app.babybaby.entity.file.parentsBoardFile.ParentsBoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsBoardFileRepository extends JpaRepository<ParentsBoardFile, Long>, ParentsBoardFileQueryDsl {
}
