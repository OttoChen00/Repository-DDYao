package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.FixedArea;

/**  
 * ClassName:FixedAreaService <br/>  
 * Function:  <br/>  
 * Date:     2017年9月20日 下午7:46:31 <br/>       
 */
public interface FixedAreaService {

  void save(FixedArea model);

  Page<FixedArea> pageQuery(Pageable pageable);

  void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId);

  void associationSubAreaToFixedArea(String id, List<String> subAreaIds);

}
  
