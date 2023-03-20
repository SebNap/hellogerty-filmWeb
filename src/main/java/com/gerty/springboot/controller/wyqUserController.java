package com.gerty.springboot.controller;


import com.gerty.springboot.entity.wyqUser;
import com.gerty.springboot.mapper.wyqUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class wyqUserController {

    @Autowired
    private wyqUserMapper userMapper;

    @GetMapping("/")
    public List<wyqUser> index(){
        List<wyqUser> all = userMapper.findAll();
        return all;

//        wyqUser user = new wyqUser();
//        user.setId(1);
//        return userMapper.findAll();
    }
}
