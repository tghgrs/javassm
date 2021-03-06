package com.fic091.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void updateBalance(String userName, int price){
        String sql="UPDATE account SET balance=balance-? WHERE username=?";
        jdbcTemplate.update(sql,price,userName);
    }

    public int getPrice(String isbn){
        String sql="SELECT price FROM book WHERE isbn=?";
        Integer price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        System.out.println(price);
        return price;
    }

    public void updateStock(String isbn){
        String sql="UPDATE book_stock SET stock=stock-1 WHERE isbn=?";
        jdbcTemplate.update(sql,isbn);
    }
}
