package com.app.babybaby.domain.adminDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDataDTO {
    private String answerTitle;
    private String answerContent;
    private String askId;
}
