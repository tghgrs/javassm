package com.fic091.test;

import com.fic091.bean.Airplane;
import com.fic091.bean.Book;
import com.fic091.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    public static void main(String[] args) {
        ApplicationContext ioc=new ClassPathXmlApplicationContext("spring-config3.xml");
        Object object=ioc.getBean("bookServlet");
    }
}
