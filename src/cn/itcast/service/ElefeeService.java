package cn.itcast.service;

import cn.itcast.pojo.ElectricityFees;

import java.util.List;

public interface ElefeeService {
    int findEleCont();
    List<ElectricityFees> findElePage(int pageNow, int size);
    List<ElectricityFees> findAll();
    List<ElectricityFees> conditionquery(String sitename,String year,String month);
}
