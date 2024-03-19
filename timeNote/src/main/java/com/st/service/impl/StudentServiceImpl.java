package com.st.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.empty.Student;
import com.st.mapper.StudentMapper;
import com.st.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @ClassName: StudentServiceImpl
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/7 23:14
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {



}
