package com.itheima.bos.service.system.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.system.RoleDao;
import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.system.RoleService;

/**  
 * ClassName:RoleServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午8:04:34 <br/>       
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    RoleDao roleDao;
    

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role, String menuIds, Integer[] permissionIds) {
          roleDao.save(role);
          
          //角色和菜单建立联系，创建一脱管态菜单，再建立联系。
          if (StringUtils.isNotEmpty(menuIds)) {
              String[] split = menuIds.split(",");
              for (String menuId : split) {
                Menu menu = new Menu();
                menu.setId(Integer.parseInt(menuId));
                role.getMenus().add(menu);
              }
          }
          
          //角色和权限建立联系，也是创建脱管态权限作为外键和角色建立联系
          if (permissionIds != null && permissionIds.length > 0) {
              for (Integer permissionId : permissionIds) {
                Permission permission = new Permission();
                permission.setId(permissionId);
                role.getPermissions().add(permission);
              }
          }
    }
}
  
