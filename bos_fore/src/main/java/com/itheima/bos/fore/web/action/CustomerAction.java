package com.itheima.bos.fore.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.itheima.bos.fore.utils.MD5Utils;
import com.itheima.bos.fore.utils.MailUtils;
import com.itheima.crm.service.Customer;
import com.itheima.crm.service.impl.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:CustomerAction <br/>
 * Function: <br/>
 * Date: 2017年9月23日 下午12:11:21 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private static final long serialVersionUID = -3536902592630133800L;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Autowired
    private JmsTemplate jmsTemplate;

    private Customer model = new Customer();

    @Override
    public Customer getModel() {
        return model;
    }

    /**
     * sendSms:.发送验证短信 <br/>
     * 
     * @return
     * @throws IOException
     */
    @Action("customerAction_sendSms")
    public String sendSms() throws IOException {
        String serverCode = RandomStringUtils.randomNumeric(4);
        System.out.println(serverCode);
        // String result = smsService.sendSms(model.getTelephone(), "尊敬的客户你好，您本次获取的验证码为：" +
        // serverCode,
        // "");
        String result = "0123456789012345";// 因为吉信通返回结果为16位字符串则表示发送成功，这里就模拟下
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletActionContext.getRequest().getSession().setAttribute("serverCode", serverCode);
        PrintWriter writer = response.getWriter();
        if (!StringUtils.isEmpty(result) && result.length() == 16) {
            writer.println("ok");
        } else {
            writer.print("fail");
        }
        return NONE;
    }

    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    /**
     * regist:.注册 <br/>
     * 
     * @return
     */
    @Action(value = "customerAction_regist", results = {
            @Result(name = "success", location = "/signup-success.html", type = "redirect"),
            @Result(name = "error", location = "/signup-fail.html", type = "redirect")})
    public String regist() {
        // 比对验证码
        String serverCode = (String) ServletActionContext.getRequest().getSession().getAttribute("serverCode");
        if (!StringUtils.isEmpty(serverCode) && checkcode.equals(serverCode)) {
            // 加密密码
            model.setPassword(MD5Utils.md5(model.getPassword()));
            customerService.regist(model);
            // 给用户发送激活邮件
            final String activeCode = RandomStringUtils.randomNumeric(36);
            // 将activeCode存放在Redis中，使用Spring Data Redis,设置有效期为24小时
            redisTemplate.opsForValue().set(model.getTelephone(), activeCode, 24, TimeUnit.HOURS);
            final String emailBody =
                    "尊敬的用户您好！请点击本连接进行<a href='http://localhost:8280/bos_fore/customerAction_active.action?activeCode="
                            + activeCode + "&telephone=" + model.getTelephone() + "'>激活</a>";
            MessageCreator messageCreator = new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    MapMessage mapMessage = session.createMapMessage();
                    mapMessage.setString("receiver", model.getEmail());
                    mapMessage.setString("subject", "激活邮件");
                    mapMessage.setString("emailBody", emailBody);
                    return mapMessage;
                }
            };
            //发送消息队列
            jmsTemplate.send("emailQueue", messageCreator );
            
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    private String activeCode;

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    /**
     * active:.激活帐号 <br/>
     * 
     * @return
     */
    @Action(value = "customerAction_active", results = {
            @Result(name = "success", location = "/login.html", type = "redirect"),
            @Result(name = "actived", location = "/login.html", type = "redirect"),
            @Result(name = "error", location = "/signup-fail.html", type = "redirect")})
    public String active() {
        String codeInRedis = redisTemplate.opsForValue().get(model.getTelephone());
        if (!StringUtils.isEmpty(codeInRedis) && !StringUtils.isEmpty(activeCode) && activeCode.equals(codeInRedis)) {
            // 判断客户之前是否激活过
            Customer customer = customerService.getCustomerByTelephone(model.getTelephone());
            if (customer != null && !StringUtils.isEmpty(customer.getType()) && customer.getType() == 1) {
                System.out.println("客户已经注册过了");
                return "actived";
            }
            customerService.activeCustomer(model.getTelephone());
            return SUCCESS;
        }
        return ERROR;
    }

    /**
     * login:. 登录<br/>
     * 
     * @return
     */
    @Action(value = "customerAction_login", results = {
            @Result(name = "success", location = "/index.html", type = "redirect"),
            @Result(name = "login", location = "/login.html", type = "redirect")})
    public String login() {
        String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
        if (!StringUtils.isEmpty(validateCode) && !StringUtils.isEmpty(checkcode)
                && validateCode.equalsIgnoreCase(checkcode)) {
            model.setPassword(MD5Utils.md5(model.getPassword()));
            Customer customer = customerService.findByTelephoneAndPassword(model.getTelephone(), model.getPassword());
            if (customer != null) {
                return SUCCESS;
            }
        }
        System.out.println("验证码有问题");
        return LOGIN;
    }

}

