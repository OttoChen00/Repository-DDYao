package com.itheima.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.SubareaDao;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubareaService;

/**  
 * ClassName:SubareaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年9月20日 下午4:10:32 <br/>       
 */
@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {

  @Autowired
  private SubareaDao subareaDao;

  @Override
  public void save(SubArea model) {
    subareaDao.save(model);
  }

  @Override
  public Page<SubArea> pageQuery(Pageable pageable) {
    return subareaDao.findAll(pageable);
  }

  @Override
  public List<SubArea> findNotAssociatedSubArea() {
    return subareaDao.findByfixedAreaIsNull();
  }

  @Override
  public List<SubArea> findAssociatedSubArea(String id) {
    return subareaDao.findByfixedAreaId(id);
  }

  @Override
  public List<Object[]> findSubAreaByProvince() {
    return subareaDao.countSubAreaByProvince();
  }

}
  
