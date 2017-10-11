package com.itheima.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.AreaDao;
import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.base.AreaService;

/**  
 * ClassName:AreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年9月19日 下午7:19:37 <br/>       
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
  
  @Autowired
  private AreaDao areaDao;
  
  @Override
  public void saveList(List<Area> list) {
    areaDao.save(list);
  }

  @Override
  public Page<Area> pageQuery(Pageable pageable) {
    return areaDao.findAll(pageable);
  }

  @Override
  public List<Area> findAll() {
    return areaDao.findAll();
  }

  @Override
  public List<Area> findByQ(String q) {
    q = q.toUpperCase();
    return areaDao.findByQ("%"+q+"%");
  }

}
  
