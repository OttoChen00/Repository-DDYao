
package com.itheima.bos.service.take_delivery.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.itheima.bos.domain.take_delivery.Order;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrderService", targetNamespace = "http://take_delivery.service.bos.itheima.com/")
@XmlSeeAlso({
})
public interface OrderService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "save", targetNamespace = "http://take_delivery.service.bos.itheima.com/", className = "com.itheima.bos.service.take_delivery.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://take_delivery.service.bos.itheima.com/", className = "com.itheima.bos.service.take_delivery.SaveResponse")
    public void save(
        @WebParam(name = "arg0", targetNamespace = "")
        Order arg0);

}
