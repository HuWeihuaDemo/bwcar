package com.imissyou.dao;

import com.imissyou.pojo.ActivityPdata;
import com.imissyou.pojo.ActivityPdataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityPdataMapper {
    int countByExample(ActivityPdataExample example);

    int deleteByExample(ActivityPdataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityPdata record);

    int insertSelective(ActivityPdata record);

    List<ActivityPdata> selectByExampleWithBLOBs(ActivityPdataExample example);

    List<ActivityPdata> selectByExample(ActivityPdataExample example);

    ActivityPdata selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityPdata record, @Param("example") ActivityPdataExample example);

    int updateByExampleWithBLOBs(@Param("record") ActivityPdata record, @Param("example") ActivityPdataExample example);

    int updateByExample(@Param("record") ActivityPdata record, @Param("example") ActivityPdataExample example);

    int updateByPrimaryKeySelective(ActivityPdata record);

    int updateByPrimaryKeyWithBLOBs(ActivityPdata record);

    int updateByPrimaryKey(ActivityPdata record);
}