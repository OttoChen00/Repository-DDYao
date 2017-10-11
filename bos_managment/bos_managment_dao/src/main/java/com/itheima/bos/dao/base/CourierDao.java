package com.itheima.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Courier;

/**  
 * ClassName:CourierDao <br/>  
 * Function:  <br/>  
 * Date:     2017年9月17日 下午4:35:35 <br/>       
 */
public interface CourierDao extends JpaRepository<Courier, Integer>,JpaSpecificationExecutor<Courier>{
  
  @Modifying
  @Query("update Courier set deltag = 1 where id=?")
  void updateDeltagById(Integer id);

  List<Courier> findByDeltagIsNull();
  
}
  
