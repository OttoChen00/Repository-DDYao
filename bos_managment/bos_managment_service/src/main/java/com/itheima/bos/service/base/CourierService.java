package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.bos.domain.base.Courier;

/**  
 * ClassName:CourierService <br/>  
 * Function:  <br/>  
 * Date:     2017年9月17日 下午4:32:41 <br/>       
 */
public interface CourierService {

  void save(Courier model);

  Page<Courier> pageQuery(Pageable pageable,Specification<Courier> specification);

  void courierAction_setDeltagById(String ids);

  List<Courier> findCourierNotDel();

}
  
