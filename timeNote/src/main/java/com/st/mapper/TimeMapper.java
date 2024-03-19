package com.st.mapper;

/**
 * @ClassName: TimeMapper
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/10 14:49
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.st.dto.IdDto;
import com.st.dto.RankDto;
import com.st.dto.TimeDto;
import com.st.dto.WeekDto;
import com.st.empty.Time;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TimeMapper extends BaseMapper<Time> {

    //查看是否有签退时间
    Object selectSignIn(String studentId);

    Object selectOut(String studentId);

    void updateTime(LocalDateTime endTime, long lastTime, String studentId);

    //查看是否有签到时间
    Object selectSign(String studentId);

    void updateAll(LocalDateTime endTime);

    List<WeekDto> selectWeekDate(String studentId, String startTime, String endTime, String keyword);

    List<IdDto> searchId(int page,int size,String keyword);

    TimeDto getname(String studentId,String keyword);

    Long getTotalTime(String studentId, String startTime, String endTime,String keyword);


    @Select("select student_id from student where is_delete = 0")
    IPage<IdDto> selectIdTole(Page<IdDto> dtoPage);

    int sreach(String keyword);

    List<RankDto> getRank(String startTime, String endTime);

    List<RankDto> getRank22(String startTime, String endTime);
}
