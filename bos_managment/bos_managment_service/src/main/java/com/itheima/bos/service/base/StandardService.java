package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     2017年9月16日 下午7:51:41 <br/>       
 */
public interface StandardService {
  
  void save(Standard standard);

  Page<Standard> pageQuery(Pageable pageable);

  List<Standard> findAll();

}
  
