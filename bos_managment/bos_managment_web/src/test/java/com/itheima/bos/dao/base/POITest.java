package com.itheima.bos.dao.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.base.AreaService;
import com.itheima.bos.utils.PinYin4jUtils;

/**  
 * ClassName:POITest <br/>  
 * Function:  <br/>  
 * Date:     2017年9月26日 下午4:49:31 <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class POITest {
    @Autowired
    private AreaService areaService;
    @Test
    public void test_01() throws FileNotFoundException, IOException {
        File file = new File("H:/j1ava1/黑马17期/第6阶段 BOS/Day04/资料/03_区域测试数据/区域导入测试数据.xlsx");
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
             System.out.println(id+"/t"+province+"/t"+city+"/t"+district+"/t"+postcode+"/t");

            String citycode = PinYin4jUtils.hanziToPinyin(city).toUpperCase();

            String[] headByString = PinYin4jUtils.getHeadByString(province + city + district);
            String shortcode = PinYin4jUtils.stringArrayToString(headByString);

            Area area = new Area(id, province, city, district, postcode, citycode, shortcode);
            list.add(area);
        }
        areaService.saveList(list);
        workbook.close();
    }
}
  
