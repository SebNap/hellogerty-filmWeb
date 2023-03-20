package com.gerty.springboot.controller;

import com.gerty.springboot.entity.wyqUser;
import com.gerty.springboot.mapper.wyqUserMapper;
import com.gerty.springboot.service.wyqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class wyqUserController {

    @Autowired
    private wyqUserMapper userMapper;

    @Autowired
    private wyqUserService userService;

    //新增和修改
    @PostMapping
    public Integer save(@RequestBody wyqUser user){
        //新增或者更新皆可
        return userService.save(user);
    }

    //查询所有数据
    @GetMapping
    public List<wyqUser> index(){
        List<wyqUser> all = userMapper.findAll();
        return all;

//        wyqUser user = new wyqUser();
//        user.setId(1);
//        return userMapper.findAll();
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);

    }
}
