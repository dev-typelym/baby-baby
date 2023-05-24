package com.app.babybaby.repository.file.nowKidsFile;

import com.app.babybaby.entity.file.nowKidsFile.NowKidsFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.babybaby.entity.file.eventFile.QEventFile.eventFile;
import static com.app.babybaby.entity.file.nowKidsFile.QNowKidsFile.nowKidsFile;

@RequiredArgsConstructor
public class NowKidsFileQueryDslImpl implements NowKidsFileQueryDsl {
    private final JPAQueryFactory query;
    
    /* NowKids의 아이디로 모든 파일의 정보를 가져오기 */
    public List<NowKidsFile> findAllNowKidsFilesWithNowKidsId(Long nowKidsId) {
        return query.selectFrom(nowKidsFile)
                .where(nowKidsFile.nowKids.id.eq(nowKidsId))
                .fetch();
    }

    @Override
    @Transactional
    public void deleteByNowKidsId(Long nowKidsId) {
        query.delete(nowKidsFile)
                .where(nowKidsFile.nowKids.id.eq(nowKidsId))
                .execute();
    }


}
