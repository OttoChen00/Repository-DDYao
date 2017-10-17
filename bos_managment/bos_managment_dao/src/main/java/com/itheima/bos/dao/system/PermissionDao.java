package com.itheima.bos.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.system.Permission;

/**  
 * ClassName:PermissionDao <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午8:22:48 <br/>       
 */
public interface PermissionDao extends JpaRepository<Permission, Integer> {
    
    @Query("select p from Permission p inner join p.roles r inner join r.users u where u.id = ?1")
    List<Permission> findByUserId(int id);

    @Query("select p from Permission p inner join p.roles r  where r.id = ?1")
    List<Permission> findByRoleId(int id);
}
  
