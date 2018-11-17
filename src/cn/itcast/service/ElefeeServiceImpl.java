package cn.itcast.service;

import cn.itcast.mapper.ElectricityFeesMapper;
import cn.itcast.pojo.ElectricityFees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElefeeServiceImpl implements ElefeeService {

    @Autowired
    private ElectricityFeesMapper electricityFeesMapper;

    @Override
    public int findEleCont() {//返回总记录量
        return electricityFeesMapper.findEleCont();
    }

    @Override//1 3
    public List<ElectricityFees> findElePage(int pageNow, int size) {//返回需要的当前页的数据
        int count = size*(pageNow-1);//3* 1-1 = 0  3* 2-1 = 3
        List<ElectricityFees> list = electricityFeesMapper.findElePage(count);
        if(list.isEmpty())
            return null;
        return list;
    }

    @Override
    public List<ElectricityFees> findAll() {
        List list = electricityFeesMapper.findAll();
        if(list.isEmpty())
            return null;
        return list;
    }

    @Override
    public List<ElectricityFees> conditionquery(String sitename,String year,String month) {
        List list = electricityFeesMapper.conditionquery(sitename);
        if(list.isEmpty())
            return null;
        return list;
    }

    @Override
    public void importRecordEle(ElectricityFees e) {//插入全部
        electricityFeesMapper.insertEle(e);
    }
}
