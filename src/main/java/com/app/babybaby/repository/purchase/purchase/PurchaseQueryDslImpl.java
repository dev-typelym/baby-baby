//package com.app.babybaby.repository.purchase.purchase;
//
//import com.app.babybaby.entity.member.Member;
//import com.app.babybaby.domain.purchaseDTO.PurchaseDTO;
//import com.app.babybaby.entity.purchase.QPurchaseDTO;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.data.domain.SliceImpl;
//
//import java.util.List;
//
//import static com.app.babybaby.entity.purchase.purchase.QPurchase.purchase;
//
//@RequiredArgsConstructor
//public class PurchaseQueryDslImpl implements PurchaseQueryDsl {
//    private final JPAQueryFactory query;
//
//    @Override
//    public Slice<PurchaseDTO> findAllByUserWithDetail_QueryDSL(Pageable pageable, Member member) {
//        List<PurchaseDTO> purchaseDTOList = query.select(new QPurchaseDTO(
//                purchase.id,
//                purchase.purchaseRegisterDate,
//                purchase.purchaseCount,
//                purchase.purchasePrice,
//                purchase.event.boardTitle,
//                purchase.event.company.memberName
//        ))
//                .from(purchase)
//                .where(purchase.member.eq(member))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        return new SliceImpl<>(purchaseDTOList);
//    }
//}
