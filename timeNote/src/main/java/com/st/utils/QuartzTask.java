package com.st.utils;

import com.st.empty.Time;
import com.st.mapper.TimeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: QuartzTask
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/12 21:06
 */
@Component
@Slf4j
public class QuartzTask {

    @Resource
    private TimeMapper timeMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void quartzTask(){
        LocalDateTime endTime = LocalDateTime.now();
        //一小时等于3600000毫秒
        timeMapper.updateAll(endTime);

        log.info("定时任务{},",new Date());
    }
}
