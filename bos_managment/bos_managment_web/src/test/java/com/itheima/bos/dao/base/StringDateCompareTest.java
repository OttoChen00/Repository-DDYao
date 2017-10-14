package com.itheima.bos.dao.base;

import org.junit.Test;

/**
 * ClassName:StringDateCompareTest <br/>
 * Function: <br/>
 * Date: 2017年9月27日 上午10:51:35 <br/>
 */
public class StringDateCompareTest {
    @Test
    public void test_01() {
        String str1 = "14:00";
        String str2 = "15:10";
        int res = str1.compareTo(str2);
        if (res > 0)
            System.out.println("str1>str2");
        else if (res == 0)
            System.out.println("str1=str2");
        else
            System.out.println("str1<str2");
    }
}

