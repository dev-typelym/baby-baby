package com.app.babybaby.repository.member.follow;


import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.review.Review;
import com.app.babybaby.entity.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FollowQueryDsl {
    public Slice<Member> findFollowersByMemberId(Pageable pageable, Long memberId);

    public Slice<Member> findFollowingsByMemberId(Pageable pageable, Long memberId);

//        [회원 상세]
        public Long findFollowingMemberCountByMemberId_QueryDSL(Long memberId);

//        [회원 상세]
        public Long findFollowerMemberCountByMemberId_QueryDSL(Long memberId);

//        [회원 상세]
        public Boolean getIsFollowedByMemberId(Long memberId, Long sessionId);

    //        [회원 상세] 유저아이디로 부모님 마당 다 가져오기
        public List<ParentsBoard> findAllParentsBoardByMemberId_QueryDSL(Long memberId);

        public List<Review> findALlReviewByMemberId_QueryDSL(Long memberId);

//        김동한
//        public List<Member> findFollowers
}
