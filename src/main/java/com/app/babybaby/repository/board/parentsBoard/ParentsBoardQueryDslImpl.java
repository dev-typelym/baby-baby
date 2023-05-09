package com.app.babybaby.repository.board.parentsBoard;

import com.app.babybaby.entity.board.event.Event;
import com.app.babybaby.entity.board.parentsBoard.ParentsBoard;
import com.app.babybaby.entity.board.parentsBoard.QParentsBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.babybaby.entity.board.parentsBoard.QParentsBoard.parentsBoard;

@RequiredArgsConstructor
public class ParentsBoardQueryDslImpl implements ParentsBoardQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<ParentsBoard> findAllWithSearch(Pageable pageable) {
//        BooleanExpression productNameEq = productSearch.getProductName() == null ? null : product.productName.eq(productSearch.getProductName());
//        BooleanExpression productPriceEq = productSearch.getProductPrice() == null ? null : product.productPrice.eq(productSearch.getProductPrice());
//        BooleanExpression productStockEq = productSearch.getProductStock() == null ? null : product.productStock.eq(productSearch.getProductStock());

//       전체 목록 불러오기(페이징)
        List<ParentsBoard> foundParentsBoard = query.select(parentsBoard)
                .from(parentsBoard)
                .join(parentsBoard.event)
                .join(parentsBoard.parentsBoardFiles)
                .fetchJoin()
                .orderBy(parentsBoard.id.desc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(parentsBoard.count())
                .from(parentsBoard)
                .fetchOne();

        return new PageImpl<>(foundParentsBoard, pageable, count);
    }

    //    상세보기
    @Override
    public Optional<ParentsBoard> findById(Long id) {

        return Optional.ofNullable(
            query.select(parentsBoard)
                    .from(parentsBoard)
                    .join(parentsBoard.event)
                    .join(parentsBoard.parentsBoardFiles)
                    .join(parentsBoard.user)
                    .join(parentsBoard.parentsBoardReplies)
                    .fetchJoin()
                    .orderBy(parentsBoard.event.id.desc())
                    .fetchOne()
        );
    }




}
