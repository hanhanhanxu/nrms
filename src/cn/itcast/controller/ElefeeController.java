package cn.itcast.controller;

import cn.itcast.pojo.ElectricityFees;
import cn.itcast.pojo.Page;
import cn.itcast.service.ElefeeService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.List;

@Controller
@RequestMapping("/ElefeeController")
public class ElefeeController {

    @Autowired
    private ElefeeService elefeeService;

    @RequestMapping("/a")
    public String a(){
        return "/error.jsp";
    }

    @RequestMapping("/findElePage")
    public String showElefeeInfo(HttpServletRequest request, Model model) {
        //获取当前页数
        String pageNow = request.getParameter("pageNow");
        //获取总记录量
        int totalCount = (int) elefeeService.findEleCont();
        Page page = null;
        if(pageNow==null || "".equals(pageNow)){
            page = new Page(1,totalCount);//初始化page,当前页是第一页，总记录数从数据库中查出来
            page.setTotalPageCount(page.getTotalPageCount());//设置总页数
        }else{
            page = new Page(Integer.parseInt(pageNow.trim()), totalCount);
            page.setTotalPageCount(page.getTotalPageCount());//设置总页数
        }
        List list = elefeeService.findElePage(page.getPageNow(),page.getPageSize());//查找当前页需要的数据，封装到list中，参数是pageNow和size
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        return "/a.jsp";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List list = elefeeService.findAll();
        model.addAttribute("list", list);
        return "/a.jsp";
    }

    @RequestMapping("/down")//导出为xls后缀的excel文件
    public String down(HttpServletResponse response,HttpServletRequest request,String totalPageCount,String pageNow){
        int size = 50;//jsp页面每页显示多少条数据
        int countAll = elefeeService.findEleCont();//数据库中的总记录数
        int count = 0;//用来存放该打印出多少条记录
        int column = 17;//表格有多少列
        //从数据库拿出当前jsp页面显示的所有数据
        List list = elefeeService.findElePage(Integer.parseInt(pageNow.trim()),size);
        //如果是最后一页，则需要打印出的记录数就不一定是size了，那么就算一下
        if(Integer.parseInt(pageNow.trim())==Integer.parseInt(totalPageCount.trim())){
            count = countAll-size*(Integer.parseInt(pageNow.trim())-1);
        }else {//不是最后一页，那么需要打印的记录数就是size
            count = size;
        }


        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("成绩表");//好像没用，没体现出来“成绩表”这三个字在哪


        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);//第一行
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        row1.createCell(0).setCellValue("站址名称");
        row1.createCell(1).setCellValue("单价-元");
        row1.createCell(2).setCellValue("上月余额");
        row1.createCell(3).setCellValue("本月预存");
        row1.createCell(4).setCellValue("电表起度");
        row1.createCell(5).setCellValue("电表止度");
        row1.createCell(6).setCellValue("用电量-度");
        row1.createCell(7).setCellValue("损耗-元");
        row1.createCell(8).setCellValue("损耗-元");
        row1.createCell(9).setCellValue("农电附加-元");
        row1.createCell(10).setCellValue("基站分摊比例-联通");
        row1.createCell(11).setCellValue("基站分摊比例-移动");
        row1.createCell(12).setCellValue("基站分摊比例-电信");
        row1.createCell(13).setCellValue("总费用-元");
        row1.createCell(14).setCellValue("抄表期间");
        row1.createCell(15).setCellValue("折月");
        row1.createCell(16).setCellValue("导入日期");

        HSSFRow row = null;
        for(int i=1;i<=count;i++){
            row = sheet.createRow(i);//在sheet里创建第i+1行  第i行是表头
            ElectricityFees ele = (ElectricityFees)list.get(i-1);

            if(ele.getSitename()!=null)
            row.createCell(0).setCellValue(ele.getSitename());
            if(ele.getUnitprice()!=null)
            row.createCell(1).setCellValue(ele.getUnitprice());
            if(ele.getBalance()!=null)
            row.createCell(2).setCellValue(ele.getBalance());
            if(ele.getPredeposit()!=null)
            row.createCell(3).setCellValue(ele.getPredeposit());
            if(ele.getMeterrise()!=null)
            row.createCell(4).setCellValue(ele.getMeterrise());
            if(ele.getMeterstop()!=null)
            row.createCell(5).setCellValue(ele.getMeterstop());
            if(ele.getEleconsumption()!=null)
            row.createCell(6).setCellValue(ele.getEleconsumption());
            if(ele.getLoss()!=null)
            row.createCell(7).setCellValue(ele.getLoss());
            if(ele.getTaxation()!=null)
            row.createCell(8).setCellValue(ele.getTaxation());
            if(ele.getAgriculturaleleadd()!=null)
            row.createCell(9).setCellValue(ele.getAgriculturaleleadd());
            if(ele.getbSARUnicom()!=null)
            row.createCell(10).setCellValue(ele.getbSARUnicom());
            if(ele.getbSARMobile()!=null)
            row.createCell(11).setCellValue(ele.getbSARMobile());
            if(ele.getbSARTelecom()!=null)
            row.createCell(12).setCellValue(ele.getbSARTelecom());
            if(ele.getTotalcost()!=null)
            row.createCell(13).setCellValue(ele.getTotalcost());
            if(ele.getMeterreading()!=null)
            row.createCell(14).setCellValue(ele.getMeterreading());
            if(ele.getMonthfolding()!=null)
            row.createCell(15).setCellValue(ele.getMonthfolding());
            if(ele.getImportdate()!=null){
                String data = DateFormat.getDateInstance().format(ele.getImportdate());
                System.out.println(data);
                row.createCell(16).setCellValue(data);
            }

        }
        //.....省略部分插入数据修改表格样式等对excel表内容操作的代码

        //调整单元格大小: 必须在单元格设值以后进行
        for (int k = 0; k < column; k++) {
            sheet.autoSizeColumn(k); // 设置为根据内容自动调整列宽
            sheet.setColumnWidth(k,sheet.getColumnWidth(k)*17/10);//设置列宽为自动列宽的1.7倍（当然不是严格的1.7倍，int的除法恕不再讨论），这个1.6左右也可以，这是本人测试的经验值
        }


        try{
            //输出Excel文件
            OutputStream output=response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=dianfei.xls");//文件名
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
        }catch (IOException e){

        }

        return "/a.jsp";//好像没用，返回到哪都一样，都是显示当前页面
    }

    @RequestMapping("/conditionquery")
    public String conditionquery(HttpServletRequest request,String sitename,String year,String month){
        List list = elefeeService.conditionquery(sitename,year,month);
        request.setAttribute("List",list);
        return "/a.jsp";
    }


}


