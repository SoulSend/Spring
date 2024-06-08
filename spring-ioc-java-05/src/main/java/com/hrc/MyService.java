package com.hrc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    private MyDao dao;
    public List<Student> getMessage(){
       return dao.getMessage();
    }
}
