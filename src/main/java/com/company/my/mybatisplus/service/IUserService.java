package com.company.my.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.company.my.mybatisplus.beans.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author qiang.luo
 * @since 2020-03-05
 */
public interface IUserService extends IService<User> {

    void updateA(User user, Wrapper<User> updateWrapper);
}
