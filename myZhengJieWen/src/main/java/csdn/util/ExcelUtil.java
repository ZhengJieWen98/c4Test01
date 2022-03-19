package csdn.util;

import csdn.test.Person;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static void main(String[] args) throws IOException {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("张三","成都","12345678910"));
        list.add(new Person("李四","北京","6666666666"));
        list.add(new Person("王麻子","上海","8888888888"));
        ExcelUtil excelWriter = new ExcelUtil();
        excelWriter.fillExcel(list,new File("C:\\Users\\38306\\Desktop\\persion.xlsx"));
    }

    /**
     * 填充Excel
     * @param data 用户数据
     * @param destFile 目标文件
     */
    public void fillExcel(List<Person> data, File destFile) throws IOException {
        //创建一个工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //在工作簿里创建一张表
        XSSFSheet sheet = xssfWorkbook.createSheet("用户");
        //表中创建一行
        XSSFRow row = sheet.createRow(0);
        //行中添加数据
        row.createCell(0).setCellValue("名称");
        row.createCell(1).setCellValue("城市");
        row.createCell(2).setCellValue("手机号码");
        for(int i=0;i<data.size();i++){
            Person person = data.get(i);
            System.out.println(person);
            XSSFRow r = sheet.createRow(i+1);
            r.createCell(0).setCellValue(person.getName());
            r.createCell(1).setCellValue(person.getCity());
            r.createCell(2).setCellValue(person.getCellPhone());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
    }
}
