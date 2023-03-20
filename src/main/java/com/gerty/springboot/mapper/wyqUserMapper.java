package com.gerty.springboot.mapper;

import com.gerty.springboot.entity.wyqUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface wyqUserMapper {

    @Select("SELECT * from wyq_user")
    List<wyqUser> findAll();

    @Insert("INSERT INTO wyq_user" +
            "(username,password,nickname,email,phone,address) " +
            "VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
    int insert(wyqUser user);

////这样写不行，如果只写id和邮件，更新邮件，其他的会被设为空
//    @Update("update wyq_user set " +
//                "username = #{username}, " +
//                "password = #{password}, " +
//                "nickname = #{nickname}, " +
//                "email = #{email}, " +
//                "phone = #{phone}, " +
//                "address = #{address} " +
//            "where id = #{id}")
    int update(wyqUser user);

    @Delete("delete from wyq_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);
}
