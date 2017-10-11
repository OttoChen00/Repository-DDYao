package com.itheima.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaService <br/>  
 * Function:  <br/>  
 * Date:     2017年9月20日 下午4:09:13 <br/>       
 */
public interface SubareaService {

  void save(SubArea model);

  Page<SubArea> pageQuery(Pageable pageable);

  List<SubArea> findNotAssociatedSubArea();

  List<SubArea> findAssociatedSubArea(String id);

}
  
