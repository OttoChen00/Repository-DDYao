package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;
import com.itheima.bos.web.action.common.CommonAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:StandardAction <br/>
 * Function: <br/>
 * Date: 2017年9月16日 下午7:36:12 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class StandardAction extends CommonAction<Standard> {

  private static final long serialVersionUID = -1289441689012167606L;
  
  public StandardAction() {
    super(Standard.class);
  }
  
  @Autowired
  private StandardService standardService;
  
  @Action(value="standardAction_save",results={@Result(name="success",location="/pages/base/standard.html",type="redirect")})
  public String save() {
    standardService.save(getModel());
    return SUCCESS;
  }
  
  
  @Action("standardAction_pageQuery")
  public String pageQuery() throws IOException {
    Pageable pageable = new PageRequest(page-1, rows);
    Page<Standard> page = standardService.pageQuery(pageable);
    
   page2json(page, null);
    return NONE;
  }
  
  /**  
   * findAll:. 为添加快递员查询所有的收派标准<br/>  
   *  
   * @return  
   * @throws IOException 
   */
  @Action("standardAction_findAll")
  public String findAll() throws IOException {
    List<Standard> list = standardService.findAll();
    String json = JSON.toJSONString(list);
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/html;charset=utf-8");
    response.getWriter().println(json);
    return NONE;
  }
}

