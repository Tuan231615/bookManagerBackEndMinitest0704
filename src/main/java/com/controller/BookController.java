package com.controller;

import com.model.Book;
import com.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Book>> listBook(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Book> createNewBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            book.setId(bookOptional.get().getId());
            return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            bookService.remove(id);
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/")
    public ResponseEntity<String> totalPriceBook(@RequestParam List<Long> id){
        return new ResponseEntity<>(String.valueOf(bookService.totalPriceBook(id)), HttpStatus.OK);
    }
}
