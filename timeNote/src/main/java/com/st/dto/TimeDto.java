package com.st.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: TimeDto
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/12 15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {

    //姓名
    private String name;

    //学号
    private String studentId;

    //班级
    private String classNumber;

    //周一
    private String monday;

    //周二
    private String tuesday;

    //周三
    private String wednesday;

    //周四
    private String thursday;

    //周五
    private String friday;

    //周六
    private String saturday;

    //周天
    private String sunday;

    //时间总和
    private Long totalTime;

    //是否合格
    private Boolean isStandard;
}
