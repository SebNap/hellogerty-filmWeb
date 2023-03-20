package com.gerty.springboot;


import com.gerty.springboot.entity.wyqUser;
import com.gerty.springboot.mapper.wyqUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class SpringbootApplication {

    @Autowired
    private wyqUserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @GetMapping("/")
    public List<wyqUser> index(){
        wyqUser user = new wyqUser();
        user.setId(1);

        return userMapper.findAll();
    }


}
