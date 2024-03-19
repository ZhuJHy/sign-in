package com.st.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.empty.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: StudentMapper
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/7 23:15
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

//    String selectSignIn(String studentId);

    Student selectStudent(String studentId);
}
