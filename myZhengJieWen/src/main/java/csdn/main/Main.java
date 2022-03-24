package csdn.main;

import csdn.pojo.Department;
import csdn.util.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        //存储各个部门的数据
        Map<String, Department> map = new TreeMap<String, Department>();
        ExcelUtil excelUtil = new ExcelUtil();
        //获取到研发部部门员工的数据
        InputStream resourceAsStream1 = excelUtil.getClass().getClassLoader().getResourceAsStream("研发部-薪酬表.xlsx");
        excelUtil.readExcel_Department("研发部", resourceAsStream1,map);
        //获取到大客户部员工的数据
        InputStream resourceAsStream2 = excelUtil.getClass().getClassLoader().getResourceAsStream("大客户部-薪酬表.xlsx");
        excelUtil.readExcel_Department("大客户部",resourceAsStream2,map);
        //获取到市场部部门员工的数据
        InputStream resourceAsStream3 = excelUtil.getClass().getClassLoader().getResourceAsStream("市场部-薪酬表.xlsx");
        excelUtil.readExcel_Department("市场部",resourceAsStream3,map);
        //获取到销售部部门员工的数据
        InputStream resourceAsStream4 = excelUtil.getClass().getClassLoader().getResourceAsStream("销售部-薪酬表.xlsx");
        excelUtil.readExcel_Department("销售部",resourceAsStream4,map);

        //读取员工五险一金申报表
        InputStream resourceAsStream5 = excelUtil.getClass().getClassLoader().getResourceAsStream("员工五险一金申报表.xlsx");
        excelUtil.readExcel_Department(resourceAsStream5,map);

        String str = "src\\main\\resources\\企业员工月度工资成本支付表.xlsx";
        File file = new File(str);
        //把数据写入到表中
        excelUtil.fillExcel_Department(map,file);

    }
}
