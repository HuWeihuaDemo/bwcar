package com.imissyou.service;

import com.imissyou.dto.DataGridResult;
import com.imissyou.dto.QueryDTO;
import com.imissyou.pojo.SysUser;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface SysUserService {
    public List<SysUser> findAll();

    public DataGridResult findUserByPage(QueryDTO queryDTO);

    public Workbook exportUser();

    public SysUser findByUsername(String username);

}
