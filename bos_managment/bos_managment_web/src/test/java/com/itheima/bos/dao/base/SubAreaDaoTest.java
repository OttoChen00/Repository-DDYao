package com.itheima.bos.dao.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SubAreaDaoTest {
    @Autowired
    private SubareaDao subareaDao;

    @Test
    public void test(){
        List<Object[]> list = subareaDao.countSubAreaByProvince();
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (Object[] arr:list
             ) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",arr[0]);
            map.put("data",arr[1]);
            mapList.add(map);
            System.out.println("------------");
        }
        System.out.println(mapList.toString());
    }
}
