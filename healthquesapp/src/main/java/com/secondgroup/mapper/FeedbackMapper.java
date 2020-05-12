package com.secondgroup.mapper;

import com.secondgroup.pojo.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    List<Feedback> selectAll();

    int updateByPrimaryKey(Feedback record);

    int updateByIdAndUserId(Feedback record);

    List<Feedback> selectAllByUserId(Integer userId);
}