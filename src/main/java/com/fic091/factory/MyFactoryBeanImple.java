package com.fic091.factory;

import com.fic091.bean.Book;
import org.springframework.beans.factory.FactoryBean;

import java.util.UUID;

public class MyFactoryBeanImple implements FactoryBean<Book> {
    @Override
    public Book getObject() throws Exception {
        Book book=new Book();
        book.setBookName(UUID.randomUUID().toString());
        book.setAuthor("jiebao");
        return book;
    }

    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
