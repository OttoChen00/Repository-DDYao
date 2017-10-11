package com.itheima.bos.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.system.MenuDao;
import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.system.MenuService;

/**  
 * ClassName:MenuServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年10月6日 下午4:55:51 <br/>       
 */
@Transactional
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuDao menuDao;
    
    @Override
    public List<Menu> findParentMenu() {
        return menuDao.findByParentMenuIsNull();
    }

    @Override
    public void save(Menu model) {
          menuDao.save(model);
    }

    @Override
    public Page<Menu> pageQuery(Pageable pageable) {
        return menuDao.findAll(pageable);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public List<Menu> findByUser(User user) {
        return menuDao.findByUserId(user.getId());
    }
    
}
  
