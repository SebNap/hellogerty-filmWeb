package com.gerty.springboot.service.impl;

import com.gerty.springboot.entity.Likev1;
import com.gerty.springboot.mapper.Likev1Mapper;
import com.gerty.springboot.service.ILikev1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-06-10
 */
@Service
public class Likev1ServiceImpl extends ServiceImpl<Likev1Mapper, Likev1> implements ILikev1Service {
//    @Autowired
//    private Likev1Mapper likev1Mapper;
//
//    @Transactional
//    public Page<Likev1> findPage(Integer pageNum, Integer pageSize, String nickname) {
//        QueryWrapper<Likev1> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("nickname", nickname);
//
//        return likev1Service.page(new Page<>(pageNum, pageSize), queryWrapper);
//    }

}
