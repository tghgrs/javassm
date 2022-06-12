package com.fic091.service;

import com.fic091.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    public void checkout(String username, String isbn){
        bookDao.updateStock(isbn);
        int price = bookDao.getPrice(isbn);
        bookDao.updateBalance(username, price);
    }
}
