package com.st.empty;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: Student
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/7 19:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("student")
public class Student {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String classNumber;

    private String studentId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // 1 表示删除 0表示未删除
    private Integer isDelete;
}
