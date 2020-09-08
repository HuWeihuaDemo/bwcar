package com.imissyou.service;

import com.imissyou.dto.DataGridResult;
import com.imissyou.dto.QueryDTO;
import com.imissyou.pojo.Tag;
import com.imissyou.utils.R;

public interface TagService {
    public R findLineData();

    public int addTag(Tag tag);

    public void delTag(Integer id);

    public int updateTag(Tag tag);

    public Tag findById(Integer id);

    public DataGridResult findByPage(QueryDTO queryDTO);
}
