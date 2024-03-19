package com.st.empty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: admin
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/7 22:46
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class admin {

    private Integer id;

    private String name;

    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
