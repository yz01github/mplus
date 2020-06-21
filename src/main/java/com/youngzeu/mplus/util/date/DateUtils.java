package com.youngzeu.mplus.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * description: []
 * title: DateUtils
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/6/16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static String getSysTime(){
        return LocalDateTime.now().format(FormatUtil.getTimeFormat());
    }

    public static String getSysDate(){
        return LocalDateTime.now().format(FormatUtil.getDateFormat());
    }
    @Test
    void test(){
        System.out.println(getSysTime());
        System.out.println(getSysDate());
    }
}
