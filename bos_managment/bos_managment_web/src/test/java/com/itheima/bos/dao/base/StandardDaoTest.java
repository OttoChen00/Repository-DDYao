package com.itheima.bos.dao.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.utils.PinYin4jUtils;

import lombok.extern.log4j.Log4j;
import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * ClassName:StandardDaoTest <br/>
 * Function: <br/>
 * Date: 2017年9月16日 下午4:23:03 <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Log4j
public class StandardDaoTest {

  @Autowired
  private StandardDao standardDao;

  @Test
  public void test() {
    Standard standard = new Standard();
    standard.setName("王老8");
    standardDao.save(standard);
  }

  @Test
  public void test_updateByName() {
    standardDao.updateMinweightByName("张三", 100);
  }

  @Test
  public void test_01() {
    PropertyFilter propertyFilter = new PropertyFilter() {
      @Override
      public boolean apply(Object object, String name, Object value) {
        if (name.equalsIgnoreCase("company")) {//不需要序列化的字段
          return false;
        }
        return true;
      }
    };
  }
  
  @Test
  public void test_02() {
    String provice = "江西省";
    String city = "南昌市";
    String district = "红谷新区";
    
    String pinyin = PinYin4jUtils.hanziToPinyin(provice+city+district);
    String[] strings = PinYin4jUtils.getHeadByString(provice+city+district);
    
    System.out.println(PinYin4jUtils.stringArrayToString(strings));
    System.out.println(org.apache.commons.lang.StringUtils.join(strings));
  }
}

