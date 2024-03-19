package com.st.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.dto.*;
import com.st.empty.Student;
import com.st.empty.Time;
import com.st.execption.ServiceException;
import com.st.mapper.StudentMapper;
import com.st.mapper.TimeMapper;
import com.st.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName: TimeServiceImpl
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/10 14:51
 */
@Slf4j
@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, Time> implements TimeService {

    static final long QUALIFIED = 10800000;

    static LocalDateTime startTime;

    static LocalDateTime endTime;

    static long lastTime;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TimeMapper timeMapper;

    @Override
    public void signIn(String studentId) {
        Student student = studentMapper.selectOne(new LambdaQueryWrapper<Student>()
                        .select(Student::getName)
                        .eq(Student::getStudentId,studentId));
        if (student == null){
            throw new ServiceException("当前学号不存在!");
        }
        if (timeMapper.selectSign(studentId) != null && timeMapper.selectSignIn(studentId) == null){
            throw new ServiceException("请勿重复签到哦!");
        }
        //获取当前是周几(1为周日，以此类推)
        DateTime dateTime = DateUtil.date();
        int day = dateToWeek(dateTime);
        log.info("今天星期:{}",day);
        //记录开始的时间
        startTime = LocalDateTime.now();
        log.info("签到时间为:{}",startTime);

        Time time = Time.builder()
                .studentId(studentId)
                .startTime(startTime)
                .weekDate(day)
                .build();

        timeMapper.insert(time);

    }

    @Override
    public void signOut(String studentId) {
        Student student = studentMapper.selectStudent(studentId);
//        Student student = studentMapper.selectOne(new LambdaQueryWrapper<Student>()
//                        .select(Student::getName)
//                        .eq(Student::getStudentId,studentId));
        if (student == null){
            throw new ServiceException("当前学号不存在!");
        }
        if (timeMapper.selectSign(studentId) == null && timeMapper.selectSignIn(studentId) == null){
            throw new ServiceException("请先签到!");
        }
        if (timeMapper.selectSign(studentId) != null && timeMapper.selectSignIn(studentId) != null){
            throw new ServiceException("请先签到!");
        }
        //记录结束的时间
        endTime = LocalDateTime.now();
        log.info("签退时间为:{}",endTime);
        startTime = (LocalDateTime) timeMapper.selectOut(studentId);

        log.info("签到时间为:{}",startTime);
        Duration duration = Duration.between(startTime,endTime);
        //记录时间差
        lastTime =duration.toMillis();
        timeMapper.updateTime(endTime,lastTime,studentId);
    }

    @Override
    public EveryDto searchId(String startTime, String endTime, int page, int size, String keyword) {


        Page<IdDto> dtoPage = new  Page<>(page,size);
        IPage<IdDto> dtoIPage = timeMapper.selectIdTole(dtoPage);

        int lists = timeMapper.sreach(keyword);

        List<IdDto> list = timeMapper.searchId((page-1) * size,size,keyword);
        log.info("关键字为:{}",keyword);
        List<TimeDto> timeDtoList = new ArrayList<>();
        EveryDto everyDto = new EveryDto();
        for (IdDto idDto : list){
            log.info("对象为{}:",idDto);
            List<WeekDto> idList = timeMapper.selectWeekDate(idDto.getStudentId(),startTime,endTime,keyword);
            log.info("当前对象为{}:",idList);
            TimeDto timeDto = new TimeDto();
            int count = 0; //合格次数
            for (int j = 0;j < idList.size();j++){
                int s = idList.get(j).getWeekdate();
                log.info("周为:{}",s);
                if (s == 1){
                    timeDto.setMonday(idList.get(j).getTimesum());
                    if ((idList.get(j).getTimesum()) != null && (Long.valueOf(idList.get(j).getTimesum())) > QUALIFIED){
                        count++;
                    }
                }else if (s == 2){
                    timeDto.setTuesday(idList.get(j).getTimesum());
                    if ((idList.get(j).getTimesum()) != null && Long.parseLong(idList.get(j).getTimesum()) > QUALIFIED){
                        count++;
                    }
                }else if (s == 3){
                    timeDto.setWednesday(idList.get(j).getTimesum());
                    if ((idList.get(j).getTimesum()) != null && Long.valueOf(idList.get(j).getTimesum()) > QUALIFIED){
                        count++;
                    }
                }else if (s == 4){
                    timeDto.setThursday(idList.get(j).getTimesum());
                    if (idList.get(j).getTimesum() != null && Long.valueOf(idList.get(j).getTimesum()) > QUALIFIED){
                        count++;
                    }
                }else if (s == 5){
                    timeDto.setFriday(idList.get(j).getTimesum());
                    if ((idList.get(j).getTimesum()) != null && Long.valueOf(idList.get(j).getTimesum()) > QUALIFIED){
                        count++;
                    }
                }else if (s == 6){
                    timeDto.setSaturday(idList.get(j).getTimesum());
                    if ((idList.get(j).getTimesum()) != null && Long.valueOf(idList.get(j).getTimesum()) > QUALIFIED){
                        count++;
                    }
                }else if (s == 7){
                    timeDto.setSunday(idList.get(j).getTimesum());
                    if ((idList.get(j).getTimesum()) != null && Long.valueOf(idList.get(j).getTimesum()) > QUALIFIED){
                        count++;
                    }
                }
            }
            TimeDto timeDto1 = timeMapper.getname(idDto.getStudentId(),keyword);
            Long totalTime = timeMapper.getTotalTime(idDto.getStudentId(),startTime,endTime,keyword);
            log.info("id为:{}",idDto.getStudentId());
            log.info("时间为{}",totalTime);
            timeDto.setName(timeDto1.getName());
            timeDto.setClassNumber(timeDto1.getClassNumber());
            timeDto.setStudentId(timeDto1.getStudentId());
            //判断是否合格
            if (count >= 4){
                timeDto.setIsStandard(true);
            }else {
                timeDto.setIsStandard(false);
            }

            timeDto.setTotalTime(totalTime);

            timeDtoList.add(timeDto);
        }

        everyDto.setTotal(lists);
        everyDto.setEveryTimeTotal(timeDtoList);
        if (dtoIPage.getPages() - dtoIPage.getCurrent() <= 0){
            everyDto.setHasNext(false);
        }else {
            everyDto.setHasNext(true);
        }
        log.info("------------------------{}",everyDto);
        return everyDto;
    }

    @Override
    public List<RankDto> getRank(String startTime, String endTime) {
        return timeMapper.getRank(startTime,endTime);
    }

    @Override
    public List<RankDto> getRank22(String startTime, String endTime) {
        return timeMapper.getRank22(startTime,endTime);
    }


    public static int dateToWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //因为数组下标从0开始，而返回的是数组的内容，是数组{1,2,3,4,5,6,7}中用1~7来表示，所以要减1
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        return week;
    }
}
