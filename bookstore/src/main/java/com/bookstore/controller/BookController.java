package com.bookstore.controller;

import com.bookstore.repository.BookRepository;
import com.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String viewBooks(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Book> books = (keyword != null && !keyword.isEmpty())
                ? bookRepository.findByTitleContainingIgnoreCase(keyword)
                : bookRepository.findAll();

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "index";
    }
}

