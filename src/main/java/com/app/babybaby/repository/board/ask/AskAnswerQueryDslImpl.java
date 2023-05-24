package com.app.babybaby.repository.board.ask;

import com.app.babybaby.entity.board.ask.QAskAnswer;
import com.app.babybaby.entity.file.eventFile.QEventFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class AskAnswerQueryDslImpl implements AskAnswerQueryDsl {
    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void deleteByAskId(Long askId) {
        QAskAnswer askAnswer = QAskAnswer.askAnswer;
        query.delete(askAnswer)
                .where(askAnswer.ask.id.eq(askId))
                .execute();
    }
}
