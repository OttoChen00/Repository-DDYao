package com.itheima.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;

/**
 * ClassName:CustomerServiceImpl <br/>
 * Function: <br/>
 * Date: 2017年9月21日 下午3:00:15 <br/>
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public List<Customer> findCustomerAssociatedByID(String fixedAreaId) {
        return customerDao.findByFixedAreaId(fixedAreaId);
    }

    @Override
    public List<Customer> findCustomerNotAssociated() {
        return customerDao.findByFixedAreaIdIsNull();
    }

    @Override
    public void assignCustomers2FixedAreaByID(String fixedAreaId, List<Integer> ids) {
        // 1.将原有的fixedAreaId全部清空
        customerDao.setFixedAreaIDNull(fixedAreaId);
        // 2.根据客户id重新添加fixedAreaId
        if (ids != null && ids.size() > 0) {
            for (Integer id : ids) {
                customerDao.updateFixedAreaId(id, fixedAreaId);
            }
        }
    }

    @Override
    public void regist(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void activeCustomer(String telephone) {
        customerDao.updateType2active(telephone);
    }

    @Override
    public Customer findByTelephoneAndPassword(String telephone, String password) {
        return customerDao.findByTelephoneAndPassword(telephone, password);
    }

    @Override
    public Customer getCustomerByTelephone(String telephone) {
        return customerDao.findByTelephone(telephone);
    }

    @Override
    public String findFixAreaId(String address) {
        return customerDao.findFixAreaIdByAdress(address);
    }

}

