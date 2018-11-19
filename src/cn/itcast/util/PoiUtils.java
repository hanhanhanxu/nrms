package cn.itcast.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class PoiUtils {

    /*把数据库中的数据导入到excel表格时使用：
    先把数据查询出来，然后再传进来就会返回一个HSSFWorkbook对象，
    拿着这个对象到相应的流中创建文件
    */
    public static HSSFWorkbook d2excel(int row , int col , String name , Map map){

        HSSFWorkbook wb = new HSSFWorkbook();//创建HSSFWorkbook对象(excel的文档对象)
        HSSFSheet sheet=wb.createSheet(name); //建立新的sheet对象（excel的表单）//读的时候要知道是哪个表

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);//第一行
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        for(int i=0;i<map.size();i++) {
            List list = (List)map.get(i);
            HSSFRow Hrow = null;
            Hrow = sheet.createRow(i);//在sheet里创建第i+1行  第i行是表头
            for(int j=0;j<list.size();j++) {
                Hrow.createCell(j).setCellValue((String) list.get(j));
            }
        }

        //调整单元格大小: 必须在单元格设值以后进行
        for (int k = 0; k < col; k++) {
            sheet.autoSizeColumn(k); // 设置为根据内容自动调整列宽
            sheet.setColumnWidth(k,sheet.getColumnWidth(k)*17/10);//设置列宽为自动列宽的1.7倍（当然不是严格的1.7倍，int的除法恕不再讨论），这个1.6左右也可以，这是本人测试的经验值
        }

        return wb;
    }

    public static Map e2d(String name , String filePathAndfileName) {

        Map<Integer,List> map = new HashMap();
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new FileInputStream(new File(filePathAndfileName)));//得到excel表格
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("不能正确得到excel表格！！！");
        }

        HSSFSheet sheet = null;
        int i = workbook.getSheetIndex(name); // sheet表名
        sheet = workbook.getSheetAt(i);

        //如果有8行数据  则sheet.getLastRowNum()为7
        int j = 0;
        for (j = 0; j < sheet.getLastRowNum() + 1; j++) {
            List list = new ArrayList();
            HSSFRow row = sheet.getRow(j);

            if (row != null) {
                //如果有18列数据  则row.getLastCellNum()为18
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    if (row.getCell(k) != null) {
                        list.add(row.getCell(k));
                        //System.out.print(row.getCell(k) + "\t");//输出数据
                    } else {
                        list.add("");//把空串添加进去
                        //System.out.print("\t");//把空的地方输出
                    }
                }//内层for循环
            }
            //System.out.println("");//换行输出

            //第一行列头不添加进数据库
            if(j>0)
                map.put(j-1, list);//list不为null  有空间但是没有值isEmpty()
        }//外层for循环

        return map;
    }

    public static String str2date(String str,String regex){
        System.out.println("在str2date函数中regex为：" + regex);
        System.out.println("在str2date函数中row.getCell(k).toString()为：" + str);

        String dstr = null;
        String[] Dstring = str.split(regex);//按规格切割
        if (3 == Dstring.length) {//已经不是表头了，再切不成三块，那就是不匹配，直接返回10101
            System.out.println("在str2date函数中切割后的数据，Dstring[0] + Dstring[1] + Dstring[2]：" + Dstring[0] + Dstring[1] + Dstring[2]);
            if(WebUtils.isDigit(Dstring[1])==false){//是汉字，将  九月  转换为 9
                System.out.println("转换前Dstring[1]："+Dstring[1]);
                Dstring[1] =  WebUtils.word2num(Dstring[1]);
                System.out.println("转换后Dstring[1]："+Dstring[1]);
            }
            //将其组合成   年 月 日   的顺序
            if(Dstring[2].length()>Dstring[0].length()) {//不管是不是excel表格日期格式与选择的格式统一，都比一下，即使有混杂的情况也能解决
                dstr = new String(Dstring[2] + "-" + Dstring[1] + "-" + Dstring[0]);
            }else{
                dstr = new String(Dstring[0] + "-" + Dstring[1] + "-" + Dstring[2]);
            }
            System.out.println("在str2date函数中组合之后的字符串：" + dstr);
        }else{
            dstr = "10101";
        }
        return dstr;
    }
}
