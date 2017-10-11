package com.itheima.crm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.crm.dao.CustomerDao;

/**  
 * ClassName:DaoTest <br/>  
 * Function:  <br/>  
 * Date:     2017年9月25日 下午4:50:10 <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DaoTest {
  
  @Autowired
  private CustomerDao customerDao;
  @Test
  public void test_01() {
    customerDao.delete(22);
  }
}
  
