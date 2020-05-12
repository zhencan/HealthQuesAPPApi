package com.secondgroup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class HealthquesappApplicationTests {

    //DI注入数据源
//    @Autowired
//    DataSource dataSource;
//
//    @Test
//    public void contextLoads() throws SQLException {
//        //看一下默认数据源
//        System.out.println(dataSource.getClass());
//        //获得连接
//        Connection connection =   dataSource.getConnection();
//        System.out.println(connection);
//        //关闭连接
//        connection.close();
//
//    }

//    @Test
//    public void contextLoads(){
        //获取classes目录绝对路径
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//
//        String path1 = null;
//        try {
//            path1 = ResourceUtils.getURL("classpath:").getPath();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        //一样的
//        System.out.println(path);
//        System.out.println(path1);

//    }

    @Test
    public void contextLoads(){

    }

}
