package com.imissyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.imissyou.dao.TagMapper;
import com.imissyou.dto.DataGridResult;
import com.imissyou.dto.QueryDTO;
import com.imissyou.pojo.Tag;
import com.imissyou.pojo.TagExample;
import com.imissyou.service.TagService;
import com.imissyou.utils.R;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public R findLineData() {
        List<String> xAxis = new ArrayList<>();
        List seriesData = new ArrayList();
        List<Tag> tags = tagMapper.selectByExample(null);
        for (Tag tag : tags) {
            String name = tag.getName();
            Long clickCount = tag.getClickCount();
            xAxis.add(name);
            seriesData.add(clickCount);
        }
        return R.ok().put("xAxis",xAxis).put("seriesData",seriesData);
    }

    @Override
    public int addTag(Tag tag) {
        int i = tagMapper.insertSelective(tag);
        return i;
    }

    @Override
    public void delTag(Integer id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateTag(Tag tag) {
        int i = tagMapper.updateByPrimaryKey(tag);
        return i;
    }

    @Override
    public Tag findById(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        TagExample example = new TagExample();
        if (queryDTO.getSort()!=null && queryDTO.getSort().equals("")){
            example.setOrderByClause("id"+queryDTO.getOrder());
        }
        return null;
    }
}
