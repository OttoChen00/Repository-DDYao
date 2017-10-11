package com.itheima.bos.fore.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**  
 * ClassName:SpringDataRedisTest <br/>  
 * Function:  <br/>  
 * Date:     2017年9月25日 下午3:36:56 <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDataRedisTest {
  
  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  
  @Test
   public void test_01() {
    //redisTemplate.opsForValue().set("bos19", "heima19");
    redisTemplate.opsForValue().set("bos20", "heima20", 10, TimeUnit.SECONDS);
  }
  
  @Test
  public void test_02() {
    String string = redisTemplate.opsForValue().get("bos20");
    System.out.println(string);
  }
}
  
