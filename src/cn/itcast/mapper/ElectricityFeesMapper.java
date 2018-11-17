package cn.itcast.mapper;

import cn.itcast.pojo.ElectricityFees;

import java.util.List;

public interface ElectricityFeesMapper {
    int findEleCont();
    List findElePage(int count);
    List findAll();
    List conditionquery(String sitename);
    void insertEle(ElectricityFees e);//插入全部
}
