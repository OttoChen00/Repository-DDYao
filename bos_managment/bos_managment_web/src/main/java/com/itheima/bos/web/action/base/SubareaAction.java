package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubareaService;
import com.itheima.bos.utils.UUIDUtils;
import com.itheima.bos.web.action.common.BaseAction;

/**
 * ClassName:SubareaAction <br/>
 * Function: <br/>
 * Date: 2017年9月20日 下午4:04:37 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class SubareaAction extends BaseAction<SubArea> {

  @Autowired
  private SubareaService subareaService;

  @Action(value = "subareaAction_save", results = {
          @Result(name = "success", type = "redirect", location = "/pages/base/sub_area.html")})
  public String save() {
    getModel().setId(UUIDUtils.getId());
    subareaService.save(getModel());
    return SUCCESS;
  }

  @Action(value = "subareaAction_pageQuery")
  public String pageQuery() throws IOException {
    Pageable pageable = new PageRequest(page-1, rows);
    Page<SubArea> page = subareaService.pageQuery(pageable);
    page2json(page, new String[]{"fixedArea","subareas"});
    return NONE;
  }
  
  @Action(value = "subAreaAction_findNotAssociatedSubArea")
  public String findNotAssociatedSubArea() throws IOException{
    List<SubArea> list = subareaService.findNotAssociatedSubArea();
    list2json(list, new String[]{"id","keyWords"}, false);
    return NONE;
  }
  
  @Action(value = "subAreaAction_findAssociatedSubArea")
  public String findAssociatedSubArea() throws IOException{
    List<SubArea> list = subareaService.findAssociatedSubArea(getModel().getId());
    list2json(list, new String[]{"id","keyWords"}, false);
    return NONE;
  }

  @Action(value = "subAreaAction_doAnalysis")
  public String doAnalysis() throws IOException{
      List<Object[]> list = subareaService.findSubAreaByProvince();
      list2json(list,null,false);
    return NONE;
  }

    @Action(value = "subAreaAction_doAnalysis02")
    public String doAnalysis02() throws IOException{
        List<Object[]> list = subareaService.findSubAreaByProvince();

        List<Map<String, Object[]>> mapList = new ArrayList<>();

        for (Object[] arr:list
                ) {
            HashMap<String, Object[]> map = new HashMap<>();
            map.put("name",new Object[]{arr[0]});
            map.put("data",new Object[]{arr[1]});
            mapList.add(map);
        }
        System.out.println(mapList.toString());

        list2json(mapList,null,false);
        return NONE;
    }
}

