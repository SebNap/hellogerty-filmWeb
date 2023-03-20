package com.gerty.springboot.mapper;

import com.gerty.springboot.entity.wyqUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface wyqUserMapper {

    @Select("SELECT * from wyq_user")
    List<wyqUser> findAll();

}
