package com.fic091.factory;

import com.fic091.bean.Airplane;

//静态工厂
public class AirplaneStaticFactory {
    public static Airplane getAirplane(String jzName){
        System.out.println("静态工厂制造中");
        Airplane airplane=new Airplane();
        airplane.setFdj("太行");
        airplane.setJzName(jzName);
        airplane.setPersonNum(300);
        airplane.setYc("198.98m");
        return airplane;
    }
}
