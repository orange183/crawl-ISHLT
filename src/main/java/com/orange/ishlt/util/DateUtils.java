package com.orange.ishlt.util;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    /**
     * @param enTime e.g. Apr 10 2024 10:00AM
     *               e.g. Apr 9 2024 8:00AM
     *               e.g. Apr 9 2024 8:00AM
     *               e.g. 4/9/2024 8:00:00 AM
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime convert(String enTime) {
        LocalDateTime parse;
        enTime = StrUtil.replace(enTime, "  ", " "); // 将两个空格变为一个空格
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);
            parse = LocalDateTime.parse(enTime, formatter);
        } catch (Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm:ss a", Locale.ENGLISH);
            parse = LocalDateTime.parse(enTime, formatter);
        }
        return parse;
    }
}
