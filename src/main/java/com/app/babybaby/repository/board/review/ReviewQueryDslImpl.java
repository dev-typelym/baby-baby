package com.app.babybaby.repository.board.review;

import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.review.QReview;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.search.admin.AdminReviewSearch;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import com.app.babybaby.type.CategoryType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;

import java.util.List;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.board.review.QReview.review;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.review.QReview.review;

@RequiredArgsConstructor
@Slf4j
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory query;


//    나의리뷰 조회
    @Override
    public Page<Review> findReviewById_QueryDSL(Pageable pageable,Long memberId) {
        log.info(memberId.toString());
        List<Review> reviews = query.select(review)
                .from(review)
                .join(review.member).fetchJoin()
                .leftJoin(review.event).fetchJoin()
                .leftJoin(review.reviewFiles).fetchJoin()
                .where(review.member.id.eq(memberId))
                .fetch();

        Long count = query.select(review.member.id.count())
                .from(review)
                .where(review.member.id.eq(memberId))
                .fetchOne();
        return new PageImpl<>(reviews,pageable,count);
    }

    @Override
    public Slice<Review> findAllByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        List<Review> reviewList = query.select(review)
                .from(review)
                .where(review.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (reviewList.size() > pageable.getPageSize()) {
            hasNext = true;
            reviewList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(reviewList, pageable, hasNext);
    }

//    [회원상세] 해당 이벤트의 리뷰 조회하기
    public List<Review> findAllReivewByEventId(Long eventId){
        return query.select(review)
                .from(review)
                .leftJoin(review.event)
                .where(review.event.id.eq(eventId))
                .fetch();
    }



//    [리뷰] 리스트 페이지
    public Page<Review> findAllReviewWithSearch_QueryDsl(Pageable pageable, ParentsBoardSearch parentsBoardSearch){
        BooleanExpression categoryType = parentsBoardSearch.getCategoryType() == null ? null : review.event.category.isNull().or(review.event.category.eq(parentsBoardSearch.getCategoryType()));

//        BooleanExpression categoryType = parentsBoardSearch.getCategoryType() == null ? null : parentsBoard.event.category.eq(parentsBoardSearch.getCategoryType());
        BooleanExpression searchTitle = parentsBoardSearch.getSearchTitle() == null ? null : review.boardTitle.contains(parentsBoardSearch.getSearchTitle());
        BooleanExpression searchContent = parentsBoardSearch.getSearchContent() == null ? null : review.boardContent.contains(parentsBoardSearch.getSearchContent());
        BooleanExpression searchAll = parentsBoardSearch.getSearchContent() == null && parentsBoardSearch.getSearchTitle() == null
                ? null
                :(review.boardContent.contains(parentsBoardSearch.getSearchContent())
                .or(review.boardTitle.contains(parentsBoardSearch.getSearchTitle())));
//        if(parentsBoardSearch.getCategoryType() == null) {
//            parentsBoardSearch.setCategoryType(CategoryType.TALK);
//        }
        log.info(categoryType + "카테고리임~~~~");
        log.info(searchTitle + "검색제목임~~~~");
        log.info(searchContent + "검색내용임~~~~");

//       전체 목록 불러오기(페이징)
        List<Review> foundReview = query.select(review)
                .from(review)
                .join(review.event)
                .fetchJoin()
                .leftJoin(review.reviewFiles)
                .fetchJoin()
                .orderBy(review.id.desc())
//                .where(createBooleanExpression(parentsBoardSearch)/*, createTextSearchOption(parentsBoardSearch)*/)
                .where(searchAll, categoryType)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
//                .where(createBooleanExpression(parentsBoardSearch)/*, createTextSearchOption(parentsBoardSearch)*/)
                .where(searchAll, categoryType)
                .fetchOne();
        log.info("asdsadasdd" + foundReview);
        return new PageImpl<>(foundReview, pageable, count);
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


    //  [관리자] 리뷰 전체 목록 조회
    @Override
    public Page<Review> findAllReviewBoardWithSearch_queryDSL(Pageable pageable , AdminReviewSearch adminReviewSearch, CategoryType eventCategory) {
        BooleanExpression reviewContentEq = adminReviewSearch.getReviewContent() == null ? null : review.boardContent.eq(adminReviewSearch.getReviewContent());

        QReview review = QReview.review;
        QEvent event = QEvent.event;

        List<Review> foundReviewBoard = query.select(review)
                .from(review)
                .join(review.event)
                .fetchJoin()
                .join(review.member)
                .fetchJoin()
                .where((eventCategory != null ? review.event.category.eq(eventCategory) : review.event.category.isNotNull()).and(reviewContentEq))
                .orderBy(review.id.asc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
                .where((eventCategory != null ? review.event.category.eq(eventCategory) : review.event.category.isNotNull()).and(reviewContentEq))
                .fetchOne();

        return new PageImpl<>(foundReviewBoard, pageable, count);
    }

    //  [관리자] 리뷰 상세보기
    @Override
    public Optional<Review> findReviewBoardById_queryDSL(Long reviewBoardId) {
        return Optional.ofNullable(
                query.select(review)
                        .from(review)
                        .join(review.event)
                        .fetchJoin()
                        .leftJoin(review.reviewFiles)
                        .fetchJoin()
                        .join(review.member)
                        .fetchJoin()
                        .where(review.id.eq(reviewBoardId))
                        .fetchOne()
        );
    }

    // [관리자] 리뷰 삭제하기
    @Override
    public void deleteReviewBoardByIds_queryDSL(List<Long> reviewBoardIds) {
        query.delete(review)
                .where(review.id.in(reviewBoardIds))
                .execute();
    }
}
