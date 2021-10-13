package com.shop.controller;

import com.shop.entity.Board;
import com.shop.entity.BoardTail;
import com.shop.repository.BoardRepository;
import com.shop.repository.BoardTailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boardtail")
public class BoardTailController {

    @Autowired
    BoardTailRepository boardTailRepository;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping("/form")
    public String form(BoardTail boardtail, long board_id) {
        Board board = boardRepository.findById(board_id).orElse(new Board());
        boardtail.setBoard(board);
        boardTailRepository.save(boardtail);
        return "redirect:/board/view?id="+board.getId();
    }

}