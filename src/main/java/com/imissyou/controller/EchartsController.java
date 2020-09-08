package com.imissyou.controller;

import com.imissyou.service.TagService;
import com.imissyou.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sys")
public class EchartsController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/Echarts/line")
    @ResponseBody
    public R findLine(){
        return tagService.findLineData();
    }

}
