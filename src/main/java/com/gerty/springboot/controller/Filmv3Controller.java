package com.gerty.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.gerty.springboot.service.IFilmv3Service;
import com.gerty.springboot.entity.Filmv3;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-06-06
 */
@RestController
@RequestMapping("/filmv3")
public class Filmv3Controller {

    @Resource
    private IFilmv3Service filmv3Service;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Filmv3 filmv3) {
        return filmv3Service.saveOrUpdate(filmv3);
    }

    //删除
    @DeleteMapping("/{movie_i_d}")
    public Boolean delete(@PathVariable Integer id) {
        return filmv3Service.removeById(id);
    }

    //查询所有
    @GetMapping
    public List<Filmv3> findAll() {
        return filmv3Service.list();
    }

//    //查询一个
//    @GetMapping("/{movie_i_d}")
//    public Filmv3 findOne(@PathVariable Integer id) {
//        return filmv3Service.getById(id);
//    }

    //分页查询
    @GetMapping("/page")
    public Page<Filmv3> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(required = false) String title) {

        QueryWrapper<Filmv3> queryWrapper = new QueryWrapper<>();

        // 如果传入了电影名称，就添加到查询条件中
        if (title != null && !title.isEmpty()) {
            queryWrapper.like("title", title);
        }

        queryWrapper.orderByDesc("movie_i_d");

        return filmv3Service.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    // 搜索电影
    @GetMapping("/search")
    public List<Filmv3> search(@RequestParam String title) {
        QueryWrapper<Filmv3> queryWrapper = new QueryWrapper<>();

        // 如果传入了电影名称，就添加到查询条件中
        if (title != null && !title.isEmpty()) {
            queryWrapper.like("title", title);
        }

        return filmv3Service.list(queryWrapper);
    }


    // 获取前250名的电影
    @GetMapping("/top250")
    public List<Filmv3> getTop250() {
        QueryWrapper<Filmv3> queryWrapper = new QueryWrapper<>();

        // 按照评分降序排列，并只取前250条记录
        queryWrapper.orderByDesc("vote_average").last("limit 250");

        return filmv3Service.list(queryWrapper);
    }

    // 获取前250名的电影
    @GetMapping("/popular250")
    public List<Filmv3> getPopular250() {
        QueryWrapper<Filmv3> queryWrapper = new QueryWrapper<>();

        // 按照评分降序排列，并只取前250条记录
        queryWrapper.orderByDesc("voteCount").last("limit 250");

        return filmv3Service.list(queryWrapper);
    }


}

