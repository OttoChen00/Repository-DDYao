package com.itheima.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Area;

/**  
 * ClassName:AreaDao <br/>  
 * Function:  <br/>  
 * Date:     2017年9月19日 下午7:20:54 <br/>       
 */
public interface AreaDao extends JpaRepository<Area, String>{
  
  @Query("select a from Area a where province like ?1 or city like ?1 or district like ?1"
          + " or postcode like ?1 or citycode like ?1 or shortcode like ?1")
  List<Area> findByQ(String string);
  
  Area findByProvinceAndCityAndDistrict(String province,String city,String district);
}
  
