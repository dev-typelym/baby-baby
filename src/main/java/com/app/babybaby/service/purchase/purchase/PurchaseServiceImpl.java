package com.app.babybaby.service.purchase.purchase;

import com.app.babybaby.domain.boardDTO.parentsBoardDTO.ParentsBoardDTO;
import com.app.babybaby.domain.purchaseDTO.purchaseDTO.PurchaseDTO;
import com.app.babybaby.entity.purchase.purchase.Purchase;
import com.app.babybaby.repository.purchase.purchase.PurchaseRepository;
import com.app.babybaby.search.board.parentsBoard.ParentsBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private final PurchaseRepository purchaseRepository;

    @Override
    public Page<PurchaseDTO> findAllByMemberPaymentWithPage(Pageable pageable, Long memberId) {
        Page<Purchase> purchases = purchaseRepository.findAllByMemberPaymentWithPage_QueryDSL(pageable, memberId);
        List<PurchaseDTO> purchaseDTOS = purchases.stream().map(purchase -> PurchaseToDTO(purchase)).collect(Collectors.toList());

        return new PageImpl<>(purchaseDTOS,pageable,purchases.getTotalElements());
    }

//    구매 상세
    @Override
    public PurchaseDTO findMemberIdByPaymentDetail(Long purchaseId) {
        Purchase purchase = purchaseRepository.findMemberIdByPaymentDetail_QueryDSL(purchaseId);
        PurchaseDTO purchaseDTO = PurchaseToDTO(purchase);
        return purchaseDTO;
    }

}
