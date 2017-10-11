package com.itheima.bos.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.bos.domain.system.User;

/**  
 * ClassName:UserDao <br/>  
 * Function:  <br/>  
 * Date:     2017年10月3日 上午9:51:15 <br/>       
 */
public interface UserDao extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
}
  
