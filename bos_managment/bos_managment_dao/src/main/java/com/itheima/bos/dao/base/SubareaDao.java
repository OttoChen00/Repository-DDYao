package com.itheima.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaDao <br/>  
 * Function:  <br/>  
 * Date:     2017年9月20日 下午4:11:20 <br/>       
 */
public interface SubareaDao extends JpaRepository<SubArea, String> {
  
  List<SubArea> findByfixedAreaIsNull();
  
  List<SubArea> findByfixedAreaId(String id);
  
  @Modifying
  @Query(value="update t_sub_area set c_fixedarea_id=null where c_fixedarea_id = ?",nativeQuery=true)
  void setFixAreaNullById(String id);
  
  List<SubArea> findByIdIn(List<String> id);

}