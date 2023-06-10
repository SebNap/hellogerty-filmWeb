package com.gerty.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.gerty.springboot.service.ICommentv1Service;
import com.gerty.springboot.entity.Commentv1;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/commentv1")
public class Commentv1Controller {

    @Resource
    private ICommentv1Service commentv1Service;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Commentv1 commentv1) {
        return commentv1Service.saveOrUpdate(commentv1);
    }


    // 创建一个新的评论
    @PostMapping("/makemovie/{movieId}")
    public boolean createCommentForMovie(@PathVariable String movieId, @RequestBody Commentv1 commentv1) {
        commentv1.setMovieID(movieId); // 设置评论所属的电影 ID
        return commentv1Service.saveOrUpdate(commentv1);
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return commentv1Service.removeById(id);
    }

    //查询所有
    @GetMapping
    public List<Commentv1> findAll() {
        return commentv1Service.list();
    }

    //查询一个
    @GetMapping("/{id}")
    public Commentv1 findOne(@PathVariable Integer id) {
        return commentv1Service.getById(id);
    }

    //查询某一部电影的所有评论
    @GetMapping("/movie/{movieId}")
    public List<Commentv1> findByMovieId(@PathVariable String movieId) {
        QueryWrapper<Commentv1> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_i_d", movieId);
        return commentv1Service.list(queryWrapper);
    }

    //分页查询
    @GetMapping("/page")
    public Page<Commentv1> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize){

        QueryWrapper<Commentv1> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        return commentv1Service.page(new Page<>(pageNum, pageSize), queryWrapper);
    }
}

