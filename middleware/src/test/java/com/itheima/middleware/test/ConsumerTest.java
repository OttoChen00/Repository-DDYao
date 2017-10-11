package com.itheima.middleware.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.middleware.consumer.EmailConsumer;

/**  
 * ClassName:ConsumerTest <br/>  
 * Function:  <br/>  
 * Date:     2017年9月28日 下午7:06:18 <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ConsumerTest {
    
    @Autowired
    private EmailConsumer emailConsumer;
    
    @Test
    public void test_01() {
    }
}
  
