package com.itheima.bos.web.action.common;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * ClassName:CommonAction <br/>
 * Function: <br/>
 * Date: 2017年9月19日 下午8:33:03 <br/>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    public BaseAction() {
        Type superType = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superType;
        Type[] types = parameterizedType.getActualTypeArguments();
        try {
            Class<T> clazz = (Class<T>) types[0];
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

        SimplePropertyPreFilter propertyFilter = new SimplePropertyPreFilter();
        Set<String> set = propertyFilter.getExcludes();
        if (excludes != null && excludes.length > 0) {
            for (String exclude : excludes) {
                set.add(exclude);
            }
        }
        String json = JSON.toJSONString(map, propertyFilter);

        System.out.println(json);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
    }


    public void list2json(List list, final String[] excludes) throws IOException {
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

    // 上面方法的加强，属性过滤可以是包括或不包括，通过传入一个布尔值来实现
    public void list2json(List list, final String[] excludes, boolean isExcludes) throws IOException {
        SimplePropertyPreFilter propertyPreFilter = new SimplePropertyPreFilter();
        if (excludes != null && excludes.length > 0) {
            Set<String> set = null;
            if (isExcludes) {
                set = propertyPreFilter.getExcludes();
            } else {
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
    
    //由于fastJson解析不了pId ,所以又新加了一个方法
    public void list2json2(List list, String[] excludes) throws IOException {

        // 指定要排除的属性,避免因为懒加载导致的问题
        JsonConfig config = new JsonConfig();
        config.setExcludes(excludes);
        String json = JSONArray.fromObject(list, config).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}

