package com.itheima.bos.service.take_delivery;

import javax.jws.WebService;

import com.itheima.bos.domain.take_delivery.Order;

/**  
 * ClassName:OrderService <br/>  
 * Function:  <br/>  
 * Date:     2017年9月26日 下午2:30:04 <br/>       
 */
@WebService
public interface OrderService {
    void save(Order order);
}
  
