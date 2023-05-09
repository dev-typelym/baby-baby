//package com.app.babybaby.domain.purchaseDTO;
//
//import com.querydsl.core.annotations.QueryProjection;
//import com.sun.istack.NotNull;
//import lombok.Getter;
//import lombok.ToString;
//import org.hibernate.annotations.ColumnDefault;
//
//import java.time.LocalDateTime;
//
//
//@Getter @ToString
//public class PurchaseDTO {
//    private Long id;
//
//    private LocalDateTime purchaseRegisterDate;
//
//    private Long purchaseCount;
//
//    private Long purchasePrice;
//
////    이벤트 제목
//    private String boardTitle;
//
////    company 이름
//    private String companyName;
//
//    @QueryProjection
//    public PurchaseDTO(Long id, LocalDateTime purchaseRegisterDate, Long purchaseCount,
//                       Long purchasePrice, String boardTitle, String companyName) {
//        this.id = id;
//        this.purchaseRegisterDate = purchaseRegisterDate;
//        this.purchaseCount = purchaseCount;
//        this.purchasePrice = purchasePrice;
//        this.boardTitle = boardTitle;
//        this.companyName = companyName;
//    }
//}
