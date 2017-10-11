package com.itheima.bos.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itheima.bos.dao.take_delivery.WorkBillDao;
import com.itheima.bos.domain.take_delivery.WorkBill;
import com.itheima.bos.utils.MailUtils;

/**  
 * ClassName:MailJob <br/>  
 * Function:  <br/>  
 * Date:     2017年10月9日 下午3:51:03 <br/>       
 */
@Component
public class MailJob {
    
    @Autowired
    private WorkBillDao workBillDao;
    
    public void sendMail() {
        List<WorkBill> list = workBillDao.findAll();
        
        String receiver = "ls@store.com";
        String subject = "每日运单信息";
        StringBuffer emailBodyBuffer = new StringBuffer();
        emailBodyBuffer.append("工单类型\t  取件状态\t    工单生成时间\t    快递员\t <br>");
        for (WorkBill workBill : list) {
            emailBodyBuffer.append(workBill.getType()+"\t"+workBill.getPickstate()+"\t"+workBill.getBuildtime()+"\t"+workBill.getCourier().getName()+"<br>");
        }
        String emailBody = emailBodyBuffer.toString();
        System.out.println(emailBody);
        MailUtils.sendMail(receiver, subject, emailBody);
    }
}
  
