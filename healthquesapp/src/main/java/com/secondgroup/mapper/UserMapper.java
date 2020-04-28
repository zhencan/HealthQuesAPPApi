package com.secondgroup.mapper;

import com.secondgroup.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User queryUserLogin(Map<String, Object> paramMap);

    List<User> findByUsername(@Param("username") String username);

    int updateImageUrl(Map<String, Object> paramMap);

    int updatePassword(Map<String, Object> paramMap);

    int updateGeneralInfo(Map<String, Object> paramMap);
}