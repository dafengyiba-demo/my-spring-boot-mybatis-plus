package com.company.my.mybatisplus.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.my.mybatisplus.beans.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserController {

    @Autowired
    private UserController controller;


    @Test
    void test() {
        controller.saveOrUpdateUser();
    }

    @Test
    void test1() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        //condition是控制该条件是否生效
        wrapper.eq(true, "name", "张三-1");
        wrapper.eq("id", 9);
        //如果要进行更新，entity上如果有乐观锁version记得带上版本号，否则更新失败，变成新增
        User user = User.builder()
                .name("张三-2")
                .age(27)
                .email("san.zhang@mail.com")
                .gender(User.Gender.male)
                .version(4)
                .isDelete(0)
                .build();
        boolean b = controller.saveOrUpdateUserWithWrapper(user, wrapper);
        System.out.println(b);
    }

    @Test
    void test3() {
        IPage<User> iPage = controller.userIPage();
        System.out.println(JSON.toJSONString(iPage));
    }

    @Test
    void test4() {
        IPage<User> iPage = controller.userIPageWithQueryWrapper();
        System.out.println(JSON.toJSONString(iPage));
    }

    @Test
    void test5() {
        controller.chainQuery();
    }
}
