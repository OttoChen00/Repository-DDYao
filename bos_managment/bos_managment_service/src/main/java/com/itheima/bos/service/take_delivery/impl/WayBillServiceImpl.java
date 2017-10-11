package com.itheima.bos.service.take_delivery.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.take_delivery.WayBillDao;
import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.take_delivery.WayBillService;

/**  
 * ClassName:WayBillServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年9月28日 下午9:23:51 <br/>       
 */
@Transactional
@Service
public class WayBillServiceImpl implements WayBillService {
    
    @Autowired
    private WayBillDao wayBillDao;

    @Override
    public void save(WayBill model) {
        wayBillDao.save(model);
    }
    
    
}
  
