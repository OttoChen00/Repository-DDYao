package com.itheima.bos.web.action.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.system.UserService;
import com.itheima.bos.web.action.common.BaseAction;

/**
 * ClassName:UserAction <br/>
 * Function: <br/>
 * Date: 2017年10月2日 下午5:43:05 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class UserAction extends BaseAction<User> {

    private static final long serialVersionUID = 6853193711621645596L;
    
    @Autowired
    private UserService userService;

    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Action(value = "userAction_login", results = {
            @Result(name = "success", type = "redirect", location = "/index.html"),
            @Result(name = "login", type = "redirect", location = "/login.html")})
    public String login() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String serverCode = (String) session.getAttribute("key");
        if (StringUtils.isNotEmpty(serverCode) && StringUtils.isNotEmpty(checkcode)
                && serverCode.equalsIgnoreCase(checkcode)) {
            // 获取shiro框架的当前用户对象subject
            Subject subject = SecurityUtils.getSubject();
            // 创建令牌
            AuthenticationToken token = new UsernamePasswordToken(getModel().getUsername(), getModel().getPassword());
            try {
                subject.login(token);
                User user = (User) subject.getPrincipal();
                session.setAttribute("user", user);
                return SUCCESS;
            } catch (IncorrectCredentialsException e) {
                System.out.println("用户密码错误");
            } catch (UnknownAccountException e) {
                System.out.println("用户名错误");
            }
        } else {
            System.out.println("验证码错误");
        }
        return LOGIN;
    }

    @Action(value = "userAction_logout", results = {
            @Result(name = "login", type = "redirect", location = "/login.html")})
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ServletActionContext.getRequest().getSession().setAttribute("user", null);
        return LOGIN;
    }
    
    private Integer[] roleIds;
    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }
    
    @Action(value = "userAction_save", 
            results = {@Result(name = "success", type = "redirect", location = "/pages/system/userlist.html")})
    public String save() {
        userService.save(getModel(),roleIds);
        return SUCCESS;
    }

    @Action(value = "userAction_findAll") 
    public String findAll() throws IOException {
        List<User> list = userService.findAll();
        list2json(list, new String[]{"roles"}, true);
        return NONE;
    }
    
}

