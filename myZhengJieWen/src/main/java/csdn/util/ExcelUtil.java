package csdn.util;

import csdn.pojo.Department;
import csdn.pojo.Person;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ExcelUtil {
    public static void main(String[] args) throws IOException {
        Map<String,Department> map = new TreeMap<String, Department>();
        ExcelUtil excelUtil = new ExcelUtil();
        //获取到部门员工的数据
        InputStream resourceAsStream1 = excelUtil.getClass().getClassLoader().getResourceAsStream("研发部-薪酬表.xlsx");
        excelUtil.readExcel_Department("研发部", resourceAsStream1,map);
        //获取到部门员工的数据
        InputStream resourceAsStream2 = excelUtil.getClass().getClassLoader().getResourceAsStream("大客户部-薪酬表.xlsx");
        excelUtil.readExcel_Department("大客户部",resourceAsStream2,map);
        //获取到部门员工的数据
        InputStream resourceAsStream3 = excelUtil.getClass().getClassLoader().getResourceAsStream("市场部-薪酬表.xlsx");
        excelUtil.readExcel_Department("市场部",resourceAsStream3,map);
        //获取到部门员工的数据
        InputStream resourceAsStream4 = excelUtil.getClass().getClassLoader().getResourceAsStream("销售部-薪酬表.xlsx");
        excelUtil.readExcel_Department("销售部",resourceAsStream4,map);

        //读取员工五险一金申报表
        InputStream resourceAsStream5 = excelUtil.getClass().getClassLoader().getResourceAsStream("员工五险一金申报表.xlsx");
        excelUtil.readExcel_Department(resourceAsStream5,map);


        Set<String> strings = map.keySet();
        for(String str:strings){
            Department department = map.get(str);
            System.out.println(str+" "+department);
        }


        String str = "C:\\Users\\38306\\Desktop\\企业员工月度工资成本支付表.xlsx";
        File file = new File(str);

        excelUtil.fillExcel_Department(map,file);

//        String path = ExcelUtil.class.getClassLoader().getResource("企业员工月度工资成本支付表.xlsx").getPath();
//        System.out.println();
//        File file = new File("企业员工月度工资成本支付表.xlsx");
//        System.out.println(file.exists());




    }


    /**
     *
     * @param departmentName
     * @param inputStream
     * @return
     * @throws IOException
     */
    public void readExcel_Department(String departmentName, InputStream inputStream,Map<String,Department> map) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheetAt.rowIterator();
        //排除掉表头
        rowIterator.hasNext();
        Row head = rowIterator.next();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Department department = new Department();
            department.setDepartment(departmentName);
            department.setJobNumber(row.getCell(0).getStringCellValue());
            department.setName(row.getCell(1).getStringCellValue());
            department.setBasicSalary(row.getCell(2).getNumericCellValue());
            department.setPostWage(row.getCell(3).getNumericCellValue());
            department.setPerformanceBonus(row.getCell(4).getNumericCellValue());
            department.setAttendanceBonus(row.getCell(5).getNumericCellValue());
            department.setAttendanceDeduction(row.getCell(6).getNumericCellValue());
            department.setPenalties_for_non_compliance(row.getCell(7).getNumericCellValue());
            department.setTransportationSubsidies(row.getCell(8).getNumericCellValue());
            department.setCommunicationsGrants(row.getCell(9).getNumericCellValue());
            map.put(department.getJobNumber(),department);
        }
        xssfWorkbook.close();
    }

    /**
     *
     * @param inputStream
     * @param map
     * @throws IOException
     */
    public void readExcel_Department(InputStream inputStream,Map<String,Department> map) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheetAt.rowIterator();
        //排除掉表头
        rowIterator.hasNext();
        Row head = rowIterator.next();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            int id = (int)row.getCell(0).getNumericCellValue();
            double num1 = row.getCell(2).getNumericCellValue(); //工资基数
            double num2 = row.getCell(3).getNumericCellValue(); //公积金比例

            Department department = map.get(""+id);
            department.setEndowmentInsuranceSelf(num1*0.08);
            department.setEndowmentInsuranceCompany(num1*0.2);

            department.setMedicareSelf(num1*0.02);
            department.setMedicareCompany(num1*0.08);

            department.setUnemploymentInsuranceSelf(num1*0.01);
            department.setUnemploymentInsuranceConpany(num1*0.02);

            department.setEmploymentInjuryInsuranceSelf(0);
            department.setEmploymentInjuryInsuranceConpany(num1*0.005);

            department.setBirthInsuranceSelf(num1*0.0);
            department.setBirthInsuranceConpany(num1*0.007);

            department.setReservedFundsSelf(num1*num2);
            department.setReservedFundsConpany(num1*num2);

            map.put(""+id,department);
        }
        xssfWorkbook.close();
    }

    /**
     *
     * @param data
     * @param destFile
     * @throws IOException
     */
    public void fillExcel_Department(Map<String,Department> data, File destFile) throws IOException {
        //创建一个工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //在工作簿里创建一张表
        XSSFSheet sheet = xssfWorkbook.createSheet("用户");
        //行中添加数据
        Set<String> keys = data.keySet();
        int i=2;
        for(String str:keys){
            Department department = data.get(str);
            //正收益
            double add =department.getAdd();
            //考勤扣除、违规处罚等负收益项
            double decrease=department.getMinusYield();
            //应发工资=工资-扣款
            double wages = department.getSalary();
            XSSFRow r = sheet.createRow(i++);
            r.createCell(0).setCellValue(department.getJobNumber());
            r.createCell(1).setCellValue(department.getName());
            r.createCell(2).setCellValue(department.getDepartment());
            r.createCell(3).setCellValue(add);
            r.createCell(4).setCellValue(decrease);
            r.createCell(20).setCellValue(wages);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
    }
}
