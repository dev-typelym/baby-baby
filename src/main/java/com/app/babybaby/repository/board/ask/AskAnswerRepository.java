package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.AskAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AskAnswerRepository extends JpaRepository<AskAnswer,Long>, AskAnswerQueryDsl {
}
