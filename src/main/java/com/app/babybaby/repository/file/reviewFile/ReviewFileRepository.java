package com.app.babybaby.repository.file.reviewFile;

import com.app.babybaby.entity.file.reviewFile.ReviewFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFileRepository extends JpaRepository<ReviewFile, Long>, ReviewFileQueryDsl {
}
