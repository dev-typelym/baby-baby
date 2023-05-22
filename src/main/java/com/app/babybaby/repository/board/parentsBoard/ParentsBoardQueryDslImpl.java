package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.board.nowKids.QNowKids;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.search.admin.AdminParentsBoardSearch;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.board.nowKids.QNowKids.nowKids;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;

@RequiredArgsConstructor
@Slf4j
public class ParentsBoardQueryDslImpl implements ParentsBoardQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<ParentsBoard> findAllWithSearch_QueryDsl(Pageable pageable, ParentsBoardSearch parentsBoardSearch) {
        BooleanExpression categoryType = parentsBoardSearch.getCategoryType() == null ? null : parentsBoard.event.category.isNull().or(parentsBoard.event.category.eq(parentsBoardSearch.getCategoryType()));

//        BooleanExpression categoryType = parentsBoardSearch.getCategoryType() == null ? null : parentsBoard.event.category.eq(parentsBoardSearch.getCategoryType());
        BooleanExpression searchTitle = parentsBoardSearch.getSearchTitle() == null ? null : parentsBoard.boardTitle.contains(parentsBoardSearch.getSearchTitle());
        BooleanExpression searchContent = parentsBoardSearch.getSearchContent() == null ? null : parentsBoard.boardContent.contains(parentsBoardSearch.getSearchContent());
        BooleanExpression searchAll = parentsBoardSearch.getSearchContent() == null && parentsBoardSearch.getSearchTitle() == null
                ? null
                :(parentsBoard.boardContent.contains(parentsBoardSearch.getSearchContent())
                .or(parentsBoard.boardTitle.contains(parentsBoardSearch.getSearchTitle())));
//        if(parentsBoardSearch.getCategoryType() == null) {
//            parentsBoardSearch.setCategoryType(CategoryType.TALK);
//        }
        log.info(categoryType + "카테고리임~~~~");
        log.info(searchTitle + "검색제목임~~~~");
        log.info(searchContent + "검색내용임~~~~");

//       전체 목록 불러오기(페이징)
        List<ParentsBoard> foundParentsBoard = query.select(parentsBoard)
                .from(parentsBoard)
                .join(parentsBoard.event)
                .fetchJoin()
                .leftJoin(parentsBoard.parentsBoardFiles)
                .fetchJoin()
                .orderBy(parentsBoard.id.desc())
//                .where(createBooleanExpression(parentsBoardSearch)/*, createTextSearchOption(parentsBoardSearch)*/)
                .where(searchAll, categoryType)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoard.count())
                .from(parentsBoard)
//                .where(createBooleanExpression(parentsBoardSearch)/*, createTextSearchOption(parentsBoardSearch)*/)
                .where(searchAll, categoryType)
                .fetchOne();
        log.info("asdsadasdd" + foundParentsBoard);
        return new PageImpl<>(foundParentsBoard, pageable, count);
    }

    //    내가쓴 게시글
    @Override
    public Page<ParentsBoard> findParentBoardListByMemberId(Pageable pageable, Long memberId) {

        List<ParentsBoard> foundParentsBoard = query.select(parentsBoard)
                .from(parentsBoard)
                .join(parentsBoard.event)
                .fetchJoin()
                .leftJoin(parentsBoard.parentsBoardFiles)
                .fetchJoin()
                .orderBy(parentsBoard.id.desc())
                .where(parentsBoard.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoard.count())
                .from(parentsBoard)
                .where(parentsBoard.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundParentsBoard, pageable, count);
    }

    //    상세보기
    @Override
    public Optional<ParentsBoard> findDetailById_QueryDsl(Long id) {

        return Optional.ofNullable(
                query.select(parentsBoard)
                        .from(parentsBoard)
                        .join(parentsBoard.event)
                        .fetchJoin()
                        .leftJoin(parentsBoard.parentsBoardFiles)
                        .fetchJoin()
                        .join(parentsBoard.member)
                        .fetchJoin()
                        .where(parentsBoard.id.eq(id))
                        .fetchOne()
        );
    }

    //    작성하기 참여예정 체험학습 select 해오기
    @Override
    public Optional<Event> findByEventId_QueryDsl(Long id) {
        return Optional.ofNullable(
                query.select(event)
                        .from(event)
                        .where(event.id.eq(id))
                        .fetchOne());
    }

    @Override
    public Page<ParentsBoard> findListByMemberIdWithPaging_QueryDSL(Pageable pageable,Long memberId) {
        List<ParentsBoard> parentsBoards = query.select(parentsBoard)
                .from(parentsBoard)
                .join(parentsBoard.member).fetchJoin()
                .join(parentsBoard.parentsBoardFiles).fetchJoin()
                .where(parentsBoard.member.id.eq(memberId))
                .orderBy(parentsBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoard.count()).from(parentsBoard).where(parentsBoard.member.id.eq(memberId)).fetchOne();


        return new PageImpl<>(parentsBoards, pageable, count);
    }


    //    맨위 카테고리 검색 설정
    private BooleanExpression createBooleanExpression(ParentsBoardSearch parentsBoardSearch) {

        BooleanExpression booleanExpression = null;

        CategoryType categoryType = parentsBoardSearch.getCategoryType();

        if(categoryType == null) {
            return null;
        }
        log.info("===============" + parentsBoardSearch.getCategoryType());
        switch (categoryType){
            case AGRICULTURE:
                booleanExpression = event.category.eq(categoryType.AGRICULTURE);
                break;
            case ART:
                booleanExpression = event.category.eq(categoryType.ART);
                break;
            case TRADITION:
                booleanExpression = event.category.eq(categoryType.TRADITION);
                break;
            case CRAFT:
                booleanExpression = event.category.eq(categoryType.CRAFT);
                break;
            case SCIENCE:
                booleanExpression = event.category.eq(categoryType.SCIENCE);
                break;
            case MUSEUM:
                booleanExpression = event.category.eq(categoryType.MUSEUM);
                break;
            case SPORTS:
                booleanExpression = event.category.eq(categoryType.SPORTS);
                break;
            case ETC:
                booleanExpression = event.category.eq(categoryType.ETC);
                break;
            default:
                break;
        }

        return booleanExpression;
    }

    // 상세보기 카테고리 최신글 2개 가져오기 where절 손 봐야하나?
    @Override
    public List<ParentsBoard> find2RecentDesc(CategoryType category) {
//        QParentsBoard subParents = new QParents
        List<ParentsBoard> parentsBoards = query.select(parentsBoard)
                .from(parentsBoard)
                .where(parentsBoard.event.category.eq(category))
                .orderBy(parentsBoard.id.desc())
                .limit(2)
                .fetch();
        return parentsBoards;
    }

//    QNowKids subNowKids = new QNowKids("subNowKids");
//    List<Member> subQueryResult = query.select(subNowKids.guide)
//            .from(subNowKids)
//            .orderBy(subNowKids.id.desc())
//            .limit(8)
//            .fetch();
//        return query.selectDistinct(nowKids.guide)
//            .from(nowKids)
//                .where(nowKids.guide.in(subQueryResult))
//            .fetch();

    //[관리자] 보호자마당 전체 목록 조회
    @Override
    public Page<ParentsBoard> findAllParentsBoardWithSearch_queryDSL(Pageable pageable, AdminParentsBoardSearch adminParentsBoardSearch) {
        BooleanExpression parentsBoardNameEq = adminParentsBoardSearch.getParentsBoardTitle() == null ? null : parentsBoard.boardTitle.eq(adminParentsBoardSearch.getParentsBoardTitle());

        QParentsBoard parentsBoard = QParentsBoard.parentsBoard;
        QEvent event = QEvent.event;

        List<ParentsBoard> foundParentsBoard = query.select(parentsBoard)
                .from(parentsBoard)
                .join(parentsBoard.event)
                .join(parentsBoard.member)
                .fetchJoin()
                .where(parentsBoardNameEq)
                .orderBy(parentsBoard.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoard.count())
                .from(parentsBoard)
                .where(parentsBoardNameEq)
                .fetchOne();

        return new PageImpl<>(foundParentsBoard, pageable, count);
    }

    //[관리자] 부모님마당 상세 조회
    @Override
    public Optional<ParentsBoard> findParentsBoardById_queryDSL(Long parentsBoardId) {
        return Optional.ofNullable(
                query.select(parentsBoard)
                        .from(parentsBoard)
                        .join(parentsBoard.event)
                        .fetchJoin()
                        .leftJoin(parentsBoard.parentsBoardFiles)
                        .fetchJoin()
                        .join(parentsBoard.member)
                        .fetchJoin()
                        .where(parentsBoard.event.id.eq(parentsBoardId))
                        .fetchOne()
        );
    }

    //  [관리자] 보호자마당 삭제
    @Override
    public void deleteAdminParentsBoardByIds_queryDSL(List<Long> parentsBoardIds) {
        query.delete(parentsBoard)
                .where(parentsBoard.id.in(parentsBoardIds))
                .execute();
    }
}
