package com.imissyou.dao;

import com.imissyou.pojo.CarSelectList;
import com.imissyou.pojo.CarSelectListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarSelectListMapper {
    int countByExample(CarSelectListExample example);

    int deleteByExample(CarSelectListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarSelectList record);

    int insertSelective(CarSelectList record);

    List<CarSelectList> selectByExample(CarSelectListExample example);

    CarSelectList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarSelectList record, @Param("example") CarSelectListExample example);

    int updateByExample(@Param("record") CarSelectList record, @Param("example") CarSelectListExample example);

    int updateByPrimaryKeySelective(CarSelectList record);

    int updateByPrimaryKey(CarSelectList record);
}