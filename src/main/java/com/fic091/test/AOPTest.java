package com.fic091.test;

import com.fic091.aop.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

    public static void main(String[] args) {
        ApplicationContext ioc=new ClassPathXmlApplicationContext("AOP-config.xml");
        Calculator bean = ioc.getBean(Calculator.class);
        bean.add(2,1);
    }
}
