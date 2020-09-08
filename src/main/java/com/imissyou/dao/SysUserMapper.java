package com.imissyou.dao;

import com.imissyou.dto.QueryDTO;
import com.imissyou.pojo.SysUser;
import com.imissyou.pojo.SysUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    void deleteMenu(List<Long> ids);

    List<SysUser> findByPage(QueryDTO queryDTO);

    List<Map<String,Object>> exportUser();

    public SysUser findByUsername(String username);


}
