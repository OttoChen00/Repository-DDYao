package com.itheima.bos.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.system.UserDao;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.system.UserService;

/**  
 * ClassName:UserServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年10月7日 下午4:49:22 <br/>       
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User model, Integer[] roleIds) {
        userDao.save(model);
        // 用户和角色建立联系，同样也是创建一个脱管态的角色作为外键和用户建立联系
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                Role role = new Role();
                role.setId(roleId);
                model.getRoles().add(role);
            }
        }  
    }

}
  
