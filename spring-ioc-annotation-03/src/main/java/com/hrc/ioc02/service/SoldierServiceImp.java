package com.hrc.ioc02.service;

import com.hrc.ioc02.dao.SoldierDao;
import com.hrc.ioc02.dao.SoldierDaoImp;
import com.hrc.ioc02.dao.SoldierDaoImp2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//放入ioc容器里面
@Service
public class SoldierServiceImp implements SoldierService{
    //更具要注入的bean的类型在ioc容器里面查找，找到就注入
    @Autowired
    private SoldierDaoImp2 dao;
    @Override
    public void getMessage() {
        dao.getMessage();
    }
}
