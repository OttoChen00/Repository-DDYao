package com.itheima.bos.service.base.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.itheima.bos.dao.base.CourierDao;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.CourierService;

/**
 * ClassName:CourierServiceImpl <br/>
 * Function: <br/>
 * Date: 2017年9月17日 下午4:33:43 <br/>
 */
@Transactional
@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierDao courierDao;

    @Override
    public void save(Courier model) {
        courierDao.save(model);
    }

    @Override
    public Page<Courier> pageQuery(Pageable pageable, Specification<Courier> specification) {
        return courierDao.findAll(specification, pageable);
    }

    @Override
    @RequiresPermissions(value = {"courier:delete"})
    public void courierAction_setDeltagById(String ids) {
        if (!StringUtils.isEmpty(ids)) {
            String[] idStrings = ids.split(",");
            for (String id : idStrings) {
                courierDao.updateDeltagById(Integer.parseInt(id));
            }
        }
    }

    @Override
    public List<Courier> findCourierNotDel() {
        return courierDao.findByDeltagIsNull();
    }

}

