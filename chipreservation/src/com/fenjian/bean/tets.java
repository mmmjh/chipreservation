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

        // ��ȷ������
        // ��ȡָ����ʽ��ʱ��
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // ����ַ���
        System.out.println(df.format(new Date()));
       
    }
}
