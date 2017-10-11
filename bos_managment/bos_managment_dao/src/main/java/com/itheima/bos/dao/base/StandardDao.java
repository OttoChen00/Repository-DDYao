package com.itheima.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardDao <br/>  
 * Function:  <br/>  
 * Date:     2017年9月16日 下午4:10:58 <br/>       
 */

public interface StandardDao extends JpaRepository<Standard, Integer>{
  
  @Modifying
  @Query("update Standard set minWeight=?2 where name=?1")
  void updateMinweightByName(String name,Integer minWeight);
  
}
  
