package com.secondgroup.mapper;

import com.secondgroup.pojo.QuestionnaireTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionnaireTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionnaireTemplate record);

    QuestionnaireTemplate selectByPrimaryKey(Integer id);

    List<QuestionnaireTemplate> selectAll();

    int updateByPrimaryKey(QuestionnaireTemplate record);

}