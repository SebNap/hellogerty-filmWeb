package com.gerty.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gerty.springboot.entity.wyqUser;
import com.gerty.springboot.mapper.wyqUserMapper;
import com.gerty.springboot.service.wyqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@RestController
@RequestMapping("/user")
public class wyqUserController {

    @Autowired
    private wyqUserMapper userMapper;

    @Autowired
    private wyqUserService userService;

    //新增和修改
    @PostMapping
    public boolean save(@RequestBody wyqUser user){
        //新增或者更新皆可
        return userService.saveUser(user);

    }

    //查询所有数据
    @GetMapping()
    public List<wyqUser> findALL() {//接口名字 /user
//        List<wyqUser> all = userMapper.findAll();
//        return all;
        return userService.list();
    }




    //分页查询
    //@RequestParam pageNumber = 1, pageSize = 10,
    //SELECT * from wyq_user LIMIT 0,2;
    //-- (x-1)*5

    //LIMIT 第一个参数 = (pageNum - 1)*pageSize
    //LIMIT 第二个参数 = pageSize

//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username
//    ){//接口名字 /user/page
//        pageNum = (pageNum - 1)*pageSize;
//        username ="%" + username + "%";
//
//        List<wyqUser> data = userMapper.selectPage(pageNum, pageSize, username);
//        Integer total = userMapper.countTotal(username);
//
//        Map<String, Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//
//        return res;
//
//    }

    //分页查询MYbatis - Plus
    @GetMapping("/page")
    public IPage<wyqUser> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String username
    ){//接口名字 /user/page

        IPage<wyqUser> page = new Page<>(pageNum, pageSize);
        QueryWrapper<wyqUser> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }

        return userService.page(page, queryWrapper);

    }



    //删除数据
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);

    }
//    @DeleteMapping("/{id}")
//    public Integer delete(@PathVariable Integer id){
//        return userMapper.deleteById(id);
//
//    }



}
