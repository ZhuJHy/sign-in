package com.st.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankDto {

    private String name;

    private String classNumber;

    private String studentId;

    private Long totalTime;
}
