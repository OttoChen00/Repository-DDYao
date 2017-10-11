package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.system.Role;

/**  
 * ClassName:RoleService <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午8:03:59 <br/>       
 */
public interface RoleService {

    void save(Role role, String menuIds, Integer[] permissionIds);

    List<Role> findAll();
}
  
