package com.service;

import com.model.Book;

import java.util.Optional;

public interface IGeneralService<B>{
    Iterable<B> findAll();
    Optional<Book> findById(Long id);
    B save(B b);
    void remove(Long id);
}
