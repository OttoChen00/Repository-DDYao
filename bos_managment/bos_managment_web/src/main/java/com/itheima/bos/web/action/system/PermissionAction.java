package com.itheima.bos.web.action.system;

import java.io.IOException;
import java.util.List;

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

import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.service.system.PermissionService;
import com.itheima.bos.web.action.common.BaseAction;
import org.springframework.ui.Model;

/**  
 * ClassName:PermissionAction <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午8:18:27 <br/>       
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PermissionAction extends BaseAction<Permission>{

    private static final long serialVersionUID = 39421113080605005L;
    
    @Autowired
    private PermissionService permissionService;
    
    @Action(value = "permissionAction_pageQuery")
    public String pageQuery() throws IOException {
        Pageable pageable = new PageRequest(page-1, rows);
        Page<Permission> page =  permissionService.pageQuery(pageable);
        page2json(page, new String[]{"roles"});
        return NONE;
    }
    
    @Action(value = "permissionAction_findAll")
    public String findAlll() throws IOException {
        List<Permission> list = permissionService.findAll();
        list2json(list, new String[]{"id","name"}, false);
        return NONE;
    }
    
    @Action(value = "permissionAction_save" ,results={@Result(name="success",location="/pages/system/permission.html",type="redirect")})
    public String save() throws IOException {
        permissionService.save(getModel());
        return SUCCESS;
    }

    @Action(value = "permissionAction_findByRoleId")
    public String findByRoleId() throws IOException {
        List<Permission> list = permissionService.findByRoleId(getModel().getId());
        list2json(list, new String[]{"id","name"}, false);
        return NONE;
    }
}
  
