package com.nar.daesogal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model){
        return "index";
    }

    @GetMapping(value = "/board/list")
    public String board(Model model){
        return "board/list";
    }

    @GetMapping(value = "/video/list")
    public String video(Model model){
        return "video/list";
    }

    @GetMapping(value = "/photo/list")
    public String photo(Model model){
        return "photo/list";
    }

//    @GetMapping(value = "/")
//    public String index(Model model){
//        model.addAttribute("loginSuccess",false);
//        return "index";
//    }

    @GetMapping(value = "/aa")
    public String aa(){
        return "aa";
    }

}