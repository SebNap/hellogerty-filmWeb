package com.gerty.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gerty.springboot.entity.wyqUser;
import com.gerty.springboot.mapper.wyqUserMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class wyqUserService extends ServiceImpl<wyqUserMapper, wyqUser> {
    public boolean saveUser(wyqUser user) {

        return saveOrUpdate(user);

//        if(user.getId() == null){
//            return save(user);
//        }else{
//            return updateById(user);
//        }

    }

//    @Autowired
//    private wyqUserMapper userMapper;
//
//    public int save(wyqUser user){
//        if(user.getId() == null){//没有id就是新增的用户
//            return userMapper.insert(user);
//
//        }else {//否则为更新
//            return userMapper.update(user);
//        }
//
//    }
}
