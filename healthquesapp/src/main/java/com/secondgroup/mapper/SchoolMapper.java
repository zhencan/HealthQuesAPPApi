package com.secondgroup.mapper;

import com.secondgroup.pojo.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(School record);

    School selectByPrimaryKey(Integer id);

    List<School> selectAll();

    int updateByPrimaryKey(School record);
}