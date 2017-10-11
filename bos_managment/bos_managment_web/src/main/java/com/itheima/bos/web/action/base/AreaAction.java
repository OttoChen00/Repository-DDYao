package com.itheima.bos.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.base.AreaService;
import com.itheima.bos.utils.FileUtils;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.common.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:AreaAction <br/>
 * Function: <br/>
 * Date: 2017年9月19日 下午4:54:40 <br/>
 */

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class AreaAction extends BaseAction<Area> {

    private static final long serialVersionUID = 1747602265002468471L;

    @Autowired
    private AreaService areaService;

    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    /**
     * improt:. 导入Excel表格到数据库, 使用的技术:POI、PinYin4j <br/>
     * 
     * @return
     * @throws InvalidFormatException
     * @throws IOException
     */
    @Action(value = "areaAction_import", results = {
            @Result(name = "success", type = "redirect", location = "/pages/base/area.html")})
    public String improt() throws InvalidFormatException, IOException {
        System.out.println(file);
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Area> list = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            province = province.substring(0, province.length() - 1);
            String city = row.getCell(2).getStringCellValue();
            city = city.substring(0, city.length() - 1);
            String district = row.getCell(3).getStringCellValue();
            district = district.substring(0, district.length() - 1);
            String postcode = row.getCell(4).getStringCellValue();
            //System.out.println(id+"\t"+province+"\t"+city+"\t"+district+"\t"+postcode+"\t");

            String citycode = PinYin4jUtils.hanziToPinyin(city).toUpperCase();

            String[] headByString = PinYin4jUtils.getHeadByString(province + city + district);
            String shortcode = PinYin4jUtils.stringArrayToString(headByString);

            Area area = new Area(id, province, city, district, postcode, citycode, shortcode);
            list.add(area);
        }
        areaService.saveList(list);
        workbook.close();
        return SUCCESS;
    }

    @Action(value = "areaAction_pageQuery")
    public String pageQuery() throws IOException {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Area> page = areaService.pageQuery(pageable);
        page2json(page, new String[] {"subareas"});
        return NONE;
    }

    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    @Action(value = "areaAction_findAll")
    public String findAll() throws IOException {
        List<Area> list = null;
        if (StringUtils.isEmpty(q)) {
            list = areaService.findAll();
        } else {
            list = areaService.findByQ(q);
        }
        list2json(list, new String[] {"subareas"});
        return NONE;
    }
    
    @Action(value = "areaAction_export")
    public String export() throws IOException {
        List<Area> list = areaService.findAll();
        
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet();
        XSSFRow topRow = xssfSheet.createRow(0);
        topRow.createCell(0).setCellValue("省");
        topRow.createCell(1).setCellValue("市");
        topRow.createCell(2).setCellValue("区");
        topRow.createCell(3).setCellValue("邮编");
        topRow.createCell(4).setCellValue("简码");
        topRow.createCell(5).setCellValue("城市编码");
        
        for (Area area : list) {
            XSSFRow createRow = xssfSheet.createRow(xssfSheet.getLastRowNum()+1);
            createRow.createCell(0).setCellValue(area.getProvince());
            createRow.createCell(1).setCellValue(area.getCity());
            createRow.createCell(2).setCellValue(area.getDistrict());
            createRow.createCell(3).setCellValue(area.getPostcode());
            createRow.createCell(4).setCellValue(area.getShortcode());
            createRow.createCell(5).setCellValue(area.getCitycode());
        }
        // 文件下载：一输出流，两消息头
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletContext servletContext = ServletActionContext.getServletContext();
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        
        String fileName = "区域资料.xlsx";
        // 根据不同浏览器（可以在请求头中获知）对文件名重新编码，火狐用的Base64，其他用的是UTF-8
        fileName = FileUtils.encodeDownloadFilename(fileName, agent);
        // 获取文件的MINE类型
        String mimeType = servletContext.getMimeType(fileName);
        // 设置文件的MINE类型
        response.setContentType(mimeType);
        // 设置下载的响应头
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        ServletOutputStream outputStream = response.getOutputStream();
        xssfWorkbook.write(outputStream);
        
        xssfWorkbook.close();
        return NONE;
    }
}

