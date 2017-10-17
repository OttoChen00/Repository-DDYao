package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.system.Permission;

/**  
 * ClassName:PermissionService <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午8:21:01 <br/>       
 */
public interface PermissionService {

    Page<Permission> pageQuery(Pageable pageable);

    void save(Permission model);

    List<Permission> findAll();

    List<Permission> findByRoleId(Integer id);
}
  
