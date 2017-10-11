package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
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
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.FixedAreaService;
import com.itheima.bos.utils.UUIDUtils;
import com.itheima.bos.web.action.common.BaseAction;
import com.itheima.crm.service.Customer;
import com.itheima.crm.service.impl.CustomerService;

import lombok.extern.log4j.Log4j;

/**
 * ClassName:FixedAreaAction <br/>
 * Function: <br/>
 * Date: 2017年9月20日 下午7:43:41 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
@Log4j
public class FixedAreaAction extends BaseAction<FixedArea> {

  private static final long serialVersionUID = 2133689355204726554L;

  @Autowired
  private FixedAreaService fixAreaService;

  @Resource(name = "crmClient")
  private CustomerService customerService;

  @Action(value = "fixedAreaAction_save", results = {
          @Result(name = "success", type = "redirect", location = "/pages/base/fixed_area.html")})
  public String save() {
    fixAreaService.save(getModel());
    return SUCCESS;
  }

  @Action(value = "fixedAreaAction_pageQuery")
  public String pageQuery() throws IOException {
    Pageable pageable = new PageRequest(page - 1, rows);
    Page<FixedArea> page = fixAreaService.pageQuery(pageable);
    page2json(page,
            new String[] {"fixedArea", "operatingTime", "operator", "operatingCompany", "subareas", "couriers"});
    return NONE;
  }

  /**
   * findNotAssociatedCustomer:查找没有关联定区的客户 <br/>
   */
  @Action(value = "fixedAreaAction_findNotAssociatedCustomer")
  public String findNotAssociatedCustomer() throws IOException {
    List<Customer> list = customerService.findCustomerNotAssociated();
    list2json(list, null);
    return NONE;
  }

  /**
   * findAssociatedCustomer:查找没关联了定区的客户 <br/>
   */
  @Action(value = "fixedAreaAction_findAssociatedCustomer")
  public String findAssociatedCustomer() throws IOException {
    List<Customer> list = customerService.findCustomerAssociatedByID(getModel().getId());
    list2json(list, null);
    return NONE;
  }

  // 封装需要绑定定区的客户id列表
  private List<Integer> customerIds;

  public void setCustomerIds(List<Integer> customerIds) {
    this.customerIds = customerIds;
  }

  /**
   * assignCustomers2FixedArea: 根据定区id 绑定客户<br/>
   */
  @Action(value = "fixedAreaAction_assignCustomers2FixedArea", results = {
          @Result(name = "success", type = "redirect", location = "/pages/base/fixed_area.html")})
  public String assignCustomers2FixedArea() throws IOException {
    customerService.assignCustomers2FixedAreaByID(getModel().getId(), customerIds);
    return SUCCESS;
  }

  private Integer courierId;
  private Integer takeTimeId;

  public void setCourierId(Integer courierId) {
    this.courierId = courierId;
  }

  public void setTakeTimeId(Integer takeTimeId) {
    this.takeTimeId = takeTimeId;
  }

  @Action(value = "fixedAreaAction_associationCourierToFixedArea", results = {
          @Result(name = "success", type = "redirect", location = "/pages/base/fixed_area.html")})
  public String associationCourierToFixedArea() throws IOException {
    fixAreaService.associationCourierToFixedArea(getModel().getId(), courierId, takeTimeId);
    return SUCCESS;
  }
  
  
  private List<String> subAreaIds;
  
  public void setSubAreaIds(List<String> subAreaIds) {
    this.subAreaIds = subAreaIds;
  }
  /**  
   * associationSubAreaToFixedArea:.关联分区 <br/>  
   *  
   * @return
   * @throws IOException  
   */
  @Action(value = "fixAreaAction_associationSubAreaToFixedArea", results = {
          @Result(name = "success", type = "redirect", location = "/pages/base/fixed_area.html")})
  public String associationSubAreaToFixedArea() {
    fixAreaService.associationSubAreaToFixedArea(getModel().getId(),subAreaIds);
    return SUCCESS;
  }
}

