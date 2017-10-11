package com.itheima.bos.web.action.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.itheima.bos.domain.base.Courier;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:CommonAction <br/>
 * Function: <br/>
 * Date: 2017年9月19日 下午8:33:03 <br/>
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T> {

  public CommonAction(Class<T> clazz) {
    try {
      model = clazz.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private T model;

  @Override
  public T getModel() {
    return model;
  }

  protected int page;
  protected int rows;

  public void setPage(int page) {
    this.page = page;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public void page2json(Page<T> page, final String[] excludes) throws IOException {
    Map<String, Object> map = new HashMap<>();
    map.put("total", page.getTotalElements());
    map.put("rows", page.getContent());

    PropertyFilter propertyFilter = new PropertyFilter() {
      @Override
      public boolean apply(Object object, String name, Object value) {
        if (excludes!= null && excludes.length > 0) {
          for (String exclude : excludes) {
            if (name.equalsIgnoreCase(exclude)) {
              return false;
            }
          }
        }
        return true;
      }
    };

    String json = JSON.toJSONString(map, propertyFilter);

    System.out.println(json);
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().println(json);

  }
  
  public void list2json(List<T> list, final String[] excludes) throws IOException {

    PropertyFilter propertyFilter = new PropertyFilter() {
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
    };

    String json = JSON.toJSONString(list, propertyFilter);

    System.out.println(json);
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().println(json);
  }
  
  //上面方法的加强，属性过滤可以是包括或不包括，通过传入一个布尔值来实现
  public void list2json(List list, final String[] excludes, boolean isExcludes) throws IOException {
    SimplePropertyPreFilter propertyPreFilter = new SimplePropertyPreFilter();
    if (excludes != null && excludes.length > 0) {
      Set<String> set = null;
      if (isExcludes) {
        set = propertyPreFilter.getExcludes();
      }else {
        set = propertyPreFilter.getIncludes();
      }
      for (String str : excludes) {
        set.add(str);
      }
    }
    String json = JSON.toJSONString(list, propertyPreFilter);
    System.out.println(json);
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().println(json);
  }
}

