package com.st;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.st.empty.Time;
import com.st.mapper.TimeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: test
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/7 23:41
 */
@Slf4j
public class test {
    static java.sql.Date endTime;


    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        cld.setTimeInMillis(System.currentTimeMillis());//当前时间

        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
        System.out.println(df.format(cld.getTime()));

        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
        System.out.println(df.format(cld.getTime()));
    }
}
