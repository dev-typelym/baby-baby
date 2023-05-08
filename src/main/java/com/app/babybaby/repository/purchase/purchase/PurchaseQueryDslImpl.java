package com.app.babybaby.repository.purchase.purchase;

import com.app.babybaby.entity.board.event.QEvent;
import com.app.babybaby.entity.purchase.PurchaseDTO;
import com.app.babybaby.entity.purchase.QPurchaseDTO;
import com.app.babybaby.entity.purchase.purchase.QPurchase;
import com.app.babybaby.entity.user.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.babybaby.entity.board.event.QEvent.event;
import static com.app.babybaby.entity.purchase.purchase.QPurchase.purchase;

@RequiredArgsConstructor
public class PurchaseQueryDslImpl implements PurchaseQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<PurchaseDTO> findAllByUserWithDetail_QueryDSL(Pageable pageable, User user) {
        List<PurchaseDTO> purchaseDTOList = query.select(new QPurchaseDTO(
                purchase.id,
                purchase.purchaseRegisterDate,
                purchase.purchaseCount,
                purchase.purchasePrice,
                purchase.event.boardTitle,
                purchase.event.company.userName
        ))
                .from(purchase)
                .where(purchase.user.eq(user))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new SliceImpl<>(purchaseDTOList);
    }
}
