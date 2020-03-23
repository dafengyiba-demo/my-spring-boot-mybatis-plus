package com.company.my.mybatisplus.beans;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 重要的注解：
 * 1.@TableName
 * 1.指定表名，默认是实体类驼峰转下换线
 * 2.可以配置全局表名前缀，详见application.yaml的table-prefix属性
 * 2.@TableId
 * 1.主键注解
 * 2.主键生成策略，如果是数据库自增，则数据必须先设置成自增
 * 3.如果是其他策略，则按照其他策略生成主键，并设置到该属性
 * 3.@TableField
 * 1.字段注解，可以定制该字段的类型，是否属于数据库字段等
 * 4.@Version
 * 1.标识该字段为乐观锁字段
 * 2.进行update操作时，带上从数据查询出来的字段值，如果版本一致，更新成功返回更新成功的数据条数且自动升级版本，如果更新失败，返回更新成功条数为0
 * 3.需要注入OptimisticLockerInterceptor
 * 5.@EnumValue
 * 1.数据库枚举类型和Java枚举类型映射
 * 2.使用方法：数据库定义的枚举类型和代码中定义相同的枚举类型，且给映射的字段加上该注解，然后查询、更新、新增都会做自动映射
 * 3.踩坑：数据库的值一定要是跟Java一致的数据，不能为空，否则会报错
 * 6.@TableLogic
 * 1.逻辑删除字段，被该注解标识的字段，当进行删除时，会将该字段更新成被逻辑删除的值，不会真是删掉，该字段需要指定删除标识和未删除的标识值
 * 7.@SqlParser
 * <p>
 * 8.@KeySequence
 * 1.oracle数据库的
 * <p>
 * <p>
 * 1.注解@TableName：
 * 重要的属性：
 * 1.value：实体对应的表名
 * 2.schema：对应的数据库名称（本例中为jdbc:mysql://localhost:3306/mybatis-plus中的mybatis-plus）
 * 3.keepGlobalPrefix：是否使用全局的表名前缀,(此版本，只需要设置全局前缀就会生效)
 * 4.excludeProperty：需要排除的字段
 * 5.resultMap：实体映射结果集，只有mybatis plus自动注入的方法才生效
 *
 * @author qiang.luo
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * <p>
     * 注解@TableId：
     * 1.value：注解的名称，注意驼峰命名法，这个值可以缺省
     * 2.type：主键类型
     * 1.IdType.AUTO：数据库自增id，数据库必须设置为自增
     * 2.IdType.NONE：自动分配
     * 3.IdType.INPUT：insert前手动插入id
     * 4.IdType.ASSIGN_ID：自动分配一个id
     * 5.IdType.ASSIGN_UUID：自动分配一个uid
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     * <p>
     * 注解@TableField：
     * 1.value：字段名
     * 2.exist：是否为数据库表字段
     * 3.keepGlobalFormat：是否使用全局的格式化器，只有设置了全局格式化器的时候生效
     * 4.jdbcType：jdbc的类型
     * 5.typeHandler：类型转换处理器
     * 6.numericScale：指定保留到小数后多少位
     */
    @TableField
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 版本
     * <p>
     * 注解@Version：
     * 1.标识该字段为乐观锁字段,需要注入一个bean： OptimisticLockerInterceptor
     */
    @Version
    private Integer version;

    /**
     * 性别
     * <p>
     * 进行枚举转换
     */
    private Gender gender;

    /**
     * 是否被删除
     * <p>
     * 处理逻辑删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;


    public enum Gender {

        male("male"),
        female("female");

        @EnumValue
        private String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        public String getValue() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
