package com.secondgroup.mapper;

import com.secondgroup.pojo.HealthyMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HealthyMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HealthyMessage record);

    HealthyMessage selectByPrimaryKey(Integer id);

    List<HealthyMessage> selectAll();

    int updateByPrimaryKey(HealthyMessage record);

    List<HealthyMessage> selectAllByUserId(Integer userId);

    int updateQuesOption(Map<String, Object> paramMap);
}