package com.app.babybaby.service.admin.adminAskService;


import com.app.babybaby.domain.adminDTO.AdminAskDTO;
import com.app.babybaby.entity.board.ask.Ask;
import com.app.babybaby.repository.board.ask.AskRepository;
import com.app.babybaby.search.admin.AdminAskSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class AdminAskServiceImpl implements AdminAskService {

    @Autowired
    private AskRepository askRepository;


    //    관리자 문의 목록
    @Override
    public Page<AdminAskDTO> getAdminAskListWithPaging(int page, AdminAskSearch adminAskSearch) {
        Page<Ask> asks = askRepository.findAllAsk_queryDSL(PageRequest.of(page, 5), adminAskSearch);
        List<AdminAskDTO> adminAskDTOS = asks.getContent().stream()
                .map(this::toAskDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(adminAskDTOS, asks.getPageable(), asks.getTotalElements());
    }

    //    관리자 문의 상세
    @Override
    public List<AdminAskDTO> getAdminAskDetail() {
        List<Ask> announcementDetail = askRepository.findAllAskDetail_queryDSL();
        List<AdminAskDTO> adminaAskDetailDTOS = announcementDetail.stream()
                .map(this::toAskDTO)
                .collect(Collectors.toList());
        return adminaAskDetailDTOS;
    }

    //  관리자 문의 삭제
    @Override
    public void deleteAdminAsk(List<String> askIds) {
        askIds.stream().map(askId -> Long.parseLong(askId)).forEach(askRepository::deleteAskByIds_queryDSL);
    }
}
