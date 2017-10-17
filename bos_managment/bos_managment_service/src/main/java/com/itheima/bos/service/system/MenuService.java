package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.User;

/**  
 * ClassName:MenuService <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午4:55:04 <br/>       
 */
public interface MenuService {

    List<Menu> findParentMenu();

    void save(Menu model);

    Page<Menu> pageQuery(Pageable pageable);

    List<Menu> findAll();

    List<Menu> findByUser(User user);

    List<Menu> findByRoleId(Integer id);
}
  
