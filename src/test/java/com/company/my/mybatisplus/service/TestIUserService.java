package com.company.my.mybatisplus.service;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.company.my.mybatisplus.beans.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestIUserService {


    @Autowired
    IUserService userService;


    @Test
    void test() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", "张三-1");
        wrapper.eq("id", 9);

        User user = User.builder()
                .name("张三-2")
                .age(27)
                .gender(User.Gender.male)
                .email("san.zhang@mail.com")
                .version(3)
                .isDelete(0)
                .build();
        userService.updateA(user, wrapper);
    }

}
