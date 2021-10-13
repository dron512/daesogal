package com.shop.controller;

import com.shop.entity.Board;
import com.shop.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/list")
    public String board(Model model,
                        @RequestParam(required = false,defaultValue = "0")int page,
                        @RequestParam(required = false,defaultValue = "")String searchtxt) {
        int size = 5;
        Page<Board> list = boardRepository.findByTitleContainingOrContentContaining(
                searchtxt,
                searchtxt,
                PageRequest.of(page,size,
                        Sort.by(Sort.Direction.DESC, "id")
                )
        );

        model.addAttribute("startpage",1);
        model.addAttribute("curpage",(list.getPageable().getPageNumber()+1));
        model.addAttribute("endpage",list.getTotalPages());

        model.addAttribute("list", list);
        return "board/list";
    }

    @GetMapping("/view")
    public String view(long id,Model model) {
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board",board);
        return "board/view";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false, defaultValue = "0") long id) {
        Board board = boardRepository.findById(id).orElse(new Board());
        model.addAttribute("board", board);
        return "board/form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue = "0") long id) {
        System.out.println("id = "+ id);
        Board board = boardRepository.findById(id).orElse(new Board());
        board.getBoardTailList().clear();
        boardRepository.delete(board);
        return "redirect:/board/list";
    }

    @PostMapping("/form")
    public String form(Model model, @Valid Board board, BindingResult bindingResult, Authentication authentication ) {
        Board newboard = new Board();

        if(authentication != null)
            board.setName(authentication.getName());
        else
            board.setName("anonymous");

        model.addAttribute("board",board);
        if( bindingResult.hasErrors()){
            return "board/form";
        }
        long nano = System.currentTimeMillis();
        String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nano);
        board.setDate(curDate);

        System.out.println("board.getId = "+board.getId());
        if (board.getId() !=0){
            newboard = boardRepository.findById(board.getId()).orElse(board);
            newboard.setTitle(board.getTitle());
            newboard.setContent(board.getContent());
            newboard.setName(board.getName());
            newboard.setDate(board.getDate());
            newboard.setContent(board.getContent());
            newboard.setId(board.getId());
            boardRepository.save(newboard);
        }
        else {
            boardRepository.save(board);
        }
        return "redirect:/board/list";
    }

}