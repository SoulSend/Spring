package com.hrc.ioc02.dao;

import com.hrc.ioc02.service.SoldierService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class SoldierDaoImp implements SoldierDao {
    @Override
    public void getMessage() {
        System.out.println("dao has bean called");
    }
}
