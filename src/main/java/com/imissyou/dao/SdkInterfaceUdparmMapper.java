package com.imissyou.dao;

import com.imissyou.pojo.SdkInterfaceUdparm;
import com.imissyou.pojo.SdkInterfaceUdparmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SdkInterfaceUdparmMapper {
    int countByExample(SdkInterfaceUdparmExample example);

    int deleteByExample(SdkInterfaceUdparmExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SdkInterfaceUdparm record);

    int insertSelective(SdkInterfaceUdparm record);

    List<SdkInterfaceUdparm> selectByExample(SdkInterfaceUdparmExample example);

    SdkInterfaceUdparm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SdkInterfaceUdparm record, @Param("example") SdkInterfaceUdparmExample example);

    int updateByExample(@Param("record") SdkInterfaceUdparm record, @Param("example") SdkInterfaceUdparmExample example);

    int updateByPrimaryKeySelective(SdkInterfaceUdparm record);

    int updateByPrimaryKey(SdkInterfaceUdparm record);
}