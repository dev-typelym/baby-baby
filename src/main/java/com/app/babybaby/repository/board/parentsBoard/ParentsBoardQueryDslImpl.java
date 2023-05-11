package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.SearchTextOption;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
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
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;

@RequiredArgsConstructor
@Slf4j
public class ParentsBoardQueryDslImpl implements ParentsBoardQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<ParentsBoard> findAllWithSearch(ParentsBoardSearch parentsBoardSearch, Pageable pageable) {


//       전체 목록 불러오기(페이징)
        List<ParentsBoard> foundParentsBoard = query.select(parentsBoard)
                .from(parentsBoard)
                .join(parentsBoard.event)
                .fetchJoin()
                .leftJoin(parentsBoard.parentsBoardFiles)
                .fetchJoin()
                .orderBy(parentsBoard.id.desc())
                .where(createBooleanExpression(parentsBoardSearch), createTextSearchOption(parentsBoardSearch))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoard.count())
                .from(parentsBoard)
                .where(createBooleanExpression(parentsBoardSearch), createTextSearchOption(parentsBoardSearch))
                .fetchOne();

        return new PageImpl<>(foundParentsBoard, pageable, count);
    }

    //    상세보기
    @Override
    public Optional<ParentsBoard> findDetailById(Long id) {

        return Optional.ofNullable(
                query.select(parentsBoard)
                        .from(parentsBoard)
                        .join(parentsBoard.event)
                        .fetchJoin()
                        .leftJoin(parentsBoard.parentsBoardFiles)
                        .fetchJoin()
                        .join(parentsBoard.member)
                        .fetchJoin()
                        .where(parentsBoard.event.id.eq(id))
                        .fetchOne()
        );
    }

    //    작성하기 참여예정 체험학습 select 해오기
    @Override
    public Optional<Event> findByEventId(Long id) {
        return Optional.ofNullable(
                query.select(event)
                        .from(event)
                        .where(event.id.eq(id))
                        .fetchOne());
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

    private BooleanExpression createTextSearchOption(ParentsBoardSearch parentsBoardSearch) {
        SearchTextOption option = parentsBoardSearch.getSearchTextOption();
        BooleanExpression booleanExpression = null;
        String searchText = parentsBoardSearch.getSearchText();

        if (searchText == null || searchText == "") {
            return null;
        }

        switch (option) {
            case TITLE:
                booleanExpression = event.boardTitle.like(searchText);
                break;
            case CONTENT:
                booleanExpression = event.boardContent.like(searchText);
                break;
            case BOTH:
                booleanExpression = event.boardTitle.like(searchText).or(event.boardContent.like(searchText));
                break;
        }

        return booleanExpression;
    }

}
