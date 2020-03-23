package com.company.my.mybatisplus.service.impl;

import com.company.my.mybatisplus.beans.TestGenerator;
import com.company.my.mybatisplus.mapper.TestGeneratorMapper;
import com.company.my.mybatisplus.service.ITestGeneratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qiang.luo
 * @since 2020-03-05
 */
@Service
public class TestGeneratorServiceImpl extends ServiceImpl<TestGeneratorMapper, TestGenerator> implements ITestGeneratorService {

}
