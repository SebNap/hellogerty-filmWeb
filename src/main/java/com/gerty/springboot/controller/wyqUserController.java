package com.gerty.springboot.controller;

import com.gerty.springboot.entity.wyqUser;
import com.gerty.springboot.mapper.wyqUserMapper;
import com.gerty.springboot.service.wyqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping()
    public List<wyqUser> findALL() {//接口名字 /user
        List<wyqUser> all = userMapper.findAll();
        return all;
    }




    //分页查询

    //@RequestParam pageNumber = 1, pageSize = 10,
    //SELECT * from wyq_user LIMIT 0,2;
    //-- (x-1)*5

    //LIMIT 第一个参数 = (pageNum - 1)*pageSize
    //LIMIT 第二个参数 = pageSize

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){//接口名字 /user/page
        pageNum = (pageNum - 1)*pageSize;
        List<wyqUser> data = userMapper.selectPage(pageNum, pageSize);
        Integer total = userMapper.countTotal();

        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);

        return res;


    }

    //删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);

    }
}
