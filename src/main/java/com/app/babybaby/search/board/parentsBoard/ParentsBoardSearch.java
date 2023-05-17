package com.app.babybaby.search.board.parentsBoard;

import com.app.babybaby.type.CategoryType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter
public class ParentsBoardSearch {
    private CategoryType categoryType;
    private String searchTitle;
    private String searchContent;
    private String searchAll;
}
