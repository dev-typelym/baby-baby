package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.AskAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AskRepository extends JpaRepository<AskAnswer, Long>, AskAnswerQueryDsl {
}
