package com.secondgroup.mapper;

import com.secondgroup.pojo.QuestionType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionType record);

    QuestionType selectByPrimaryKey(Integer id);

    List<QuestionType> selectAll();

    int updateByPrimaryKey(QuestionType record);
}