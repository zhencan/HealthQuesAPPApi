package com.secondgroup.mapper;

import com.secondgroup.pojo.QuestionRadio;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionRadioMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionRadio record);

    QuestionRadio selectByPrimaryKey(Integer id);

    List<QuestionRadio> selectAll();

    int updateByPrimaryKey(QuestionRadio record);

    List<QuestionRadio> selectAllByTemplateId(Integer quesTplId);
}