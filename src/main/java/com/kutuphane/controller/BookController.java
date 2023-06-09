package com.kutuphane.controller;

import com.kutuphane.model.Author;
import com.kutuphane.model.Book;
import com.kutuphane.service.AuthorService;
import com.kutuphane.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;


    public BookController(BookService bookService, AuthorService authorService ) {
        this.bookService = bookService;
        this.authorService = authorService;

    }


    @GetMapping("/book")
    public String viewBook(Model model) {
        List<Book> bookList = bookService.listAll();
        model.addAttribute("bookList", bookList);
        return "book/index";
    }

    @GetMapping("/savebook")
    public String getBook(@ModelAttribute("book") Book book, Model model) {
        List<Author> authorList = authorService.listAll();
        model.addAttribute("authorList", authorList);

        return "book/create";
    }

    @PostMapping("/savebook")
    public String saveBook(Book book) {
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.get(id);
        model.addAttribute("update", book);
        List<Author> authorList = authorService.listAll();
        model.addAttribute("authorList", authorList);

        return "book/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/detail/{id}")
    public String detailBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.get(id);
        model.addAttribute("book", book);
        return "book/detail";
    }

}
