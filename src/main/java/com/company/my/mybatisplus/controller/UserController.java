package com.company.my.mybatisplus.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.my.mybatisplus.beans.User;
import com.company.my.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author qiang.luo
 * @since 2020-03-05
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;


//TODO =========================service的插入接口===========================================

    /**
     * service的基本插入接口
     */
    @PostMapping(value = "/user")
    public boolean createUser() {
        return userService.save(users().get(0));
    }

    /**
     * service的批量插入接口（一次全部插入）
     */
    @PostMapping(value = "/users-1")
    public boolean createUserBatch1() {
        return userService.saveBatch(users());
    }

    /**
     * service的批量插入接口（按照指定大小量插入）
     */
    @PostMapping(value = "/users-2")
    public boolean createUserBatch2() {
        return userService.saveBatch(users(), 1);

    }

    private List<User> users() {
        List<User> users = new ArrayList<>();
        for (int i = 3; i >= 0; i--) {
            User user = User.builder()
                    .name("张三-" + i)
                    .age(28)
                    .email("san.zhang@mail.com")
                    .gender(User.Gender.male)
                    .version(0)
                    .isDelete(0)
                    .build();
            users.add(user);
        }
        return users;
    }

//TODO =========================service的跟新或者插入接口===========================================

    /**
     * 主键id存在更新记录，否插入一条记录（跟@TableId注解不是强相关）
     */

    public boolean saveOrUpdateUser() {
        return userService.saveOrUpdate(users().get(0).setId(19L).setName("张三-3").setVersion(1));
    }

    // 根据wrapper里的逻辑进行判断，存在则进行更新（注意有没有乐观锁version），否则进行新增
    public boolean saveOrUpdateUserWithWrapper(User user, Wrapper<User> updateWrapper) {
        return userService.saveOrUpdate(user, updateWrapper);
    }


//    // 批量修改插入
//    boolean saveOrUpdateBatch(Collection<T> entityList);
//    // 批量修改插入
//    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);

//    // 根据 entity 条件，删除记录
//    boolean remove(Wrapper<T> queryWrapper);
//    // 根据 ID 删除
//    boolean removeById(Serializable id);
//    // 根据 columnMap 条件，删除记录
//    boolean removeByMap(Map<String, Object> columnMap);
//    // 删除（根据ID 批量删除）
//    boolean removeByIds(Collection<? extends Serializable> idList);

//    // 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
//    boolean update(Wrapper<T> updateWrapper);
//    // 根据 whereEntity 条件，更新记录
//    boolean update(T entity, Wrapper<T> updateWrapper);
//    // 根据 ID 选择修改
//    boolean updateById(T entity);
//    // 根据ID 批量更新
//    boolean updateBatchById(Collection<T> entityList);
//    // 根据ID 批量更新
//    boolean updateBatchById(Collection<T> entityList, int batchSize);

//    // 根据 ID 查询
//    T getById(Serializable id);
//    // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
//    T getOne(Wrapper<T> queryWrapper);
//    // 根据 Wrapper，查询一条记录
//    T getOne(Wrapper<T> queryWrapper, boolean throwEx);
//    // 根据 Wrapper，查询一条记录
//    Map<String, Object> getMap(Wrapper<T> queryWrapper);
//    // 根据 Wrapper，查询一条记录
//    <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);

//    // 查询所有
//    List<T> list();
//    // 查询列表
//    List<T> list(Wrapper<T> queryWrapper);
//    // 查询（根据ID 批量查询）
//    Collection<T> listByIds(Collection<? extends Serializable> idList);
//    // 查询（根据 columnMap 条件）
//    Collection<T> listByMap(Map<String, Object> columnMap);
//    // 查询所有列表
//    List<Map<String, Object>> listMaps();
//    // 查询列表
//    List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);
//    // 查询全部记录
//    List<Object> listObjs();
//    // 查询全部记录
//    <V> List<V> listObjs(Function<? super Object, V> mapper);
//    // 根据 Wrapper 条件，查询全部记录
//    List<Object> listObjs(Wrapper<T> queryWrapper);
//    // 根据 Wrapper 条件，查询全部记录
//    <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);


//    // 无条件翻页查询
//    IPage<T> page(IPage<T> page);
//    // 翻页查询
//    IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
//    // 无条件翻页查询
//    IPage<Map<String, Object>> pageMaps(IPage<T> page);
//    // 翻页查询
//    IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);


    /**
     * 分页查询，无条件分页
     * 分页需要注入一个PaginationInterceptor {@link com.company.my.mybatisplus.config.IPageConfig}
     */
    public IPage<User> userIPage() {
        IPage<User> iPage = new Page<>();
        iPage.setCurrent(1).setSize(4);
        return userService.page(iPage);
    }

    /**
     * 分页查询，有条件分页查询
     */
    public IPage<User> userIPageWithQueryWrapper() {
        IPage<User> iPage = new Page<>();
        iPage.setCurrent(0).setSize(3);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("version", 0);
        return userService.page(iPage, wrapper);
    }


//    // 查询总记录数
//    int count();
//    // 根据 Wrapper 条件，查询总记录数
//    int count(Wrapper<T> queryWrapper);


//    // 链式查询 普通
//    QueryChainWrapper<T> query();
//    // 链式查询 lambda 式。注意：不支持 Kotlin
//    LambdaQueryChainWrapper<T> lambdaQuery();
//
//    // 示例：
//    query().eq("column", value).one();
//    lambdaQuery().eq(Entity::getId, value).list();


    /**
     * 链式查询
     */
    public void chainQuery() {
        //链式查询
        User user = userService.query().eq("version", 0).last("limit 1").one();
        List<User> users = userService.query().eq("version", 0).list();
        System.out.println(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(users));
        //支持lambda的链式查询
        User one = userService.lambdaQuery().eq(User::getVersion, "0").last("limit 1").one();
        System.out.println(JSON.toJSONString(one));
    }


//    // 链式更改 普通
//    UpdateChainWrapper<T> update();
//    // 链式更改 lambda 式。注意：不支持 Kotlin
//    LambdaUpdateChainWrapper<T> lambdaUpdate();
//
//    // 示例：
//    update().eq("column", value).remove();
//    lambdaUpdate().eq(Entity::getId, value).update(entity);


}

