package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.CourierService;
import com.itheima.bos.web.action.common.CommonAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:Courier <br/>
 * Function: <br/>
 * Date: 2017年9月17日 下午4:26:03 <br/>
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CourierAction extends CommonAction<Courier> {

    public CourierAction() {
        super(Courier.class);
    }

    @Autowired
    private CourierService courierService;

    @Action(value = "courierAction_save", results = {
            @Result(name = "success", location = "/pages/base/courier.html", type = "redirect")})
    public String save() {
        courierService.save(getModel());
        return SUCCESS;
    }



    @Action("courierAction_pageQuery")
    public String pageQuery() throws IOException {

        // new 一个Spring JAP 的查询对象
        Specification<Courier> specification = new Specification<Courier>() {

            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(getModel().getCourierNum())) {
                    Predicate p1 = cb.equal(root.get("courierNum").as(String.class), getModel().getCourierNum());
                    list.add(p1);
                }
                if (!StringUtils.isEmpty(getModel().getCompany())) {
                    Predicate p2 = cb.like(root.get("company").as(String.class), "%" + getModel().getCompany() + "%");
                    list.add(p2);
                }
                if (!StringUtils.isEmpty(getModel().getType())) {
                    Predicate p3 = cb.equal(root.get("type").as(String.class), getModel().getType());
                    list.add(p3);
                }
                if (getModel().getStandard() != null && !StringUtils.isEmpty(getModel().getStandard().getName())) {
                    Join join = root.join("standard");
                    Predicate p4 = cb.equal(join.get("name").as(String.class), getModel().getStandard().getName());
                    list.add(p4);
                }

                Predicate[] array = new Predicate[list.size()];
                return cb.and(list.toArray(array));
            }
        };


        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Courier> page = courierService.pageQuery(pageable, specification);
        String[] excludes = new String[] {"takeTime", "fixedAreas"};
        page2json(page, excludes);

        return NONE;
    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Action(value = "courierAction_setDeltagById", results = {
            @Result(name = "success", location = "/pages/base/courier.html", type = "redirect")})
    public String setDeltagById() {
        try {
            courierService.courierAction_setDeltagById(ids);
        } catch (UnauthorizedException e) {
            System.out.println("当前用户没有权限");
        }
        return SUCCESS;
    }


    /**
     * listajax:.查询没有被逻辑删除的所有快递员 <br/>
     * 
     * @return
     * @throws IOException
     */
    @Action(value = "courierAction_listajax")
    public String listajax() throws IOException {
        List<Courier> list = courierService.findCourierNotDel();
        list2json(list, new String[] {"id", "name"}, false);
        return NONE;
    }

}

