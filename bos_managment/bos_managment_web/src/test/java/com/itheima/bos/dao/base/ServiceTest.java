package com.itheima.bos.dao.base;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.crm.service.Customer;
import com.itheima.crm.service.impl.CustomerService;

/**  
 * ClassName:ServiceTest <br/>  
 * Function:  <br/>  
 * Date:     2017年9月21日 下午3:30:15 <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ServiceTest {
  
  @Resource(name="crmClient")
  private CustomerService service;//注入代理服务
  
  @Test
  public void test_01() {
    List<Customer> list = service.findCustomerAssociatedByID("dq001");
    System.out.println(list.size());
  }
  
  @Test
  public void test_02() {
      List<Customer> list = service.findAll();
      System.out.println(list.size());
  }
}
  
