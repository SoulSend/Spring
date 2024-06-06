package com.hrc.ioc02.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SoldierDaoImp2 implements SoldierDao{

    @Override
    public void getMessage() {
        System.out.println("dao2 has bean called!");
    }
}
