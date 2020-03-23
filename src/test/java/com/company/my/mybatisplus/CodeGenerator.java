package com.company.my.mybatisplus;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * 代码生成配置：
 * 1.需要引入两个包：
 * 1.mybatis-plus-generator：代码生成核心包
 * 2.velocity-engine-core：模板引擎的包，根据自己项目的模板引擎引入
 * 2.创建AutoGenerator代码生成器 {@link CodeGenerator#main}
 * 3.给AutoGenerator的属性设值： {@link CodeGenerator#main}
 * 1.GlobalConfig全局配置，配置代码生成的基本位置等
 * 2.DataSourceConfig数据源
 * 3.VelocityTemplateEngine模板引擎
 * 4.PackageConfig配置包名，父包名等
 * 5.StrategyConfig配置策略，字段下划线转驼峰等，配置指定要生成代码的表，以及具体的生成策略
 * <p>
 * 备注：
 * mybatis plus的代码生成插件，建议放在测试代码中，根据需要进行代码生成，且不会被打包到生产代码中
 */
@SpringBootTest
class CodeGenerator {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;


    @Test
    void main() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        mpg.setGlobalConfig(globalConfig());

        // 数据源配置
        mpg.setDataSource(dataSourceConfig());

        //模板引擎，必须配置
        mpg.setTemplateEngine(new VelocityTemplateEngine());

        //包配置
        mpg.setPackageInfo(packageConfig());

        //策略配置
        mpg.setStrategy(strategyConfig());

        //执行代码生成
        mpg.execute();
    }

    GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        //全局基本路径配置
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("qiang.luo");
        globalConfig.setOpen(false);
        return globalConfig;
    }

    DataSourceConfig dataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverClass);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        return dataSourceConfig;
    }

    PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        return pc.setParent("com.company.my.mybatisplus")
                .setController("controller")
                .setEntity("beans")
                .setService("service")
                .setMapper("mapper")
                .setXml("mapper.xml");
    }

    StrategyConfig strategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        //设置表明转实体策略，下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //设置字段策略，下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //生成lombok风格的实体类
        strategy.setEntityLombokModel(true);
        //生产build风格的实体类
        strategy.setEntityBuilderModel(true);
        //生成rest风格的controller
        strategy.setRestControllerStyle(true);
        //需要生成代码的表
        strategy.setInclude("tb_test_generator", "tb_user");
        //指定表前缀，会自动去掉实体和类名上的前缀
        strategy.setTablePrefix("tb_");
        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

}
