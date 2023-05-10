package com.app.babybaby.search.board.parentsBoard;

import com.app.babybaby.type.CategoryType;
import lombok.Data;
import lombok.Getter;

@Data @Getter
public class ParentsBoardSearch {
    private String parentsBoardTitle;
    private String parentsBoardContent;
    private CategoryType categoryType;
}
