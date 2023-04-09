package com.service;

import com.model.Book;

import java.util.List;

public interface IBookService extends IGeneralService<Book>{
    public double totalPriceBook(List<Long> id);
}
