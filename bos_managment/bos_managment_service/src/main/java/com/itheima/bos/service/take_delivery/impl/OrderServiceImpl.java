package com.itheima.bos.service.take_delivery.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.AreaDao;
import com.itheima.bos.dao.base.FixAreaDao;
import com.itheima.bos.dao.take_delivery.OrderDao;
import com.itheima.bos.dao.take_delivery.WorkBillDao;
import com.itheima.bos.domain.base.Area;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.domain.take_delivery.WorkBill;
import com.itheima.bos.service.take_delivery.OrderService;
import com.itheima.bos.utils.Constant;
import com.itheima.crm.service.impl.CustomerService;

/**
 * ClassName:OrderServiceImpl <br/>
 * Function: <br/>
 * Date: 2017年9月26日 下午2:31:40 <br/>
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FixAreaDao fixAreaDao;
    @Autowired
    private WorkBillDao workBillDao;

    @Override
    public void save(Order order) {
        Area dbSendArea = findDbProvinceAndCityAndDistrict(order.getSendArea());
        order.setSendArea(dbSendArea);
        Area dbRecArea = findDbProvinceAndCityAndDistrict(order.getRecArea());
        order.setOrderNum(RandomStringUtils.randomNumeric(16));
        order.setRecArea(dbRecArea);
        order.setStatus(Constant.ORDER_STATUS_DQJ);
        order.setOrderTime(new Date());
        orderDao.save(order);

        // 1.自动分单：---寄件详细地址完全匹配
        if (StringUtils.isNotEmpty(order.getSendAddress())) {
            String findFixAreaId = customerService.findFixAreaId(order.getSendAddress());
            if (StringUtils.isNotEmpty(findFixAreaId)) {
                FixedArea fixedArea = fixAreaDao.getOne(findFixAreaId);
                autoDispenseByFixArea(fixedArea, order);
                return;
            }
        }

        // 2.自动分单：---寄件区域关键字查询
        if (dbSendArea != null && StringUtils.isNotEmpty(order.getSendAddress())) {
            Set<SubArea> subareas = dbSendArea.getSubareas();
            String sendAddress = order.getSendAddress();
            if (!subareas.isEmpty()) {
                for (SubArea subArea : subareas) {
                    if (sendAddress.contains(subArea.getKeyWords())||sendAddress.contains(subArea.getAssistKeyWords())) {
                        FixedArea fixedArea = subArea.getFixedArea();
                        if (fixedArea!=null) {
                            autoDispenseByFixArea(fixedArea, order);
                            return;
                        }
                    }
                }
            }
        }
        
        // 3.人工分单
        order.setOrderType(Constant.ORDER_TYPE_SDFD);
    }

    /**
     * findDbProvinceAndCityAndDistrict:.将Area从瞬时态转为持久态 <br/>
     * 
     * @param area
     * @return
     */
    public Area findDbProvinceAndCityAndDistrict(Area area) {
        if (area != null) {
            Area dbArea =
                    areaDao.findByProvinceAndCityAndDistrict(area.getProvince(), area.getCity(), area.getDistrict());
            return dbArea;
        }
        return null;
    }
    
    /**  
     * autoDispense:. 根据定区查找快递员自动分单<br/>  
     *    
     */
    public void autoDispenseByFixArea(FixedArea fixedArea,Order order) {
        Set<Courier> couriers = fixedArea.getCouriers();
        if (!couriers.isEmpty()) {
            // todo 查询当班的快递员
            Iterator<Courier> iterator = couriers.iterator();
            Courier courier = iterator.next();// 假装第一个快递员在上班
            order.setCourier(courier);
            // 生成工单
            WorkBill workBill = new WorkBill();
            workBill.setType(Constant.WORDBILL_TYPE_X);
            workBill.setPickstate(Constant.WORKBILL_PICKSTATE_XD);
            workBill.setBuildtime(new Date());
            workBill.setRemark(order.getRemark());
            workBill.setCourier(courier);
            workBill.setOrder(order);
            workBillDao.save(workBill);
            order.setOrderType(Constant.ORDER_TYPE_ZDFD);
        }
    }
}

