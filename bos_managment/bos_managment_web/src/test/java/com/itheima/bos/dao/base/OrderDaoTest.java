package com.itheima.bos.dao.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.bos.dao.take_delivery.OrderDao;

/**  
 * ClassName:OrderDaoTest <br/>  
 * Function:  <br/>  
 * Date:     2017年9月26日 下午4:12:22 <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OrderDaoTest {
    @Autowired
    private AreaDao orderDao;
    @Test
    public void test_01() {
       // orderDao.findByProvinceAndCityAndDistrict("北京", city, district)
    }
}
  
