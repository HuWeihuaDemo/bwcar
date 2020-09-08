package com.imissyou.dao;

import com.imissyou.pojo.ActivityTag;
import com.imissyou.pojo.ActivityTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityTagMapper {
    int countByExample(ActivityTagExample example);

    int deleteByExample(ActivityTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityTag record);

    int insertSelective(ActivityTag record);

    List<ActivityTag> selectByExample(ActivityTagExample example);

    ActivityTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityTag record, @Param("example") ActivityTagExample example);

    int updateByExample(@Param("record") ActivityTag record, @Param("example") ActivityTagExample example);

    int updateByPrimaryKeySelective(ActivityTag record);

    int updateByPrimaryKey(ActivityTag record);
}