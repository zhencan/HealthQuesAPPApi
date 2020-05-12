/**
 * FileName: SchoolService
 * Author:   ZSX
 * Date:     2020/4/13 22:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.secondgroup.service;

import com.secondgroup.mapper.SchoolMapper;
import com.secondgroup.pojo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ZSX
 * @create 2020/4/13
 * @since 1.0.0
 */
@Service
public class SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    public School getSchoolById(Integer schoolId) {
        return schoolMapper.selectByPrimaryKey(schoolId);
    }
}