package com.app.babybaby.service.member.member;

import com.app.babybaby.domain.boardDTO.eventDTO.EventDTO;
import com.app.babybaby.domain.boardDTO.reviewDTO.ReviewDTO;
import com.app.babybaby.domain.memberDTO.CompanyDTO;
import com.app.babybaby.entity.member.Member;
import com.app.babybaby.repository.board.event.EventRepository;
import com.app.babybaby.repository.board.review.ReviewRepository;
import com.app.babybaby.repository.member.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    private final EventRepository eventRepository;

    @Override
    public Optional<Member> getMemberById(Long memberId) {
        return memberRepository.findById(memberId);

    }

    @Override
    public CompanyDTO getAllMemberInfo(Long companyId) {
       Member member = memberRepository.findById(companyId).get();
       CompanyDTO companyDTO = toCompanyDTO(member);
       companyDTO.getEvents().stream();
       return companyDTO;
    }
}
