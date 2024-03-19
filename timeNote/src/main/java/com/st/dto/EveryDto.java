package com.st.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: EveryDto
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/14 10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EveryDto {

    private int total;

    private boolean hasNext;

    private List<TimeDto> everyTimeTotal;
}
