package com.company.my.mybatisplus;

import com.company.my.mybatisplus.beans.User;
import com.company.my.mybatisplus.config.IPageConfig;
import com.company.my.mybatisplus.controller.UserController;
import com.company.my.mybatisplus.service.impl.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 知识大纲：
 *  1.安装、配置----见下面整合详细步骤
 *  2.注解 ---- 详见{@link User}
 *  3.核心功能之：代码生成器 ---- 详见{@link CodeGenerator}
 *  4.核心功能之：CRUD接口
 *      1.service的CRUD：{@link UserController}
 *      2.mapper的CRUD： {@link UserServiceImpl}
 *  5.条件构造器
 *  6.分页插件  {@link IPageConfig}
 *
 *
 * 整合的详细步骤：
 * <p>
 * 1.引入mybatis plus:
 *      <dependency>
 *          <groupId>com.baomidou</groupId>
 *          <artifactId>mybatis-plus-boot-starter</artifactId>
 *          <version>3.3.1.tmp</version>
 *      </dependency>
 * 2.如果是需要引入快照版本，还需添加maven仓库：
 *      <repository>
 *          <id>snapshots</id>
 *          <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
 *      </repository>
 * 3.引入mysql的连接驱动：
 *      <dependency>
 *          <groupId>mysql</groupId>
 *          <artifactId>mysql-connector-java</artifactId>
 *      </dependency>
 * 4.在application.yaml中添加数据库配置：
 *  spring:
 *      datasource:
 *          url: jdbc:mysql://localhost:3306/mybatis-plus
 *          username: root
 *          password: 123456
 * 整合完成
 * ===========================================================================================
 */


@SpringBootApplication
/**
 * 配置包扫描，该路径下的文件会被作为mapper扫描到mybatis的容器中
 */
@MapperScan({"com.company.my.mybatisplus.mapper"})
public class MySpringBootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootMybatisPlusApplication.class, args);
    }

}
