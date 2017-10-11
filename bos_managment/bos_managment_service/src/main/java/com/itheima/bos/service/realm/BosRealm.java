package com.itheima.bos.service.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itheima.bos.dao.system.MenuDao;
import com.itheima.bos.dao.system.PermissionDao;
import com.itheima.bos.dao.system.RoleDao;
import com.itheima.bos.dao.system.UserDao;
import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;

/**  
 * ClassName:BosRealm <br/>  
 * Function:  <br/>  
 * Date:     2017年10月3日 上午9:44:02 <br/>       
 */
@Component
public class BosRealm extends AuthorizingRealm{
    
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    
    /**  
     * TODO 授权方法.  
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)  
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        SimpleAuthorizationInfo info = new  SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        
        List<Role> roles = null;
        List<Permission> permissions = null;
        // 如果是admin则赋予所有的角色和权限
        if (user.getUsername().equals("admin")) {
            roles = roleDao.findAll();
            permissions = permissionDao.findAll();
            // 如果是其他用户则查询该用户所拥有的权限和角色
        }else {
            roleDao.findByUserId(user.getId());
            permissions = permissionDao.findByUserId(user.getId());
        }
        if (roles != null) {
            for (Role role : roles) {
                //赋予角色
                info.addRole(role.getKeyword());
            }
        }
        if (permissions != null) {
            for (Permission permission : permissions) {
                //赋予权限
                info.addStringPermission(permission.getKeyword());
            }
        }
        return info;
    }

    /**  
     * TODO 认证方法.  
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)  
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken aToken) throws AuthenticationException {
        UsernamePasswordToken token  = (UsernamePasswordToken) aToken;
        // 自己判断用户名是否存在，密码的比对交给shiro
        String username = token.getUsername();
        User user = userDao.findByUsername(username);
        AuthenticationInfo info = null;
        if (user != null) {
            info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        return info;
    }

}
  
