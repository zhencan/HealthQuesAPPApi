package com.secondgroup.mapper;

import com.secondgroup.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    int selectOccupyMessage(Integer userId, Integer isOccupy);

    List<Message> selectAllMessage(Integer userId);

    int updateOccupyMessage(Integer id, Integer atUpdate);
}