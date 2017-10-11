package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.Area;

/**  
 * ClassName:AreaService <br/>  
 * Function:  <br/>  
 * Date:     2017年9月19日 下午7:17:03 <br/>       
 */
public interface AreaService {

  void saveList(List<Area> list);

  Page<Area> pageQuery(Pageable pageable);

  List<Area> findAll();

  List<Area> findByQ(String q);
    
}
  
