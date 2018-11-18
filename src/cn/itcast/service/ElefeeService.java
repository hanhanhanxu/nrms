package cn.itcast.service;

import cn.itcast.pojo.ElectricityFees;

import java.util.List;
import java.util.Map;

public interface ElefeeService {
    int findEleCont();
    List<ElectricityFees> findElePage(int pageNow, int size);
    List<ElectricityFees> findAll();
    List<ElectricityFees> conditionquery(String sitename,String year,String month);
    void importRecordEle(ElectricityFees e);

}
