package com.st.controller;

import com.st.common.R;
import com.st.dto.EveryDto;
import com.st.dto.RankDto;
import com.st.execption.ServiceException;
import com.st.ipregin.Ip;
import com.st.service.TimeService;
import com.st.utils.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName: TimeController
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/10 14:53
 */
@Api(tags = "学生签到签退模块")
@Slf4j
@RestController
public class TimeController {

    @Autowired
    private TimeService timeService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    static String lockId;
    long seconds = 1L;

    static String IP1 = "59.48.111.141";

    static String IP2 = "59.48.111.138";


    // 双体系ip：59.48.111.141,59.48.111.138
    @RequestLimiter(QPS = 50,timeout = 200,timeunit = TimeUnit.MINUTES,msg = "玩命加载中....请稍后")
    @PostMapping("/student/signIn")
    public R<?> signIn(String studentId) throws InterruptedException {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        log.info("签到地址为:------{}",ip);
        if (!CommonUtils.isAllowed(ip)){
            throw new ServiceException("请在双体内签到");
        }
        lockId = "lock_" + studentId;
        SimpleRedisLock redisLock = new SimpleRedisLock(lockId,stringRedisTemplate);
        boolean isLock = redisLock.tryLock(lockId,60);
        if (!isLock){
            //获取锁失败，返回错误信息
            throw new ServiceException("操作过于频繁!");
        }
        try {
            log.info("加锁成功:{}",lockId);
            timeService.signIn(studentId);
        }finally {
            redisLock.unlock(lockId);
        }
        return R.succ(201,"签到成功!",null,"Java服务器");
    }

    @RequestLimiter(QPS = 50,timeout = 200,timeunit = TimeUnit.MINUTES,msg = "玩命加载中....请稍后")
    @PutMapping("/student/signOut")
    public R<?> signOut(String studentId) throws InterruptedException {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        log.info("签到地址为:------{}",ip);
        if (!CommonUtils.isAllowed(ip)){
            throw new ServiceException("请在双体内签退");
        }
        lockId = "lock_" + studentId;
        SimpleRedisLock redisLock = new SimpleRedisLock(lockId,stringRedisTemplate);
        boolean isLock = redisLock.tryLock(lockId,60);
        if (!isLock){
            throw new ServiceException("操作过于频繁!");
        }
        try {
            log.info("加锁成功:{}",lockId);
            timeService.signOut(studentId);
        }finally {
            redisLock.unlock(lockId);
        }
        return R.succ(201,"签退成功!",null,"Java服务器");
    }

    /**
     * 查询所有人的周一到周日的时间
    */
    @PostMapping("/queryKeyInfo")
    public R<?> queryKeyInfo(Integer page, Integer size, String startTime, String endTime, String keyword){
        if (page == null && size == null){
            page = 1;
            size = 10;
        }
        if (startTime == null && endTime == null){
            startTime = DateUtil.getDateStart();
            endTime = DateUtil.getDateEnd();
        }
        EveryDto everyDto = timeService.searchId(startTime,endTime,page,size,keyword);
        return R.succ(everyDto);
    }

    @GetMapping("/ping")
    public R<?> ping(){
        log.info("请求过来了-----------");
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        log.info("请求ip为-------->{}",ip);
        return R.succ(201,"欢迎使用双体系签到系统-Java服务器运行正常！",null,"Java服务器");
    }

    //查询21排行榜
    @GetMapping("/getRanking")
    public R<?> getRanking(String startTime,String endTime){
        if (startTime == null && endTime == null){
            startTime = DateUtil.getDateStart();
            endTime = DateUtil.getDateEnd();
        }
        List<RankDto> rankDtoList = timeService.getRank(startTime,endTime);
        return R.succ(rankDtoList);
    }

    //查询22榜单
    @GetMapping("/getRanking22")
    public R<?> getRanking22(String startTime,String endTime){
        if (startTime == null && endTime == null){
            startTime = DateUtil.getDateStart();
            endTime = DateUtil.getDateEnd();
        }
        List<RankDto> rankDtoList = timeService.getRank22(startTime,endTime);
        return R.succ(rankDtoList);
    }
}
