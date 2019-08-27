package com.min.spring.util;

import java.util.TimeZone;

public class test {
    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone.getRawOffset()/ (60*60*1000));
    }
}
