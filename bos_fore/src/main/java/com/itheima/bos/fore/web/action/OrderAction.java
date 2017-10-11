package com.itheima.bos.fore.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.service.take_delivery.impl.OrderService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:OrderAction <br/>
 * Function: <br/>
 * Date: 2017年9月26日 下午2:25:07 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

    private Order model = new Order();
    @Resource(name = "orderClient")
    private OrderService orderService;

    @Override
    public Order getModel() {
        return model;
    }

    private String sendAreaInfo;
    private String recAreaInfo;// 这两个是city_picker插件上传的数据,安徽省/铜陵市/铜陵县,没法直接封装到model中

    public void setSendAreaInfo(String sendAreaInfo) {
        this.sendAreaInfo = sendAreaInfo;
    }

    public void setRecAreaInfo(String recAreaInfo) {
        this.recAreaInfo = recAreaInfo;
    }

    @Action(value = "orderAction_add", results = {
            @Result(name = "success", location = "/index.html", type = "redirect")})
    public String save() {
        Area sendArea = string2area(sendAreaInfo);
        model.setSendArea(sendArea);
        Area recArea = string2area(recAreaInfo);
        model.setRecArea(recArea);
        orderService.save(model);
        return SUCCESS;
    }

    public Area string2area(String areaInfo) {
        if (StringUtils.isNotEmpty(areaInfo)) {
            String[] strings = areaInfo.split("/");
            String province = strings[0];
            province = province.substring(0, province.length() - 1);
            String city = strings[1];
            city = city.substring(0, city.length() - 1);
            String district = strings[2];
            district = district.substring(0, district.length() - 1);
            Area area = new Area(province, city, district);
            return area;
        }
        return null;
    }
}

