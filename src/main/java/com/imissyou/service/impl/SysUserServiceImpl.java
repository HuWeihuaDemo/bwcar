package com.imissyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imissyou.dao.SysUserMapper;
import com.imissyou.dto.DataGridResult;
import com.imissyou.dto.QueryDTO;
import com.imissyou.pojo.SysUser;
import com.imissyou.service.SysUserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findUserByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        if (queryDTO.getSort() != null && !queryDTO.getSort().equals(""))
            queryDTO.setSort("user_id");
        List<SysUser> byPage = sysUserMapper.findByPage(queryDTO);
        PageInfo<SysUser> info = new PageInfo<>(byPage);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, info.getList());
        return result;
    }

    @Override
    public Workbook exportUser() {
        //1,创建一个空的excel文件
        Workbook workbook = new HSSFWorkbook();
        //2,创建sheet，填充数据
        Sheet sheet = workbook.createSheet("某用户信息表导出");
        //3,创建标题数组
        String titles[] = {"用户ID","用户名","邮箱","电话","创建时间"};
        String colums[] = {"userId", "username", "email", "mobile", "createTime"};
        List<Map<String, Object>> maps = sysUserMapper.exportUser();
        Row rowTitle = sheet.createRow(0);
        //标题行
        for (int i = 0; i<titles.length; i++){
            Cell cell = rowTitle.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //遍历数据填充到单元格
        for (int i=0; i< maps.size(); i++){
            //一条记录应该创建一个Row对象 每次+1
            Row row = sheet.createRow(i+1);  //空的，需要填充数据
            //填充单元格
            for (int j =0; j < titles.length; j++){
                Cell cell = row.createCell(j);
                //获取用户ID的值
                Map<String,Object> rowValue = maps.get(i);
                //循环动态设置多个字段的值
                Object o = rowValue.get(colums[j]); //此处获取的值可以是UserId
                //使用map封装的原因
                cell.setCellValue(o+"");
            }
        }
        return workbook;
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }


}
