package com.imissyou.dao;

import com.imissyou.pojo.CarBrand;
import com.imissyou.pojo.CarBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarBrandMapper {
    int countByExample(CarBrandExample example);

    int deleteByExample(CarBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarBrand record);

    int insertSelective(CarBrand record);

    List<CarBrand> selectByExample(CarBrandExample example);

    CarBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarBrand record, @Param("example") CarBrandExample example);

    int updateByExample(@Param("record") CarBrand record, @Param("example") CarBrandExample example);

    int updateByPrimaryKeySelective(CarBrand record);

    int updateByPrimaryKey(CarBrand record);
}