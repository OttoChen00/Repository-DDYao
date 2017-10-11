package com.itheima.bos.dao.take_delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.bos.domain.take_delivery.Order;

/**  
 * ClassName:OrderDao <br/>  
 * Function:  <br/>  
 * Date:     2017年9月26日 下午3:45:23 <br/>       
 */
public interface OrderDao extends JpaRepository<Order, Integer>{
    
}
  
