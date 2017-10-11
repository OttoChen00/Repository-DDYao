package com.itheima.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerDao <br/>  
 * Function:  <br/>  
 * Date:     2017年9月21日 下午3:09:20 <br/>       
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>{
  
  List<Customer> findByFixedAreaId(String fixedAreaId);
  
  List<Customer> findByFixedAreaIdIsNull();
  
  @Modifying
  @Query("update Customer set fixedAreaId=null where fixedAreaId=?")
  void setFixedAreaIDNull(String fixedAreaId);
  
  @Modifying
  @Query("update Customer set fixedAreaId=?2 where id=?1")
  void updateFixedAreaId(Integer id, String fixedAreaId);

  @Modifying
  @Query("update Customer set type=1 where telephone=?")
  void updateType2active(String telephone);
  
  Customer findByTelephoneAndPassword(String telephone,String password);
  
  Customer findByTelephone(String telephone);
  
  @Query("select fixedAreaId from Customer where address = ?")
  String findFixAreaIdByAdress(String address);
  
}