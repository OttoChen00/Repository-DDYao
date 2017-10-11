package com.itheima.middleware.consumer;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import com.itheima.middleware.utils.MailUtils;

/**  
 * ClassName:EmailConsumer <br/>  
 * Function:  <br/>  
 * Date:     2017年9月28日 下午5:17:49 <br/>       
 */
@Component("emailConsumer")
public class EmailConsumer implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        MapMessage mapMessage = (MapMessage) msg;
        try {
            String receiver = mapMessage.getString("receiver");
            String subject = mapMessage.getString("subject");
            String emailBody = mapMessage.getString("emailBody");
            System.out.println(receiver+subject+emailBody);
            MailUtils.sendMail(receiver, subject, emailBody);
        } catch (JMSException e) {
            System.out.println("获取消息队列失败");
            e.printStackTrace();  
            
        }
    }
    
}
  
