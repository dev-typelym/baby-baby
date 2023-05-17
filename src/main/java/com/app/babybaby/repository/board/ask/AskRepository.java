package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.Ask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AskRepository extends JpaRepository<Ask, Long>, AskQueryDsl {
}
