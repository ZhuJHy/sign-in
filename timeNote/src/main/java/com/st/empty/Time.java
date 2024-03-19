package com.st.empty;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import java.time.LocalDateTime;

/**
 * @ClassName: Time
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/10 8:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sign_time")
public class Time {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String studentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    private BigInteger lastTime;

    private Integer weekDate;
}
