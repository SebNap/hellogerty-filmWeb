package com.gerty.springboot.service.impl;

import com.gerty.springboot.entity.WyqUser;
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

}
