package cn.itcast.mapper;

import java.util.List;

public interface ElectricityFeesMapper {
    int findEleCont();
    List findElePage(int count);
    List findAll();
    List conditionquery(String sitename);
}
