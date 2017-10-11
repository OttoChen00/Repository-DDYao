package com.itheima.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.StandardDao;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;

/**  
 * ClassName:StandardServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年9月16日 下午7:53:11 <br/>       
 */

@Transactional
@Service
public class StandardServiceImpl implements StandardService{
  
  @Autowired
  private StandardDao standardDao;
  
  @Override
  public void save(Standard standard) {
      standardDao.save(standard);
  }

  @Override
  public Page<Standard> pageQuery(Pageable pageable) {
    return standardDao.findAll(pageable);
  }

  @Override
  public List<Standard> findAll() {
    return standardDao.findAll();
  }

}
  
