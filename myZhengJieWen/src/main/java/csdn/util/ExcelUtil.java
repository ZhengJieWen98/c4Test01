package csdn.util;

import csdn.pojo.Department;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelUtil {
    /**
     * 获取各个部门的数据
     * @param departmentName 部门名
     * @param inputStream 输入流
     * @param map 存储数据的集合
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
     * 读取五险一金的数据
     * @param inputStream 输入流
     * @param map 计算出五险一金的数据存储在map集合中
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
     * 存储数据
     * @param data 存储数据的集合
     * @param destFile 存储目标文件
     * @throws IOException
     */
    public void fillExcel_Department(Map<String,Department> data, File destFile) throws IOException {
        //创建一个工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //在工作簿里创建一张表
        XSSFSheet sheet = xssfWorkbook.createSheet("用户");
        //
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("工号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("部门");
        row.createCell(3).setCellValue("工资");
        row.createCell(4).setCellValue("扣款");
        row.createCell(5).setCellValue("养老(个人)");
        row.createCell(6).setCellValue("医疗(个人)");
        row.createCell(7).setCellValue("失业(个人)");
        row.createCell(8).setCellValue("工伤(个人)");
        row.createCell(9).setCellValue("生育(个人)");
        row.createCell(10).setCellValue("公积金(个人)");
        row.createCell(11).setCellValue("合计(个人)");
        row.createCell(12).setCellValue("养老(公司)");
        row.createCell(13).setCellValue("医疗(公司)");
        row.createCell(14).setCellValue("失业(公司)");
        row.createCell(15).setCellValue("工伤(公司)");
        row.createCell(16).setCellValue("生育(公司)");
        row.createCell(17).setCellValue("公积金(公司)");
        row.createCell(18).setCellValue("合计(公司)");
        row.createCell(19).setCellValue("个税金额");
        row.createCell(20).setCellValue("应发工资");
        row.createCell(21).setCellValue("实发工资");
        row.createCell(22).setCellValue("企业支出成本");
        //行中添加数据
        Set<String> keys = data.keySet();
        int i=1;
        for(String str:keys){
            Department department = data.get(str);
            //正收益
            double add =department.getAdd();
            //考勤扣除、违规处罚等负收益项
            double decrease=department.getMinusYield();
            //应发工资=工资-扣款
            double wages = department.getSalary();
            XSSFRow r = sheet.createRow(i++);
            //工号
            r.createCell(0).setCellValue(department.getJobNumber());
            //姓名
            r.createCell(1).setCellValue(department.getName());
            //部门
            r.createCell(2).setCellValue(department.getDepartment());
            //工资
            r.createCell(3).setCellValue(department.getAdd());
            //扣款
            r.createCell(4).setCellValue(department.getMinusYield());
            //养老(个人)
            r.createCell(5).setCellValue(department.getEndowmentInsuranceSelf());
            //医疗(个人)
            r.createCell(6).setCellValue(department.getMedicareSelf());
            //失业(个人)
            r.createCell(7).setCellValue(department.getUnemploymentInsuranceSelf());
            //工伤(个人)
            r.createCell(8).setCellValue(department.getEmploymentInjuryInsuranceSelf());
            //生育(个人)
            r.createCell(9).setCellValue(department.getBirthInsuranceSelf());
            //公积金(个人)
            r.createCell(10).setCellValue(department.getReservedFundsSelf());
            //合计(个人)
            r.createCell(11).setCellValue(department.getSelf());
            //养老(公司)
            r.createCell(12).setCellValue(department.getEndowmentInsuranceCompany());
            //医疗(公司)
            r.createCell(13).setCellValue(department.getMedicareCompany());
            //失业(公司)
            r.createCell(14).setCellValue(department.getUnemploymentInsuranceConpany());
            //工伤(公司)
            r.createCell(15).setCellValue(department.getEmploymentInjuryInsuranceConpany());
            //生育(公司)
            r.createCell(16).setCellValue(department.getBirthInsuranceConpany());
            //公积金(公司)
            r.createCell(17).setCellValue(department.getReservedFundsConpany());
            //合计(公司)
            r.createCell(18).setCellValue(department.getConpany());
            //个税金额
            r.createCell(19).setCellValue(department.getTax());
            //应发工资
            r.createCell(20).setCellValue(department.getSalary());
            //实发工资
            r.createCell(21).setCellValue(department.getNetPayroll());
            //企业支出成本
            r.createCell(22).setCellValue(department.getAllConpany());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
    }
}
