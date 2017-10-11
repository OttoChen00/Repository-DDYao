package com.itheima.bos.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;

/**  
 * ClassName:RoleDao <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午8:05:34 <br/>       
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
    
    @Query("select r from Role r inner join r.users u where u.id = ? ")
    List<Role> findByUserId(int id);

}
  
