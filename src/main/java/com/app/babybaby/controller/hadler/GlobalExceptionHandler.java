package com.app.babybaby.controller.hadler;

import com.app.babybaby.exception.BoardNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    protected RedirectView handleBoardNotFoundException(BoardNotFoundException e, HttpServletRequest request) {
//        request.getRequestURL()
        // boardlist 경로
        log.info("들어옴");
        return new RedirectView("/parentsYards/list");
    }
}
