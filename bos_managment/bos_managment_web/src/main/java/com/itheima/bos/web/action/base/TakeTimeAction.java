package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.TakeTime;
import com.itheima.bos.service.base.TakeTimeService;
import com.itheima.bos.web.action.common.BaseAction;

/**  
 * ClassName:TakeTimeAction <br/>  
 * Function:  <br/>  
 * Date:     2017年9月22日 下午8:47:57 <br/>       
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class TakeTimeAction extends BaseAction<TakeTime> {
  
  @Autowired
  private TakeTimeService takeTimeService;
  
  @Action(value = "takeTimeAction_listajax")
  public String listajax() throws IOException {
    List<TakeTime> list = takeTimeService.listAll();
    list2json(list, new String[]{"id","name"},false);
    return NONE;
  }
}
  
