package com.app.babybaby.repository.member.follow;

import com.app.babybaby.entity.member.Member;
import com.app.babybaby.entity.member.QFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

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
}
