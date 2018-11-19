package cn.itcast.controller;

import cn.itcast.pojo.ElectricityFees;
import cn.itcast.pojo.Page;
import cn.itcast.service.ElefeeService;
import cn.itcast.util.PoiUtils;
import cn.itcast.util.WebUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ElefeeController")
public class ElefeeController {

    @Autowired
    private ElefeeService elefeeService;

    @RequestMapping("/a")
    public String a() {
        return "/error.jsp";
    }


    @RequestMapping("/findElePage")
    public String showElefeeInfo(HttpServletRequest request, Model model) {
        //获取当前页数
        String pageNow = request.getParameter("pageNow");
        //获取总记录量
        int totalCount = (int) elefeeService.findEleCont();
        Page page = null;
        if (pageNow == null || "".equals(pageNow)) {
            page = new Page(1, totalCount);//初始化page,当前页是第一页，总记录数从数据库中查出来
            page.setTotalPageCount(page.getTotalPageCount());//设置总页数
        } else {
            page = new Page(Integer.parseInt(pageNow.trim()), totalCount);
            page.setTotalPageCount(page.getTotalPageCount());//设置总页数
        }
        List list = elefeeService.findElePage(page.getPageNow(), page.getPageSize());//查找当前页需要的数据，封装到list中，参数是pageNow和size
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        return "/a.jsp";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List list = elefeeService.findAll();
        model.addAttribute("list", list);
        return "/a.jsp";
    }


    @RequestMapping("/addRecordEle")
    public String addRecordEle(@RequestParam("file") CommonsMultipartFile file,
                               HttpServletRequest request, String datetype) {

        String regex = null;
        if(datetype==null || "".equals(datetype))
            regex = ".";//意外情况出现 ， 默认是 . 的格式 2004.9.11
        if("2".equals(datetype) || "3".equals(datetype))
            regex = "-";
        else
            regex = "\\.";
        System.out.println(datetype);
        System.out.println(regex);
        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path = sc.getRealPath("\\table") + "\\"; // 设定文件保存的目录
        System.out.println("文件保存的路径：" + path);
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();

        // 获得原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println("原始文件名:" + fileName);
        // 新文件名
        String newFileName = UUID.randomUUID() + fileName;
        System.out.println("新文件名:" + newFileName);
        int b, r = 17;
        //r是判断第几个列是时间列，第18列就是 r = 17  因为j=0开始
        if (!file.isEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                InputStream in = file.getInputStream();
                while ((b = in.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new FileInputStream(new File(path + newFileName)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("有异常！！！");
        }
        HSSFSheet sheet = null;
        int i = workbook.getSheetIndex("成绩表"); // sheet表名
        sheet = workbook.getSheetAt(i);

        //如果有8行数据  则sheet.getLastRowNum()为7
        int j = 0;
        for (j = 0; j < sheet.getLastRowNum() + 1; j++) {
            HSSFRow row = sheet.getRow(j);
            List list = new ArrayList();
            if (row != null) {
                //如果有18列数据  则row.getLastCellNum()为18
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    if (row.getCell(k) != null) {
                        //添加第18列日期数据
                        if (k == r) {
                            String[] Dstring = row.getCell(k).toString().split("\\.");
                            String[] Dstring2 = row.getCell(k).toString().split("-");
                            if (3 == Dstring.length || 3==Dstring2.length) {//如果能切割成3串的话，非表头
                                String datestr = PoiUtils.str2date(row.getCell(k).toString(),regex);//转换格式 yy-MM-dd
                                if("10101".equals(datestr)){//非表头还不能转换，那就是选择切割的符号不匹配
                                    System.out.println("excel表格日期格式不正确！**************");
                                    request.setAttribute("error","excel表格日期格式不正确！请设置为形如   2018.9.10   这样的格式，或检查excel表格中的日期格式是否与选择的日期格式统一。");
                                    return "/error.jsp";
                                }else{
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = null;
                                    try {//转换为date类型放到数据库中Mon Sep 10 00:00:00 CST 2018
                                        date = sdf.parse(datestr);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        System.out.println("excel表格到入到数据库中-转换为date时有异常***");
                                    }
                                    list.add(date);//把时间放进去
                                    System.out.println("把时间放进去：" + date);
                                }
                            } else {//处理表头情况
                                list.add(row.getCell(k));//第18列也有可能不是日期数据
                            }
                        } else {//添加前17列数据
                            list.add(row.getCell(k));
                        }
                        //System.out.print(row.getCell(k) + "\t");//输出数据
                    } else {
                        list.add("");//把空串添加进去
                        //System.out.print("\t");//把空的地方输出
                    }
                }//内层for循环
            }
            //System.out.println("");//换行输出
            /*Iterator it = list.iterator();
            while (it.hasNext()) {
                System.out.print(it.next() + "\t");
            }
            System.out.println("");
            System.out.println(j);
            System.out.println(list.size());*/
            //第一行列头不弄进数据库
            try{
                if (j > 0) {
                    ElectricityFees e = new ElectricityFees(list.get(0).toString(), list.get(1).toString(), Float.parseFloat(list.get(2).toString()), Float.parseFloat(list.get(3).toString()), Float.parseFloat(list.get(4).toString()), Float.parseFloat(list.get(5).toString()), Float.parseFloat(list.get(6).toString()), Float.parseFloat(list.get(7).toString()), Float.parseFloat(list.get(8).toString()), Float.parseFloat(list.get(9).toString()), Float.parseFloat(list.get(10).toString()), Float.parseFloat(list.get(11).toString()), Float.parseFloat(list.get(12).toString()), Float.parseFloat(list.get(13).toString()), Float.parseFloat(list.get(14).toString()), list.get(15).toString(), Float.parseFloat(list.get(16).toString()), (Date) list.get(17));
                    elefeeService.importRecordEle(e);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }//外层for循环

        return "/ElefeeController/findElePage.action";
    }

    @RequestMapping("/down")//导出为xls后缀的excel文件
    public String down(HttpServletResponse response, HttpServletRequest request, String totalPageCount, String pageNow) {
        int size = 50;//jsp页面每页显示多少条数据
        int countAll = elefeeService.findEleCont();//数据库中的总记录数
        int count = 0;//用来存放该打印出多少条记录
        int column = 18;//表格有多少列
        //从数据库拿出当前jsp页面显示的所有数据
        List list = elefeeService.findElePage(Integer.parseInt(pageNow.trim()), size);
        //如果是最后一页，则需要打印出的记录数就不一定是size了，那么就算一下
        if (Integer.parseInt(pageNow.trim()) == Integer.parseInt(totalPageCount.trim())) {
            count = countAll - size * (Integer.parseInt(pageNow.trim()) - 1);
        } else {//不是最后一页，那么需要打印的记录数就是size
            count = size;
        }

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("成绩表");//读的时候要知道是哪个表

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);//第一行
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        row1.createCell(0).setCellValue("站址编号");
        row1.createCell(1).setCellValue("站址名称");
        row1.createCell(2).setCellValue("单价-元");
        row1.createCell(3).setCellValue("上月余额");
        row1.createCell(4).setCellValue("本月预存");
        row1.createCell(5).setCellValue("电表起度");
        row1.createCell(6).setCellValue("电表止度");
        row1.createCell(7).setCellValue("用电量-度");
        row1.createCell(8).setCellValue("损耗-元");
        row1.createCell(9).setCellValue("损耗-元");
        row1.createCell(10).setCellValue("农电附加-元");
        row1.createCell(11).setCellValue("基站分摊比例-联通");
        row1.createCell(12).setCellValue("基站分摊比例-移动");
        row1.createCell(13).setCellValue("基站分摊比例-电信");
        row1.createCell(14).setCellValue("总费用-元");
        row1.createCell(15).setCellValue("抄表期间");
        row1.createCell(16).setCellValue("折月");
        row1.createCell(17).setCellValue("导入日期");

        HSSFRow row = null;
        for (int i = 1; i <= count; i++) {
            row = sheet.createRow(i);//在sheet里创建第i+1行  第i行是表头
            ElectricityFees ele = (ElectricityFees) list.get(i - 1);

            if (ele.getSitenum() != null)
                row.createCell(0).setCellValue(ele.getSitenum());
            if (ele.getSitename() != null)
                row.createCell(1).setCellValue(ele.getSitename());
            if (ele.getUnitprice() != null)
                row.createCell(2).setCellValue(ele.getUnitprice());
            if (ele.getBalance() != null)
                row.createCell(3).setCellValue(ele.getBalance());
            if (ele.getPredeposit() != null)
                row.createCell(4).setCellValue(ele.getPredeposit());
            if (ele.getMeterrise() != null)
                row.createCell(5).setCellValue(ele.getMeterrise());
            if (ele.getMeterstop() != null)
                row.createCell(6).setCellValue(ele.getMeterstop());
            if (ele.getEleconsumption() != null)
                row.createCell(7).setCellValue(ele.getEleconsumption());
            if (ele.getLoss() != null)
                row.createCell(8).setCellValue(ele.getLoss());
            if (ele.getTaxation() != null)
                row.createCell(9).setCellValue(ele.getTaxation());
            if (ele.getAgriculturaleleadd() != null)
                row.createCell(10).setCellValue(ele.getAgriculturaleleadd());
            if (ele.getbSARUnicom() != null)
                row.createCell(11).setCellValue(ele.getbSARUnicom());
            if (ele.getbSARMobile() != null)
                row.createCell(12).setCellValue(ele.getbSARMobile());
            if (ele.getbSARTelecom() != null)
                row.createCell(13).setCellValue(ele.getbSARTelecom());
            if (ele.getTotalcost() != null)
                row.createCell(14).setCellValue(ele.getTotalcost());
            if (ele.getMeterreading() != null)
                row.createCell(15).setCellValue(ele.getMeterreading());
            if (ele.getMonthfolding() != null)
                row.createCell(16).setCellValue(ele.getMonthfolding());
            if (ele.getImportdate() != null) {
                System.out.println("导出时的日期是" + ele.getImportdate());
                //导出时的日期ele.getImportdate()是   Sun Sep 10 00:00:00 CST 2017  因为是直接从数据库中拿出来的datetime类型
                //格式化日期转换器把从数据库中取出的datetime类型转换为date类型
                String data = DateFormat.getDateInstance().format(ele.getImportdate());
                //System.out.println(data);//把date类型（yy-MM-dd）设置到单元格里
                row.createCell(17).setCellValue(data);
            }

        }
        //.....省略部分插入数据修改表格样式等对excel表内容操作的代码

        //调整单元格大小: 必须在单元格设值以后进行
        for (int k = 0; k < column; k++) {
            sheet.autoSizeColumn(k); // 设置为根据内容自动调整列宽
            sheet.setColumnWidth(k, sheet.getColumnWidth(k) * 17 / 10);//设置列宽为自动列宽的1.7倍（当然不是严格的1.7倍，int的除法恕不再讨论），这个1.6左右也可以，这是本人测试的经验值
        }


        try {
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=dianfei.xls");//文件名
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
        } catch (IOException e) {

        }

        return "/a.jsp";//好像没用，返回到哪都一样，都是显示当前页面
    }

    @RequestMapping("/conditionquery")
    public String conditionquery(HttpServletRequest request, String sitename, String year, String month) {
        List list = elefeeService.conditionquery(sitename, year, month);
        request.setAttribute("List", list);
        return "/a.jsp";
    }


}


