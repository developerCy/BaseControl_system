package util;

import org.apache.poi.hssf.usermodel.*;
import pojo.EtpsInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by changyu on 2017/7/13 0013.
 */
public class ExcelUtil {
    public static void out_Etps_info_list_to_Excel(List<EtpsInfo> etpsInfo_list, HttpServletResponse response) {
        OutputStream outputStream=null;
        try {
            HSSFWorkbook excel = new HSSFWorkbook();  //--->创建了一个excel文件
            HSSFSheet sheet = excel.createSheet("商户信息汇总");   //--->创建了一个工作簿
            //表格第一行
            HSSFRow row = sheet.createRow(0);   //--->创建一行
            row.createCell((short) 0).setCellValue("渠道商");   //--->创建一个单元格
            row.createCell((short) 1).setCellValue("商户");   //--->创建一个单元格
            row.createCell((short) 2).setCellValue("手机号");   //--->创建一个单元格
            row.createCell((short) 3).setCellValue("地址");   //--->创建一个单元格
            row.createCell((short) 4).setCellValue("邮箱");   //--->创建一个单元格
            row.createCell((short) 5).setCellValue("状态");   //--->创建一个单元格
            row.createCell((short) 6).setCellValue("创建时间");   //--->创建一个单元格
            //表格第i行
            for (int i = 0; i < etpsInfo_list.size(); i++) {
                HSSFRow row2 = sheet.createRow(i + 1);   //--->创建一行
                row2.createCell((short) 0).setCellValue(etpsInfo_list.get(i).getiAgent_name());
                row2.createCell((short) 1).setCellValue(etpsInfo_list.get(i).getEtps_name());
                row2.createCell((short) 2).setCellValue(etpsInfo_list.get(i).getContact_phone());
                row2.createCell((short) 3).setCellValue(etpsInfo_list.get(i).getContact_address());
                row2.createCell((short) 4).setCellValue(etpsInfo_list.get(i).getContact_mail());
                row2.createCell((short) 5).setCellValue(etpsInfo_list.get(i).getStatus());
                row2.createCell((short) 6).setCellValue(etpsInfo_list.get(i).getCreate_time());
            }
            response.reset();
            String file_name="商户信息汇总.xls";
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="+new String(file_name.getBytes("GBK"),"ISO8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=GBK");
            outputStream= response.getOutputStream();
            excel.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}