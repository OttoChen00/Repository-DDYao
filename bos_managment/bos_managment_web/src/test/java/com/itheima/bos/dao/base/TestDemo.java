package com.itheima.bos.dao.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubareaService;

/**  
 * ClassName:TestDemo <br/>  
 * Function:  <br/>  
 * Date:     2017年9月20日 下午4:49:28 <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestDemo {
  
  @Autowired
  private SubareaService subareaService;
  
  @Test
  public void test_01() throws IOException {
    Page<SubArea> page = subareaService.pageQuery(new PageRequest(0, 10));
    page2json(page, null);
  }
  
  public void page2json(Page<SubArea> page, final String[] excludes) throws IOException {
    Map<String, Object> map = new HashMap<>();
    map.put("total", page.getTotalElements());
    map.put("rows", page.getContent());
    /*PropertyFilter propertyFilter = new PropertyFilter() {
      @Override
      public boolean apply(Object object, String name, Object value) {
        if (excludes != null && excludes.length > 0) {
          for (String exclude : excludes) {
            if (name.equalsIgnoreCase(exclude)) {
              return false;
            }
          }
        }
        return true;
      }
    };*/
    SimplePropertyPreFilter propertyFilter = new SimplePropertyPreFilter();
    propertyFilter.getExcludes().add("");
    String json = JSON.toJSONString(map, propertyFilter, SerializerFeature.PrettyFormat);
    System.out.println(json);
  }
}
  
