package com.hrc.service;

import com.hrc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    
    @Autowired
    private StudentDao studentDao;
    @Transactional
    public void changeInfo(){
        studentDao.updateAgeById(100,1);
        studentDao.updateNameById("张三",1);
    }
}
