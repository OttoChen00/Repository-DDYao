package com.itheima.bos.web.action.take_delivery;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.take_delivery.WayBillService;
import com.itheima.bos.web.action.common.BaseAction;

/**  
 * ClassName:WaybillAction <br/>  
 * Function:  <br/>  
 * Date:     2017年9月28日 下午9:19:51 <br/>       
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class WayBillAction extends BaseAction<WayBill>{
    
    private static final long serialVersionUID = 6309281902012178975L;
    
    @Autowired
    private WayBillService wayBillService;

    @Action(value = "waybillAction_quickSave")
    public String quickSave() throws IOException {
        String flag ;
        try {
            wayBillService.save(getModel());
            flag = "0";
        } catch (Exception e) {
            flag = "1";
            e.printStackTrace();  
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(flag);
        return NONE;
    }
}
  
