package com.itheima.bos.service.base.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.CourierDao;
import com.itheima.bos.dao.base.FixAreaDao;
import com.itheima.bos.dao.base.SubareaDao;
import com.itheima.bos.dao.base.TakeTimeDao;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.domain.base.TakeTime;
import com.itheima.bos.service.base.FixedAreaService;
import com.itheima.bos.service.base.SubareaService;

/**  
 * ClassName:FixAreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年9月20日 下午7:47:21 <br/>       
 */
@Transactional
@Service
public class FixAreaServiceImpl implements FixedAreaService {
  
  @Autowired
  private FixAreaDao fixAreaDao;
  @Autowired
  private TakeTimeDao takeTimeDao;
  @Autowired
  private CourierDao courierDao;
  @Autowired
  private SubareaDao subareaDao;
  
  @Override
  public void save(FixedArea model) {
    fixAreaDao.save(model);
  }

  @Override
  public Page<FixedArea> pageQuery(Pageable pageable) {
    return fixAreaDao.findAll(pageable);
  }

  @Override
  public void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId) {
      TakeTime takeTime = takeTimeDao.getOne(takeTimeId);
      Courier courier = courierDao.getOne(courierId);
      FixedArea fixedArea = fixAreaDao.findOne(id);
      courier.setTakeTime(takeTime);
      fixedArea.getCouriers().add(courier);
      courier.getFixedAreas().add(fixedArea);
  }

  @Override
  public void associationSubAreaToFixedArea(String id, List<String> subAreaIds) {
      // 1.将原来和定区关联的分区全部清空
      subareaDao.setFixAreaNullById(id);
      // 2.重新建立关联
      FixedArea fixedArea = fixAreaDao.getOne(id);
      List<SubArea> subAreas = subareaDao.findByIdIn(subAreaIds);
      for (SubArea subArea : subAreas) {
        subArea.setFixedArea(fixedArea);
      }
      
      /*Set<SubArea> set = fixedArea.getSubareas();
      for (SubArea subArea : subAreas) {
        set.add(subArea);
      }*/
  }

}
  
