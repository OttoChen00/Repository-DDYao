
package com.itheima.crm.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.itheima.crm.service.Customer;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CustomerService", targetNamespace = "http://service.crm.itheima.com/")
@XmlSeeAlso({
})
public interface CustomerService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "activeCustomer", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.ActiveCustomer")
    @ResponseWrapper(localName = "activeCustomerResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.ActiveCustomerResponse")
    public void activeCustomer(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns com.itheima.crm.service.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByTelephoneAndPassword", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindByTelephoneAndPassword")
    @ResponseWrapper(localName = "findByTelephoneAndPasswordResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindByTelephoneAndPasswordResponse")
    public Customer findByTelephoneAndPassword(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "regist", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.Regist")
    @ResponseWrapper(localName = "registResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.RegistResponse")
    public void regist(
        @WebParam(name = "arg0", targetNamespace = "")
        Customer arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.itheima.crm.service.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCustomerByTelephone", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.GetCustomerByTelephone")
    @ResponseWrapper(localName = "getCustomerByTelephoneResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.GetCustomerByTelephoneResponse")
    public Customer getCustomerByTelephone(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.itheima.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCustomerNotAssociated", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindCustomerNotAssociated")
    @ResponseWrapper(localName = "findCustomerNotAssociatedResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindCustomerNotAssociatedResponse")
    public List<Customer> findCustomerNotAssociated();

    /**
     * 
     * @return
     *     returns java.util.List<com.itheima.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindAllResponse")
    public List<Customer> findAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.itheima.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCustomerAssociatedByID", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindCustomerAssociatedByID")
    @ResponseWrapper(localName = "findCustomerAssociatedByIDResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.FindCustomerAssociatedByIDResponse")
    public List<Customer> findCustomerAssociatedByID(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "assignCustomers2FixedAreaByID", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.AssignCustomers2FixedAreaByID")
    @ResponseWrapper(localName = "assignCustomers2FixedAreaByIDResponse", targetNamespace = "http://service.crm.itheima.com/", className = "com.itheima.crm.service.AssignCustomers2FixedAreaByIDResponse")
    public void assignCustomers2FixedAreaByID(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        List<Integer> arg1);

}