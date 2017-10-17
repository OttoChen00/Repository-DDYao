package com.itheima.bos.web.action.system;

import java.io.IOException;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.system.MenuService;
import com.itheima.bos.web.action.common.BaseAction;

/**
 * ClassName:MenuAction <br/>
 * Function: <br/>
 * Date: 2017年10月6日 下午4:51:32 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class MenuAction extends BaseAction<Menu> {

    private static final long serialVersionUID = -2314187784635614473L;
    
    @Autowired
    private MenuService menuService;
    
    @Action(value = "menuAction_findParentMenu")
    public String findParentMenu() throws IOException {
        List<Menu> list = menuService.findParentMenu();
        list2json(list, new String[]{"id","text","children"}, false);
        return NONE;
    }
    
    @Action(value = "menuAction_save" ,results={@Result(name="success",location="/pages/system/menu.html",type="redirect")})
    public String save() throws IOException {
        if (getModel().getParentMenu() != null && getModel().getParentMenu().getId() == 0) {
            getModel().setParentMenu(null);
        }
        menuService.save(getModel());
        return SUCCESS;
    }
    
    @Action(value = "menuAction_pageQuery" ,results={@Result(name="success",location="/pages/system/menu.html",type="redirect")})
    public String pageQuery() throws IOException {
        String page2 = getModel().getPage();
        page = Integer.parseInt(page2);
        Pageable pageable = new PageRequest(page-1, rows);
        Page<Menu> page =  menuService.pageQuery(pageable);
        page2json(page, new String[]{"parentMenu","roles","childrenMenus","text","children"});
        return NONE;
    }
    

    @Action(value = "menuAction_findAll")
    public String findAll() throws IOException {
        List<Menu> list = menuService.findAll();
        //list2json(list, new String[]{"id","name","pId"}, false); fastJson解析不了pId
        list2json2(list, new String[]{"children","roles","parentMenu","text"});
        return NONE;
    }
    
    @Action(value = "menuAction_findByUser")
    public String findByUser() throws IOException {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        // 如果是admin，则查询所有菜单。
        List<Menu> list = null;
        if (user.getUsername().equals("admin")) {
            list = menuService.findAll();
        }else {
            list = menuService.findByUser(user);
        }
        list2json2(list, new String[]{"children","roles","parentMenu","text"});
        return NONE;
    }

    @Action(value = "menuAction_findByRoleId")
    public String findByRoleId() throws IOException {
        List<Menu> list = menuService.findByRoleId(getModel().getId());
        list2json(list,new String[]{"id"},false);
        return NONE;
    }
}

