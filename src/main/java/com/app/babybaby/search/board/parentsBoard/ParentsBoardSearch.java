package com.app.babybaby.search.board.parentsBoard;

import com.app.babybaby.type.CategoryType;
import com.app.babybaby.type.SearchTextOption;
import lombok.Data;
import lombok.Getter;

@Data @Getter
public class ParentsBoardSearch {
    private String searchText;
    private CategoryType categoryType;
    private SearchTextOption searchTextOption;
}
