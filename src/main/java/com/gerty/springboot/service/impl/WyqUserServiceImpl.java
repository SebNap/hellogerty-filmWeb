package com.gerty.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gerty.springboot.common.Constants;
import com.gerty.springboot.controller.dto.wyqUserDTO;
import com.gerty.springboot.entity.WyqUser;
import com.gerty.springboot.exception.ServiceException;
import com.gerty.springboot.mapper.WyqUserMapper;
import com.gerty.springboot.service.IWyqUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SebNap(wyq)
 * @since 2023-04-14
 */
@Service
public class WyqUserServiceImpl extends ServiceImpl<WyqUserMapper, WyqUser> implements IWyqUserService {

    private static final Log LOG = Log.get();

    @Override
    public wyqUserDTO login(wyqUserDTO userDTO) {
        WyqUser one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }

    }

    @Override
    public WyqUser register(wyqUserDTO userDTO) {
        WyqUser one = getUserInfo(userDTO);
        if(one == null) {
            one = new WyqUser();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one); //存到数据库
        } else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return one;

    }

    private WyqUser getUserInfo(wyqUserDTO userDTO) {
        QueryWrapper<WyqUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        WyqUser one;
        try {
            one = getOne(queryWrapper); //丛数据查询数据信息
        } catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
