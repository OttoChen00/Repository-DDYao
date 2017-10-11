package com.itheima.bos.service.system;

import java.util.List;

import com.itheima.bos.domain.system.User;

/**  
 * ClassName:UserService <br/>  
 * Function:  <br/>  
 * Date:     2017年10月7日 下午4:48:56 <br/>       
 */
public interface UserService {

    void save(User model, Integer[] roleIds);

    List<User> findAll();

}
  
