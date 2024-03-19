package com.st.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.common.R;
import com.st.dto.EveryDto;
import com.st.dto.IdDto;
import com.st.dto.RankDto;
import com.st.empty.Time;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: TimeService
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/10 14:51
 */
public interface TimeService extends IService<Time> {
    void signIn(String studentId);

    void signOut(String studentId);

//    List queryKeyInfo(String keyword, int page, int size, String startTime, String endTime);

//    List queryKeyTest(String keyword, int page, int size, String startTime, String endTime);

    EveryDto searchId(String startTime, String endTime, int page, int size, String keyword);

    List<RankDto> getRank(String startTime, String endTime);

    List<RankDto> getRank22(String startTime, String endTime);
}
