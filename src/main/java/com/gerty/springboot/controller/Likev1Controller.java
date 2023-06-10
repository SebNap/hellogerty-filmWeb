package com.gerty.springboot.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.gerty.springboot.service.ILikev1Service;
import com.gerty.springboot.entity.Likev1;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-06-10
 */
@RestController
@RequestMapping("/likev1")
public class Likev1Controller {

    @Resource
    private ILikev1Service likev1Service;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Likev1 likev1) {
        return likev1Service.saveOrUpdate(likev1);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Likev1 likev1) {
        return likev1Service.save(likev1);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id, @RequestParam String nickname) {
        QueryWrapper<Likev1> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_i_d", id);
        queryWrapper.eq("nickname", nickname);
        return likev1Service.remove(queryWrapper);
    }


//    //删除
//    @DeleteMapping("/{id}")
//    public Boolean delete(@PathVariable Integer id) {
//        return likev1Service.removeById(id);
//    }

    //查询所有
    @GetMapping
    public List<Likev1> findAll() {
        return likev1Service.list();
    }

    //查询一个
    @GetMapping("/{id}")
    public Likev1 findOne(@PathVariable Integer id) {
        return likev1Service.getById(id);
    }

    //查询
    @GetMapping("/search")
    public List<Likev1> findPage(@RequestParam(required = false) String nickname)
    {
        QueryWrapper<Likev1> queryWrapper = new QueryWrapper<>();
        if (nickname != null && !nickname.isEmpty()) {
            queryWrapper.like("nickname", nickname);
        }
        return likev1Service.list(queryWrapper);
    }

}

