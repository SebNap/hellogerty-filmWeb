package com.gerty.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gerty.springboot.entity.wyqUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface wyqUserMapper extends BaseMapper<wyqUser> {

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

    @Select("SELECT * from wyq_user where username like #{username} LIMIT #{pageNum}, #{pageSize}")
    List<wyqUser> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("select count(*) from wyq_user where username like #{username}")
    Integer countTotal(String username);
}
