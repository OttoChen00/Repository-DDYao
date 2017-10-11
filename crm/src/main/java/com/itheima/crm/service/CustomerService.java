package com.itheima.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerService <br/>  
 * Function:  <br/>  
 * Date:     2017年9月21日 下午2:58:41 <br/>       
 */
@WebService
public interface CustomerService {
  List<Customer> findAll(); 
  
  List<Customer> findCustomerAssociatedByID(String fixedAreaId); 
  
  List<Customer> findCustomerNotAssociated(); 
  
  void assignCustomers2FixedAreaByID(String fixedAreaId,List<Integer> ids);
  
  void regist(Customer customer);
  
  void activeCustomer(String telephone);
  
  Customer findByTelephoneAndPassword(String telephone,String password);
  
  Customer getCustomerByTelephone(String telephone);
  
  String findFixAreaId(String address);
}
