package com.gerty.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.gerty.springboot.service.IAllv2Service;
import com.gerty.springboot.entity.Allv2;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-06-08
 */
@RestController
@RequestMapping("/allv2")
public class Allv2Controller {

    @Resource
    private IAllv2Service allv2Service;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Allv2 allv2) {
        return allv2Service.saveOrUpdate(allv2);
    }

    //删除
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return allv2Service.removeById(id);
    }

    //查询所有
    @GetMapping
    public List<Allv2> findAll() {
        return allv2Service.list();
    }

    //查询一个
    @GetMapping("/{id}")
    public Allv2 findOne(@PathVariable Integer id) {
        return allv2Service.getById(id);
    }

    //查询一个电影详情
    @GetMapping("/movie/{movieId}")
    public Allv2 findMovieDetails(@PathVariable String movieId) {
        QueryWrapper<Allv2> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_i_d", movieId);
        return allv2Service.getOne(queryWrapper);
    }


    //分页查询
    @GetMapping("/page")
    public Page<Allv2> findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize){

        QueryWrapper<Allv2> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        return allv2Service.page(new Page<>(pageNum, pageSize), queryWrapper);
    }
}

