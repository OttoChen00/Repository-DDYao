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

import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.system.RoleService;
import com.itheima.bos.web.action.common.BaseAction;

/**  
 * ClassName:RoleAction <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午8:00:34 <br/>       
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class RoleAction extends BaseAction<Role>{
    
    private static final long serialVersionUID = -4237309178371924357L;
    
    @Autowired
    private RoleService roleService;
    
    @Action(value = "roleAction_findAll")
    public String findAll() throws IOException {
        List<Role> list = roleService.findAll();
        list2json(list, new String[]{"users","permissions","menus"});
        return NONE;
    }
    
    private String menuIds;
    private Integer[] permissionIds;
    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
    public void setPermissionIds(Integer[] permissionIds) {
        this.permissionIds = permissionIds;
    }
    
    @Action(value = "roleAction_save" ,results={@Result(name="success",location="/pages/system/role.html",type="redirect")})
    public String save() throws IOException {
        roleService.save(getModel(),menuIds,permissionIds);
        return SUCCESS;
    }
}
  
