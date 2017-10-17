package com.itheima.bos.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.system.Menu;

/**  
 * ClassName:MenuDao <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午4:59:00 <br/>       
 */
public interface MenuDao extends JpaRepository<Menu, Integer>{
    
    List<Menu> findByParentMenuIsNull();
    
    @Query("select m from Menu m inner join m.roles r inner join r.users u where u.id = ?1")
    List<Menu> findByUserId(int id);

    @Query("select m from Menu m inner join m.roles r where r.id = ?1")
    List<Menu> findByRoleId(Integer id);
}
  
