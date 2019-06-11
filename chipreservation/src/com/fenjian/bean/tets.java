package com.fenjian.bean;


import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * Hello world!
 * @author jim
 * @date 2017/11/25
 */
public class tets {
    public static void main(String[] args) {

        // 精确到毫秒
        // 获取指定格式的时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // 输出字符串
        System.out.println(df.format(new Date()));
       
    }
}
