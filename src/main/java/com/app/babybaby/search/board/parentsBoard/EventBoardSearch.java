package com.app.babybaby.search.board.parentsBoard;

import com.app.babybaby.type.CategoryType;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class EventBoardSearch {
    private String boardTitle;
    private String boardContent;
    private CategoryType categoryType;
}
