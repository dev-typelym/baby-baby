package com.app.babybaby.repository.member.follow;

import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.app.babybaby.entity.board.review.QReview;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.QFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;
import static com.app.babybaby.entity.board.review.QReview.review;
import static com.app.babybaby.entity.member.QFollow.follow;

@RequiredArgsConstructor
public class FollowQueryDslImpl implements FollowQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<Member> findFollowersByMemberId(Pageable pageable, Long memberId) {
        List<Member> memberList = query.select(follow.follower)
                .from(follow)
                .where(follow.follower.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (memberList.size() > pageable.getPageSize()) {
            hasNext = true;
            memberList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(memberList, pageable, hasNext);
    }


    @Override
    public Slice<Member> findFollowingsByMemberId(Pageable pageable, Long memberId) {
        List<Member> memberList = query.select(follow.following)
                .from(follow)
                .where(follow.following.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (memberList.size() > pageable.getPageSize()) {
            hasNext = true;
            memberList.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(memberList, pageable, hasNext);
    }

//    [회원 상세]
    public Long findFollowingMemberCountByMemberId_QueryDSL(Long memberId){
        return query.select(follow.following.id.count())
                .from(follow)
                .where(follow.following.id.eq(memberId))
                .fetchOne();
    }

    public Long findFollowerMemberCountByMemberId_QueryDSL(Long memberId){
        return query.select(follow.follower.id.count())
                .from(follow)
                .where(follow.follower.id.eq(memberId))
                .fetchOne();
    }

        
    /* **나중에 옮기기** 내가 쓴 부모님 마당 가져오기*/
    public List<ParentsBoard> findAllParentsBoardByMemberId_QueryDSL(Long memberId){
        return query.selectFrom(parentsBoard)
                .leftJoin(parentsBoard.event)
                .leftJoin(parentsBoard.parentsBoardFiles)
                .where(parentsBoard.member.id.eq(memberId))
                .fetch();
    }
    /* **나중에 옮기기** 내가 쓴 리뷰 가져오기*/
    public List<Review> findALlReviewByMemberId_QueryDSL(Long memberId){
        return query.selectFrom(review)
                .leftJoin(review.event)
                .leftJoin(review.reviewFiles)
                .where(review.member.id.eq(memberId))
                .fetch();
    }

}
