package com.fic091.factory;

import com.fic091.bean.Airplane;

//实例工厂
public class AirplaneInstanceFactory {
    public Airplane getAirplane(String jzName){
        System.out.println("实例工厂制造中");
        Airplane airplane=new Airplane();
        airplane.setFdj("太行");
        airplane.setJzName(jzName);
        airplane.setPersonNum(300);
        airplane.setYc("198.98m");
        return airplane;
    }
}
