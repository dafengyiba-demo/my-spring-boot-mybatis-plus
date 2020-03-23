package com.company.my.mybatisplus;

import com.company.my.mybatisplus.beans.User;
import com.company.my.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MySpringBootMybatisPlusApplicationTests {


    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试@TableId
     */
    @Test
    void test1() {
        User user = new User();
        user.setName("张三");
        user.setAge(27);
        user.setEmail("xxs@qq.com");
        user.setGender(User.Gender.female);
        int insert = userMapper.insert(user);
    }

    /**
     * 测试@Version
     */
    @Test
    void test2() {
        User user = new User();
        user.setId(5L);
        user.setName("张三");
        user.setAge(26);
        user.setVersion(1);
        user.setEmail("xxs@qq.com");
        user.setGender(User.Gender.female);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 测试@EnumValue，枚举查询转换
     */
    @Test
    void test3() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 测试@TableLogic，逻辑删除
     */
    @Test
    void test4() {
        int i = userMapper.deleteById(1234855904218648579L);
        System.out.println(i);

    }


}
